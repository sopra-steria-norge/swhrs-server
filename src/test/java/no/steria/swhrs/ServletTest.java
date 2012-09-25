package no.steria.swhrs;

import org.apache.commons.lang.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static junit.framework.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

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
    private String POST = "post";
    private String GET = "get";

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
        performCall(GET, RegistrationConstants.REQUEST_URL_HOURS_RETRIEVE_WEEK);
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
        performCall(GET, RegistrationConstants.REQUEST_URL_HOURS_RETRIEVE_DAY);
        assertTrue(getResponseObject().size() > 0);
    }

    @Test
    @Ignore
    public void testAddHourRegistration_AllInformationPresent() throws Exception {
        when(request.getParameter(RegistrationConstants.DATE)).thenReturn("2012-09-03");
        when(request.getParameter(RegistrationConstants.PROJECT_NUMBER)).thenReturn("1112790");
        when(request.getParameter(RegistrationConstants.ACTIVITY_CODE)).thenReturn("1");
        when(request.getParameter(RegistrationConstants.HOURS)).thenReturn("1.25");
        when(request.getParameter(RegistrationConstants.DESCRIPTION)).thenReturn("integrationTest");
        when(request.getParameter(RegistrationConstants.BILLABLE)).thenReturn("false");
        when(request.getParameter(RegistrationConstants.WORK_TYPE)).thenReturn("");

        performCall(POST, RegistrationConstants.REQUEST_URL_HOURS_ADD);

        JSONObject returnObject = getResponseObject();
        Integer entryId = Integer.parseInt((String)returnObject.get(RegistrationConstants.TASK_NUMBER));
        assertNotNull(entryId);
        assertTrue(entryId != 0);
    }

    @Test
    @Ignore
    public void testAddHourRegistration_NoBillableInformationPresent() throws Exception {
        when(request.getParameter(RegistrationConstants.DATE)).thenReturn("2012-09-03");
        when(request.getParameter(RegistrationConstants.PROJECT_NUMBER)).thenReturn("1112790");
        when(request.getParameter(RegistrationConstants.ACTIVITY_CODE)).thenReturn("1");
        when(request.getParameter(RegistrationConstants.HOURS)).thenReturn("1.25");
        when(request.getParameter(RegistrationConstants.DESCRIPTION)).thenReturn("integrationTest");
        when(request.getParameter(RegistrationConstants.WORK_TYPE)).thenReturn("");

        performCall(POST, RegistrationConstants.REQUEST_URL_HOURS_ADD);

        JSONObject returnObject = getResponseObject();
        Integer entryId = Integer.parseInt((String)returnObject.get(RegistrationConstants.TASK_NUMBER));
        assertNotNull(entryId);
        assertTrue(entryId != 0);
    }

    @Test
    public void testAddHourRegistration_nullHoursSentIn() throws Exception {
        when(request.getParameter(RegistrationConstants.DATE)).thenReturn("2012-09-03");
        when(request.getParameter(RegistrationConstants.PROJECT_NUMBER)).thenReturn("1112790");
        when(request.getParameter(RegistrationConstants.ACTIVITY_CODE)).thenReturn("1");
        when(request.getParameter(RegistrationConstants.HOURS)).thenReturn(null);
        when(request.getParameter(RegistrationConstants.DESCRIPTION)).thenReturn("integrationTest");
        when(request.getParameter(RegistrationConstants.WORK_TYPE)).thenReturn("");

        performCall(POST, RegistrationConstants.REQUEST_URL_HOURS_ADD);

        verify(response).sendError(anyInt(), anyString());
    }

    /**
     * Things to consider:
     * - Delete tests where input is null, empty string, no entry should be handled
     * @throws Exception
     */
    @Test
    @Ignore
    public void testDeleteHourRegistration_success() throws Exception {
        when(request.getParameter(RegistrationConstants.TASK_NUMBER)).thenReturn("2105852");
        performCall(POST, RegistrationConstants.REQUEST_URL_HOURS_DELETE);
        assertEquals(RegistrationConstants.TEXT_SUCCESS, writer.toString());
    }

    @Test
    @Ignore
    public void testDeleteHourRegistration_alreadyDeleted() throws Exception {
        when(request.getParameter(RegistrationConstants.TASK_NUMBER)).thenReturn("");
        performCall(POST, RegistrationConstants.REQUEST_URL_HOURS_DELETE);
        verify(response).sendError(anyInt(), anyString());
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
        when(request.getParameter(RegistrationConstants.HOURS)).thenReturn(null);
        when(request.getParameter(RegistrationConstants.DESCRIPTION)).thenReturn("integrationTes2t");
        when(request.getParameter(RegistrationConstants.BILLABLE)).thenReturn("false");
        when(request.getParameter(RegistrationConstants.WORK_TYPE)).thenReturn("");
        when(request.getParameter(RegistrationConstants.TASK_NUMBER)).thenReturn("2105854");

        performCall(POST, RegistrationConstants.REQUEST_URL_HOURS_UPDATE);

        assertEquals(RegistrationConstants.TEXT_SUCCESS, writer.toString());
    }

    @Test
    public void testUpdateHourRegistration_missingFundamentalData() throws Exception {
        when(request.getParameter(RegistrationConstants.DATE)).thenReturn("2012-09-03");
        when(request.getParameter(RegistrationConstants.PROJECT_NUMBER)).thenReturn("1112790");
        when(request.getParameter(RegistrationConstants.ACTIVITY_CODE)).thenReturn("1");
        when(request.getParameter(RegistrationConstants.HOURS)).thenReturn(null);
        when(request.getParameter(RegistrationConstants.DESCRIPTION)).thenReturn("integrationTes2t");
        when(request.getParameter(RegistrationConstants.BILLABLE)).thenReturn("false");
        when(request.getParameter(RegistrationConstants.WORK_TYPE)).thenReturn("");
        when(request.getParameter(RegistrationConstants.TASK_NUMBER)).thenReturn("2105854");

        performCall(POST, RegistrationConstants.REQUEST_URL_HOURS_UPDATE);
        verify(response).sendError(anyInt(), anyString());
    }

    /**
     * Things to consider:
     * - Wrong date format, null, empty string, empty result (but valid input)
     * - Test in the middle, start and end day of a week period
     * @throws Exception
     */
    @Test
    public void testSubmitPeriod_successRun() throws Exception {
        when(request.getParameter(RegistrationConstants.DATE)).thenReturn("2012-09-03");
        when(request.getRequestURL()).thenReturn(new StringBuffer(SERVER_URL + RegistrationConstants.REQUEST_URL_SUBMIT));
        servlet.doPost(request, response);

        assertEquals(RegistrationConstants.TEXT_SUCCESS, writer.toString());
    }

    // This method have to be executed after the success run.
    @Test
    public void testSubmitPeriod_FailRun() throws Exception {
        when(request.getParameter(RegistrationConstants.DATE)).thenReturn("2012-09-03");
        when(request.getRequestURL()).thenReturn(new StringBuffer(SERVER_URL + RegistrationConstants.REQUEST_URL_SUBMIT));
        servlet.doPost(request, response);

        verify(response).sendError(anyInt(), anyString());
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
     * - Favorite doesn't exist   (responds with success even then)
     * @throws Exception
     */
    @Test
    public void testDeleteFavorite() throws Exception {
        when(request.getParameter(RegistrationConstants.PROJECT_NUMBER)).thenReturn("1112790");
        when(request.getParameter(RegistrationConstants.ACTIVITY_CODE)).thenReturn("1");
        performCall(POST, RegistrationConstants.REQUEST_URL_FAVORITE_DELETE);
        assertEquals(RegistrationConstants.TEXT_SUCCESS, writer.toString());
    }

    @Test
    public void testDeleteFavorite_missingInputProjectNumber() throws Exception {
        when(request.getParameter(RegistrationConstants.PROJECT_NUMBER)).thenReturn(null);
        when(request.getParameter(RegistrationConstants.ACTIVITY_CODE)).thenReturn("1");
        performCall(POST, RegistrationConstants.REQUEST_URL_FAVORITE_DELETE);
        verify(response).sendError(anyInt(), anyString());
    }

    /**
     * Things to consider:
     * - Favorite already added (sqlserverexception all the way out with table name = not good)
     * - Invalid favorite details - it will add anything you ask it to.
     * @throws Exception
     */
    @Test
    public void testAddFavorite() throws Exception {
        when(request.getParameter(RegistrationConstants.PROJECT_NUMBER)).thenReturn("1112790");
        when(request.getParameter(RegistrationConstants.ACTIVITY_CODE)).thenReturn("1");
        performCall(POST, RegistrationConstants.REQUEST_URL_FAVORITE_ADD);
        assertEquals(RegistrationConstants.TEXT_SUCCESS, writer.toString());
    }

    @Test
    public void testAddFavorite_missingOneInput_null() throws Exception {
        when(request.getParameter(RegistrationConstants.PROJECT_NUMBER)).thenReturn(null);
        when(request.getParameter(RegistrationConstants.ACTIVITY_CODE)).thenReturn("1");
        performCall(POST, RegistrationConstants.REQUEST_URL_FAVORITE_ADD);

        verify(response).sendError(anyInt(), anyString());
    }

    @Test
    public void testAddFavorite_missingOneInput_empty() throws Exception {
        when(request.getParameter(RegistrationConstants.PROJECT_NUMBER)).thenReturn("");
        when(request.getParameter(RegistrationConstants.ACTIVITY_CODE)).thenReturn("1");
        performCall(POST, RegistrationConstants.REQUEST_URL_FAVORITE_ADD);

        verify(response).sendError(anyInt(), anyString());
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
     * - Null, empty, success, no entry.
     * @throws Exception
     */
    @Test
    public void testSearchFavorites_inputEmptyString() throws Exception {
        when(request.getParameter(RegistrationConstants.SEARCH)).thenReturn("");
        performCall(GET, RegistrationConstants.REQUEST_URL_FAVORITE_SEARCH);
        assertTrue(getResponseObject().size() > 0);
    }

    @Test
    public void testSearchFavorites_inputNull() throws Exception {
        when(request.getParameter(RegistrationConstants.SEARCH)).thenReturn(null);
        performCall(GET, RegistrationConstants.REQUEST_URL_FAVORITE_SEARCH);
        assertTrue(getResponseObject().isEmpty());
    }

    @Test
    public void testSearchFavorites_inputExists() throws Exception {
        when(request.getParameter(RegistrationConstants.SEARCH)).thenReturn("ferie");
        performCall(GET, RegistrationConstants.REQUEST_URL_FAVORITE_SEARCH);
        assertTrue(getResponseObject().size() > 0);
    }

    @Test
    public void testSearchFavorites_inputDoesNotExist() throws Exception {
        when(request.getParameter(RegistrationConstants.SEARCH)).thenReturn("dotømmer");
        performCall(GET, RegistrationConstants.REQUEST_URL_FAVORITE_SEARCH);
        assertTrue(getResponseObject().isEmpty());
    }

    private void performCall(String methodType, String requestSpecificUrl) throws IOException, ServletException {
        when(request.getRequestURL()).thenReturn(new StringBuffer(SERVER_URL + requestSpecificUrl));
        if (StringUtils.equals(methodType, POST)) servlet.doPost(request, response);
        else servlet.doGet(request, response);
    }

    private JSONObject getResponseObject() throws ParseException {
        return (JSONObject) parser.parse(writer.toString());
    }

}
