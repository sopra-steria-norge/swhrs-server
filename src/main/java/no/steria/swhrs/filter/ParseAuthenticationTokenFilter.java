package no.steria.swhrs.filter;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ParseAuthenticationTokenFilter implements Filter {
    public static final String AUTHENTICATION_TOKEN_HEADER_NAME = "X-Authentication-Token";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
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

        if (authenticationTokenJsonObject != null) {
            request.setAttribute("authenticationToken", authenticationTokenJsonObject);
            chain.doFilter(req, resp);
        } else {
            incorrectAuthenticationHeader(response);
        }
    }

    private static void incorrectAuthenticationHeader(HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Did not specify correct authentication header");
    }

    @Override
    public void destroy() {
    }
}
