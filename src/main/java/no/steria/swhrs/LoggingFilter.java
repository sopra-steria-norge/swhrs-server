package no.steria.swhrs;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

/**
 * Date: 26.09.12
 * Time: 12:30
 * All rights reserved Steria AS 2012
 *
 * @author chrm@steria.no
 */
public class LoggingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println(servletRequest.getParameterMap().toString());

        // do logging of request
        System.out.println(servletRequest.toString());

        // replace the servletResponse with the one I have that will log
        HttpServletResponseWrapper wrappedResponse = new HttpServletResponseWrapper((HttpServletResponse) servletResponse);

        // Say I'm done
        filterChain.doFilter(servletRequest, wrappedResponse);
    }

    @Override
    public void destroy() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
