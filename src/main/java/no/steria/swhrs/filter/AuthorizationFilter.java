package no.steria.swhrs.filter;

import org.json.simple.JSONObject;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

        if (authenticationToken == null) {
            return;
        }

        List<String> userBlacklist = (List<String>) req.getServletContext().getAttribute("userBlacklist");
        List<String> userWhitelist = (List<String>) req.getServletContext().getAttribute("userWhitelist");
        Boolean onlyAllowUsersFromWhitelist = (Boolean) req.getServletContext().getAttribute("onlyAllowUsersFromWhitelist");

        if (userBlacklist.contains(authenticationToken.get("username"))) {
            response.sendError(500, "User in blacklist.");
        }

        if (!onlyAllowUsersFromWhitelist || userWhitelist.contains(authenticationToken.get("username"))) {
            chain.doFilter(request, response);
        } else {
            response.sendError(500, "User not in whitelist.");
        }
    }

    @Override
    public void destroy() {
    }
}