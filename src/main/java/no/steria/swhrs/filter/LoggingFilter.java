package no.steria.swhrs.filter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Map;

public class LoggingFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        WitheldableContentHttpServletResponse response = new WitheldableContentHttpServletResponse((HttpServletResponse) resp);

        String requestId = String.valueOf(System.currentTimeMillis());
        String username;
        try {
            username = (String) ((JSONObject) request.getAttribute("authenticationToken")).get("username");
        } catch (Exception e) {
            username = "<no username>";
        }

        logRequest(request.getMethod(), requestId, username, request.getRequestURL().toString(), getParametersAsJSONString(request), request.getRemoteAddr());

        try {
            chain.doFilter(request, response);
        } catch (Exception e) {
            logUnsuccessfulResponse(requestId, e);
            response.sendError(500, "Server error. Request ID: " + requestId);
            return;
        }

        response.flushBodyContent();
        logSuccessfulResponse(requestId, response.getResponseBody());
    }

    private static String getParametersAsJSONString(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
            JSONArray parameterArray = new JSONArray();
            parameterArray.addAll(Arrays.asList(entry.getValue()));
            jsonObject.put(entry.getKey(), parameterArray.toJSONString());
        }
        return jsonObject.toJSONString();
    }

    @Override
    public void destroy() {
    }

    private void logRequest(String method, String requestId, String userId, String requestPath, String parameters, String ip) {
        logger.info("{} requestId={} userId={} path={} parameters={} IP={}", new Object[]{method, requestId, userId, requestPath, parameters, ip});
    }

    private void logSuccessfulResponse(String requestId, String requestBody) {
        logger.info("OK requestId={}", requestId);
        logger.debug("RESPONSE requestId={} responseBody={}", requestId, requestBody);
    }

    private void logUnsuccessfulResponse(String requestId, Exception exception) {
        logger.error("ERROR requestId=" + requestId, exception);
    }

    private static class WitheldableContentHttpServletResponse extends HttpServletResponseWrapper {
        private StringWriter stringWriter = new StringWriter();

        public WitheldableContentHttpServletResponse(HttpServletResponse response) {
            super(response);
            response.setCharacterEncoding("UTF-8");
        }

        public String getResponseBody() {
            return stringWriter.toString();
        }

        public void flushBodyContent() throws IOException {
            super.getWriter().print(stringWriter.toString());
        }

        @Override
        public PrintWriter getWriter() throws IOException {
            return new PrintWriter(stringWriter);
        }

        @Override
        public ServletOutputStream getOutputStream() throws IOException {
            throw new UnsupportedOperationException("Not supported for binary streams");
        }
    }
}