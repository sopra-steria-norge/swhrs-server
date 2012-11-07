package no.steria.swhrs.filter;

import no.steria.swhrs.SwhrsFilterException;
import no.steria.swhrs.dao.MSSQLHourRegDao;
import no.steria.swhrs.domain.Password;
import no.steria.swhrs.domain.User;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);
    private MSSQLHourRegDao hourRegDao;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        hourRegDao = (MSSQLHourRegDao) filterConfig.getServletContext().getAttribute("hourRegDao");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        JSONObject authenticationTokenJsonObject = (JSONObject) request.getAttribute("authenticationToken");

        User user;
        try {
            String username = (String) authenticationTokenJsonObject.get("username");
            String password = (String) authenticationTokenJsonObject.get("password");
            user = hourRegDao.findUser(username, Password.fromHashed(password));
        } catch (RuntimeException e) {
            throw new SwhrsFilterException(HttpServletResponse.SC_FORBIDDEN, "Username and password not set");
        }
        if (user != null) {
            request.setAttribute("user", user);
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            throw new SwhrsFilterException(HttpServletResponse.SC_FORBIDDEN, "Username and password was not recognized");
        }

        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
    }
}
