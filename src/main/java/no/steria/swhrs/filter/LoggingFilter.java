package no.steria.swhrs.filter;

import no.steria.swhrs.SwhrsFilterException;
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
        } catch (SwhrsFilterException e) {
            logUnsuccessfulExpectedResponse(requestId, e);
            response.sendError(e.getStatusCode(), e.getMessage() + ". Request ID: " + requestId);
            return;
        } catch (Exception e) {
            logUnsuccessfulUnexpectedResponse(requestId, e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unexpected error. Request ID: " + requestId);
            return;
        }

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

    private void logUnsuccessfulUnexpectedResponse(String requestId, Exception exception) {
        logger.error("ERROR requestId=" + requestId, exception);
    }

    private void logUnsuccessfulExpectedResponse(String requestId, SwhrsFilterException exception) {
        logger.error("ERROR requestId={} errorMessage={}", requestId, exception.getMessage());
    }

    private static class WitheldableContentHttpServletResponse extends HttpServletResponseWrapper {
        private StringBuilder stringBuilder = new StringBuilder();

        public WitheldableContentHttpServletResponse(HttpServletResponse response) {
            super(response);
        }

        public String getResponseBody() {
            return stringBuilder.toString();
        }

        @Override
        public PrintWriter getWriter() throws IOException {
            return new PrintWriter(super.getWriter()) {
                @Override
                public void print(String s) {
                    stringBuilder.append(s);
                    super.print(s);
                }
            };
        }
    }
}