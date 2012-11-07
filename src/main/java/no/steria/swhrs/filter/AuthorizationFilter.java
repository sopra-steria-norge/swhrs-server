package no.steria.swhrs.filter;

import no.steria.swhrs.SwhrsFilterException;
import org.json.simple.JSONObject;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AuthorizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        JSONObject authenticationToken = (JSONObject) request.getAttribute("authenticationToken");

        List<String> userBlacklist = (List<String>) req.getServletContext().getAttribute("userBlacklist");
        List<String> userWhitelist = (List<String>) req.getServletContext().getAttribute("userWhitelist");
        Boolean onlyAllowUsersFromWhitelist = (Boolean) req.getServletContext().getAttribute("onlyAllowUsersFromWhitelist");

        if (userBlacklist.contains(authenticationToken.get("username"))) {
            throw new SwhrsFilterException(500, "User in blacklist");
        }

        if (!onlyAllowUsersFromWhitelist || userWhitelist.contains(authenticationToken.get("username"))) {
            chain.doFilter(request, response);
        } else {
            throw new SwhrsFilterException(500, "User not in whitelist");
        }
    }

    @Override
    public void destroy() {
    }
}