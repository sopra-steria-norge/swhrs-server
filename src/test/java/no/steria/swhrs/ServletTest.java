package no.steria.swhrs;

import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Date: 21.09.12
 * Time: 12:17
 * All rights reserved Steria AS 2012
 *
 * @author chrm@steria.no
 */
public class ServletTest {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private StringWriter writer;
    private TestServlet servlet;
    private static JettyServer jettyServer;

    @Before
    public void setup() throws Exception {
        jettyServer = new JettyServer();
        jettyServer.setPort(10000);
        jettyServer.startServer();

        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        when(request.getHeader(AuthorizationFilter.AUTHENTICATION_TOKEN_HEADER_NAME)).thenReturn("{\"username\": \"ror\", \"password\": \"salt_IcZa2+j8IMsLptIK4JFG1ODO8Fk=\"}");
        when(request.getParameter(RegistrationConstants.DATE)).thenReturn("2012-09-05");

        User user = new User();
        user.setUsername("ROR");
        user.setPassword(Password.fromPlaintext("salt", "password"));
        when(request.getAttribute(RegistrationConstants.USER)).thenReturn(user);

        writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        when(response.getWriter()).thenReturn(printWriter);

        servlet = new TestServlet();
        servlet.init();
    }


    @Test
    public void testRetrieveWeekInformation() throws Exception {
        when(request.getRequestURL()).thenReturn(new StringBuffer("http://localhost:10000/swhrs-app/hours/week"));
        servlet.doGet(request, response);
        System.out.println(writer.toString());
    }
}
