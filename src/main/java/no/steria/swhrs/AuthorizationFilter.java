package no.steria.swhrs;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(AuthorizationFilter.class);
    public static final String AUTHENTICATION_TOKEN_HEADER_NAME = "X-Authentication-Token";
    private MSSQLHourRegDao hourRegDao;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try {
            hourRegDao = MSSQLHourRegDao.createInstance();
        } catch (NamingException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        JSONObject authenticationTokenJsonObject;
        try {
            authenticationTokenJsonObject = (JSONObject) JSONValue.parseWithException(request.getHeader(AUTHENTICATION_TOKEN_HEADER_NAME));
        } catch (NullPointerException e) {
            incorrectAuthenticationHeader(response);
            return;
        } catch (ParseException e) {
            incorrectAuthenticationHeader(response);
            return;
        } catch (ClassCastException e) {
            incorrectAuthenticationHeader(response);
            return;
        }

        User user;
        try {
            String username = (String) authenticationTokenJsonObject.get("username");
            String password = (String) authenticationTokenJsonObject.get("password");
            user = hourRegDao.findUser(username, Password.fromHashed(password));
        } catch (RuntimeException e) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Username and password not set.");
            return;
        }
        if (user != null) {
            request.setAttribute("user", user);
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Username and password was not recognized.");
            return;
        }

        chain.doFilter(req, resp);
    }

    private static void incorrectAuthenticationHeader(HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Did not specify correct authentication header");
    }

    @Override
    public void destroy() {
    }
}