package no.steria.swhrs;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
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
    private JSONParser parser;

    private static String SERVER_URL = "http://localhost:10000/swhrs-app/";

    @Before
    public void setup() throws Exception {
        jettyServer = new JettyServer();
        jettyServer.setPort(10000);
        jettyServer.startServer();

        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        when(request.getHeader(AuthorizationFilter.AUTHENTICATION_TOKEN_HEADER_NAME)).thenReturn("{\"username\": \"ror\", \"password\": \"salt_IcZa2+j8IMsLptIK4JFG1ODO8Fk=\"}");

        User user = new User();
        user.setUsername("ROR");
        user.setPassword(Password.fromPlaintext("salt", "password"));
        when(request.getAttribute(RegistrationConstants.USER)).thenReturn(user);

        writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        when(response.getWriter()).thenReturn(printWriter);

        servlet = new TestServlet();
        servlet.init();

        parser = new JSONParser();
    }

    /**
     * Things to consider:
     * - Wrong date format, null, empty string, empty result (but valid input)
     * - Test in the middle, start and end day of a week period
     * @throws Exception
     */
    @Test
    public void testRetrieveWeekInformation() throws Exception {
        when(request.getParameter(RegistrationConstants.DATE)).thenReturn("2012-09-05");
        when(request.getRequestURL()).thenReturn(new StringBuffer(SERVER_URL + RegistrationConstants.REQUEST_URL_HOURS_RETRIEVE_WEEK));
        servlet.doGet(request, response);
        assertTrue(getResponseObject().size() > 0);
        System.out.println(writer.toString());
    }

    /**
     * Things to consider:
     * - Wrong date format, null, empty string, empty result (but valid input)
     * @throws Exception
     */
    @Test
    public void testRetrieveDayInformation() throws Exception {
        when(request.getParameter(RegistrationConstants.DATE)).thenReturn("2012-09-03");
        when(request.getRequestURL()).thenReturn(new StringBuffer(SERVER_URL + RegistrationConstants.REQUEST_URL_HOURS_RETRIEVE_DAY));
        servlet.doGet(request, response);
        assertTrue(getResponseObject().size() > 0);
    }

    @Test
    @Ignore
    public void testAddHourRegistration() throws Exception {
        when(request.getParameter(RegistrationConstants.DATE)).thenReturn("2012-09-03");
        when(request.getParameter(RegistrationConstants.PROJECT_NUMBER)).thenReturn("1112790");
        when(request.getParameter(RegistrationConstants.ACTIVITY_CODE)).thenReturn("1");
        when(request.getParameter(RegistrationConstants.HOURS)).thenReturn("1.25");
        when(request.getParameter(RegistrationConstants.DESCRIPTION)).thenReturn("integrationTest");
        when(request.getParameter(RegistrationConstants.BILLABLE)).thenReturn("false");
        when(request.getParameter(RegistrationConstants.WORK_TYPE)).thenReturn("");

        when(request.getRequestURL()).thenReturn(new StringBuffer(SERVER_URL + RegistrationConstants.REQUEST_URL_HOURS_ADD));
        servlet.doPost(request, response);
        assertEquals(RegistrationConstants.TEXT_SUCCESS, writer.toString());
    }

    /**
     * Things to consider:
     * - Delete tests where input is null, empty string, no entry should be handled
     * @throws Exception
     */
    @Test
    @Ignore
    public void testDeleteHourRegistration() throws Exception {
        when(request.getParameter(RegistrationConstants.TASK_NUMBER)).thenReturn("2105840");
        when(request.getRequestURL()).thenReturn(new StringBuffer(SERVER_URL + RegistrationConstants.REQUEST_URL_HOURS_DELETE));
        servlet.doPost(request, response);
        assertEquals(RegistrationConstants.TEXT_SUCCESS, writer.toString());
    }

    /**
     * Things to consider:
     * - Date mandatory? - Fails on empty string
     * - Format of double value, comma or period
     * @throws Exception
     */
    @Test
    @Ignore
    public void testUpdateHourRegistration() throws Exception {
        when(request.getParameter(RegistrationConstants.DATE)).thenReturn("2012-09-03");
        when(request.getParameter(RegistrationConstants.PROJECT_NUMBER)).thenReturn("1112790");
        when(request.getParameter(RegistrationConstants.ACTIVITY_CODE)).thenReturn("1");
        when(request.getParameter(RegistrationConstants.HOURS)).thenReturn("1.76");
        when(request.getParameter(RegistrationConstants.DESCRIPTION)).thenReturn("integrationTes2t");
        when(request.getParameter(RegistrationConstants.BILLABLE)).thenReturn("false");
        when(request.getParameter(RegistrationConstants.WORK_TYPE)).thenReturn("");
        when(request.getParameter(RegistrationConstants.TASK_NUMBER)).thenReturn("2105842");

        when(request.getRequestURL()).thenReturn(new StringBuffer(SERVER_URL + RegistrationConstants.REQUEST_URL_HOURS_UPDATE));
        servlet.doPost(request, response);

        assertEquals(RegistrationConstants.TEXT_SUCCESS, writer.toString());
    }

    /**
     * Things to consider:
     * - Wrong date format, null, empty string, empty result (but valid input)
     * - Test in the middle, start and end day of a week period
     * @throws Exception
     */
    @Test
    public void testSubmitPeriod() throws Exception {
        when(request.getParameter(RegistrationConstants.DATE)).thenReturn("2012-09-03");
        when(request.getRequestURL()).thenReturn(new StringBuffer(SERVER_URL + RegistrationConstants.REQUEST_URL_SUBMIT));
        servlet.doPost(request, response);

        assertEquals(RegistrationConstants.TEXT_SUCCESS, writer.toString());
    }

    /**
     * Things to consider:
     * - Wrong date format, null, empty string, empty result (but valid input)
     * - Test in the middle, start and end day of a week period
     * @throws Exception
     */
    @Test
    public void testReopenPeriod() throws Exception {
        when(request.getParameter(RegistrationConstants.DATE)).thenReturn("2012-09-03");
        when(request.getRequestURL()).thenReturn(new StringBuffer(SERVER_URL + RegistrationConstants.REQUEST_URL_REOPEN));
        servlet.doPost(request, response);

        assertEquals(RegistrationConstants.TEXT_SUCCESS, writer.toString());
    }

    /**
     * Things to consider:
     * - Favorite already added (sqlserverexception all the way out with table name = not good)
     * - Invalid favorite details
     * - Invalid input
     * @throws Exception
     */
    @Test
    public void testAddFavorite() throws Exception {
        when(request.getParameter(RegistrationConstants.PROJECT_NUMBER)).thenReturn("1112790");
        when(request.getParameter(RegistrationConstants.ACTIVITY_CODE)).thenReturn("1");
        when(request.getRequestURL()).thenReturn(new StringBuffer(SERVER_URL + RegistrationConstants.REQUEST_URL_FAVORITE_ADD));
        servlet.doPost(request, response);

        assertEquals(RegistrationConstants.TEXT_SUCCESS, writer.toString());
    }

    /**
     * Things to consider:
     * - Favorite doesn't exist
     * - Invalid input
     * @throws Exception
     */
    @Test
    public void testDeleteFavorite() throws Exception {
        when(request.getParameter(RegistrationConstants.PROJECT_NUMBER)).thenReturn("1112790");
        when(request.getParameter(RegistrationConstants.ACTIVITY_CODE)).thenReturn("1");
        when(request.getRequestURL()).thenReturn(new StringBuffer(SERVER_URL + RegistrationConstants.REQUEST_URL_FAVORITE_DELETE));
        servlet.doPost(request, response);

        assertEquals(RegistrationConstants.TEXT_SUCCESS, writer.toString());
    }

    /**
     * Things to consider:
     * - Non existent user
     * @throws Exception
     */
    @Test
    public void testGetFavorites() throws Exception {
        when(request.getRequestURL()).thenReturn(new StringBuffer(SERVER_URL + RegistrationConstants.REQUEST_URL_FAVORITE_GET));
        servlet.doGet(request, response);

        assertTrue(getResponseObject().size() > 0);
    }

    /**
     * Things to consider:
     * -
     * @throws Exception
     */
    @Test
    public void testSearchFavorites() throws Exception {
        when(request.getRequestURL()).thenReturn(new StringBuffer(SERVER_URL + RegistrationConstants.REQUEST_URL_FAVORITE_SEARCH));
        when(request.getParameter(RegistrationConstants.SEARCH)).thenReturn("");
        servlet.doGet(request, response);

        assertTrue(getResponseObject().size() > 0);
    }

    private JSONObject getResponseObject() throws ParseException {
        return (JSONObject) parser.parse(writer.toString());
    }

}
