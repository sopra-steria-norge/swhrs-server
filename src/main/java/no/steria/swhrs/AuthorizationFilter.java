package no.steria.swhrs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

public class AuthorizationFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(AuthorizationFilter.class);
    private HourRegDao hourRegDao;
    private Set<String> restrictedUris;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        hourRegDao = MSSQLHourRegDao.createInstance();
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession session = request.getSession(false);

        if (session == null || !(session.getAttribute("user") instanceof User)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Must be authenticated");
            return;
        }

        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
    }
}