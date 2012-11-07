package no.steria.swhrs.filter;

import no.steria.swhrs.SwhrsFilterException;
import org.joda.time.Instant;
import org.json.simple.JSONObject;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AccountLockoutFilter implements Filter {
    private Map<String, Integer> loginTrialsMap = new ConcurrentHashMap<String, Integer>();
    private Map<String, Instant> accountLockoutMap = new ConcurrentHashMap<String, Instant>();

    private static final int LOCKOUT_NUMBER_OF_TRIALS_THRESHOLD = 3;
    private static final int LOCKOUT_IN_MS_THRESHOLD = 60000;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        if (request.getAttribute("authenticationToken") == null) {
            throw new SwhrsFilterException(500, "No authentication token");
        }
        String username = (String) ((JSONObject) request.getAttribute("authenticationToken")).get("username");

        if (!loginTrialsMap.containsKey(username)) {
            loginTrialsMap.put(username, 0);
        }

        if (accountLockoutMap.get(username) != null && accountLockoutMap.get(username).isAfterNow()) {
            throw new SwhrsFilterException(500, "Account lockout");
        } else {
            chain.doFilter(request, response);
        }

        if (response.getStatus() == 403) {
            loginTrialsMap.put(username, loginTrialsMap.get(username) + 1);
            if (loginTrialsMap.get(username) >= LOCKOUT_NUMBER_OF_TRIALS_THRESHOLD) {
                accountLockoutMap.put(username, new Instant(Instant.now().plus(LOCKOUT_IN_MS_THRESHOLD)));
                loginTrialsMap.put(username, 0);
            }
        }
    }

    @Override
    public void destroy() {
    }
}
