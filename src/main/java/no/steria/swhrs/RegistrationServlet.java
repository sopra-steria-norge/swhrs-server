package no.steria.swhrs;

import no.steria.swhrs.dao.HourRegDao;
import no.steria.swhrs.domain.*;
import no.steria.swhrs.util.JSONBuilder;
import no.steria.swhrs.util.RegistrationConstants;
import no.steria.swhrs.util.UtilityMethods;
import no.steria.swhrs.validator.Validators;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


/**
 * @author xsts
 * @author chrm@steria.no
 */
public class RegistrationServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(RegistrationServlet.class);
    private static final long serialVersionUID = -1090477374982937503L;
    private static final String APPLICATION_JSON = "application/json";
    private static final String TEXT_PLAIN = "text/plain";

    HourRegDao hourRegDao;

    @Override
    public void init() throws ServletException {
        super.init();
        hourRegDao = (HourRegDao) getServletContext().getAttribute("hourRegDao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURL().toString().contains(RegistrationConstants.REQUEST_URL_HOURS_RETRIEVE_DAY)) {
            getDayListResponseAsJSON(req, resp);
        } else if (req.getRequestURL().toString().contains(RegistrationConstants.REQUEST_URL_FAVORITE_GET)) {
            getFavorites(req, resp);
        } else if (req.getRequestURL().toString().contains(RegistrationConstants.REQUEST_URL_HOURS_RETRIEVE_WEEK)) {
            getWeekDetails(req, resp);
        } else if (req.getRequestURL().toString().contains(RegistrationConstants.REQUEST_URL_FAVORITE_SEARCH)) {
            searchFavourites(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURL().toString().contains(RegistrationConstants.REQUEST_URL_HOURS_ADD)) {
            addHourRegistration(req, resp);
        } else if (req.getRequestURL().toString().contains(RegistrationConstants.REQUEST_URL_HOURS_DELETE)) {
            deleteHourRegistration(req, resp);
        } else if (req.getRequestURL().toString().contains(RegistrationConstants.REQUEST_URL_FAVORITE_ADD)) {
            addFavourites(req, resp);
        } else if (req.getRequestURL().toString().contains(RegistrationConstants.REQUEST_URL_SUBMIT)) {
            submitPeriod(req, resp);
        } else if (req.getRequestURL().toString().contains(RegistrationConstants.REQUEST_URL_REOPEN)) {
            reopenPeriod(req, resp);
        } else if (req.getRequestURL().toString().contains(RegistrationConstants.REQUEST_URL_FAVORITE_DELETE)) {
            deleteFavourite(req, resp);
        } else if (req.getRequestURL().toString().contains(RegistrationConstants.REQUEST_URL_HOURS_UPDATE)) {
            updateHourRegistration(req, resp);
        }
    }

    /**
     * Adds an hour registration the database
     *
     * @param request The HTTP request containing parameters of "ProjectNr", "hours", "lunchNumber" and "description"
     */
    private void addHourRegistration(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (Validators.hasValidationError(RegistrationConstants.REQUEST_URL_HOURS_ADD, request, response)) {
            return;
        }

        User user = getUserAttribute(request);
        String username = user.getUsername();
        String projectNumber = request.getParameter(RegistrationConstants.PROJECT_NUMBER);
        String activityCode = request.getParameter(RegistrationConstants.ACTIVITY_CODE);
        String hours = request.getParameter(RegistrationConstants.HOURS);
        String description = request.getParameter(RegistrationConstants.DESCRIPTION);
        DateTime date = getDate(request.getParameter(RegistrationConstants.DATE));
        String workType = request.getParameter(RegistrationConstants.WORK_TYPE);
        boolean billable = resolveBillable(request.getParameter(RegistrationConstants.BILLABLE),
                user.getUsername(), UtilityMethods.getProjectKey(projectNumber, activityCode));

        Integer entryId = hourRegDao.addHourRegistrations(username, username, projectNumber, activityCode, date, Double.parseDouble(hours), billable,
                workType, description, false);
        fillInSuccessResponse(response, APPLICATION_JSON, JSONBuilder.createFromAddHours(entryId).toString());
    }

    /**
     * This method updates an hour registration with a new description for time entry (comment) and new hours number
     *
     * @param request HttpServletRequest
     */
    private void updateHourRegistration(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (Validators.hasValidationError(RegistrationConstants.REQUEST_URL_HOURS_UPDATE, request, response)) {
            return;
        }

        User user = getUserAttribute(request);
        String userId = user.getUsername();
        String taskNumber = request.getParameter(RegistrationConstants.TASK_NUMBER);
        String hours = request.getParameter(RegistrationConstants.HOURS);
        String description = request.getParameter(RegistrationConstants.DESCRIPTION);
        String activityCode = request.getParameter(RegistrationConstants.ACTIVITY_CODE);
        String projectNumber = request.getParameter(RegistrationConstants.PROJECT_NUMBER);
        DateTime date = getDate(request.getParameter(RegistrationConstants.DATE));
        String workType = request.getParameter(RegistrationConstants.WORK_TYPE);
        boolean billable = resolveBillable(request.getParameter(RegistrationConstants.BILLABLE),
                user.getUsername(), UtilityMethods.getProjectKey(projectNumber, activityCode));

        hourRegDao.updateHourRegistration(userId, taskNumber, projectNumber, activityCode, date, Double.parseDouble(hours), billable, workType, description);
        fillInSuccessResponse(response);
    }

    /**
     * This method will delete an hour registration from the database
     *
     * @param request  The HTTP request contains taskNumber which is the unique identifier for each registration in the database
     * @param response The HTTP response will return plain text with either
     *                 "ERROR: Already submitted" if the deleteHourRegistration returns false, meaning that the registration is locked
     *                 or "success" if the deletion was successful
     * @throws IOException
     */
    private void deleteHourRegistration(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (Validators.hasValidationError(RegistrationConstants.REQUEST_URL_HOURS_DELETE, request, response)) {
            return;
        }

        User user = getUserAttribute(request);
        String username = user.getUsername();
        String taskNumber = request.getParameter(RegistrationConstants.TASK_NUMBER);

        try {
            hourRegDao.deleteHourRegistration(username, taskNumber);
        } catch (RuntimeException r) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, r.getMessage());
        }
        fillInSuccessResponse(response);
    }

    /**
     * This method removes a project from the users favourite list in the database
     *
     * @param request The HTTP request contains project number and activity code
     */
    private void deleteFavourite(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (Validators.hasValidationError(RegistrationConstants.REQUEST_URL_FAVORITE_DELETE, request, response)) {
            return;
        }

        User user = getUserAttribute(request);
        String projectNumber = request.getParameter(RegistrationConstants.PROJECT_NUMBER);
        String activityCode = request.getParameter(RegistrationConstants.ACTIVITY_CODE);

        hourRegDao.deleteFavourite(user.getUsername(), projectNumber, activityCode);
        fillInSuccessResponse(response);
    }

    /**
     * This method adds new projects to the users favourite list
     *
     * @param request The HTTP request contains project number and activity code
     */
    private void addFavourites(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (Validators.hasValidationError(RegistrationConstants.REQUEST_URL_FAVORITE_ADD, request, response)) {
            return;
        }

        User user = getUserAttribute(request);
        String projectNumber = request.getParameter(RegistrationConstants.PROJECT_NUMBER);
        String activityCode = request.getParameter(RegistrationConstants.ACTIVITY_CODE);

        // TODO validate that the project and activity code being added actually exists, this is a shortcoming for now
        // TODO validate that the project isn't already a favorite of this user

        hourRegDao.addFavourites(user.getUsername(), projectNumber, activityCode);
        fillInSuccessResponse(response);
    }

    /**
     * Returns a HTTP response containing a JSON object of the users favourites stored in the database
     *
     * @param response HTTP request containing a JSON object of the users favourites
     * @throws IOException
     */
    private void getFavorites(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (Validators.hasValidationError(RegistrationConstants.REQUEST_URL_FAVORITE_GET, request, response)) {
            return;
        }

        User user = getUserAttribute(request);
        List<UserFavourites> userList = hourRegDao.getUserFavourites(user.getUsername());
        fillInSuccessResponse(response, APPLICATION_JSON, JSONBuilder.createFromFavourites(userList).toString());
    }

    /**
     * This method will search through ProjectDetail with the search input, and
     * return a JSON string with project number, activity code and description
     *
     * @param request  The HTTP request contains a search input which is used to search through
     *                 projects in the database
     * @param response THE HTTP response will return a JSON String with project number, activity code, and description.
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    private void searchFavourites(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (Validators.hasValidationError(RegistrationConstants.REQUEST_URL_FAVORITE_SEARCH, request, response)) {
            return;
        }

        String searchInput = request.getParameter(RegistrationConstants.SEARCH);
        List<ProjectDetail> project = hourRegDao.searchProjects(searchInput);
        fillInSuccessResponse(response, APPLICATION_JSON, JSONBuilder.createProjects(project).toString());
    }

    /**
     * This method will submit a period entry - start date of the period needs to be passed as an argument.
     *
     * @param request  The HTTP request
     * @param response The HTTP response
     * @throws IOException
     */
    private void submitPeriod(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (Validators.hasValidationError(RegistrationConstants.REQUEST_URL_SUBMIT, request, response)) {
            return;
        }

        String username = getUserAttribute(request).getUsername();
        DateTime startDate = getDate(request.getParameter(RegistrationConstants.DATE));

        try {
            hourRegDao.submitHours(username, username, startDate);
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
            return;
        }

        fillInSuccessResponse(response);
    }

    /**
     * This method will attempt to reopen a period entry - start date of the period needs to be passed as an argument.
     *
     * @param request  The HTTP request
     * @param response The HTTP response
     * @throws IOException
     */
    private void reopenPeriod(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (Validators.hasValidationError(RegistrationConstants.REQUEST_URL_REOPEN, request, response)) {
            return;
        }

        String username = getUserAttribute(request).getUsername();
        DateTime startDate = getDate(request.getParameter(RegistrationConstants.DATE));
        hourRegDao.reopenHours(username, username, startDate);
        fillInSuccessResponse(response);
    }

    /**
     * This method returns a HTTP request containing JSON data for a period
     *
     * @param request  The HTTP request contains the week parameter which contains strings of either "thisWeek", "prevWeek" or "nextWeek"
     * @param response The HTTP response will return a json object containing data about weekdays, dates and hours for the week requested
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    private void getWeekDetails(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (Validators.hasValidationError(RegistrationConstants.REQUEST_URL_HOURS_RETRIEVE_WEEK, request, response)) {
            return;
        }

        User user = getUserAttribute(request);
        String userId = user.getUsername();

        DateTime date = RegistrationConstants.dateTimeFormatter.parseDateTime(request.getParameter(RegistrationConstants.DATE));
        PeriodDetails periodDetails = hourRegDao.getPeriodDetails(userId, date);
        NormTimeDetails normTimeDetails = hourRegDao.getNormTimeDetails(userId, userId, periodDetails.getStartDate());
        WeekDetails weekDetails = hourRegDao.getWeekList(userId, userId, "EMP", periodDetails.getStartDate());
        fillInSuccessResponse(response, APPLICATION_JSON, JSONBuilder.createFromWeekDetails(weekDetails, periodDetails, normTimeDetails).toString());
    }

    /**
     * Returns a HTTP response containing all hour registrations for a certain day stored in a JSON object
     *
     * @param request  The HTTP request containing a parameter "day" containing either "today", "prevDay"
     * @param response The HTTP response contains a json object with all data about a registration needed to display it in the app
     * @throws IOException
     */
    private void getDayListResponseAsJSON(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (Validators.hasValidationError(RegistrationConstants.REQUEST_URL_HOURS_RETRIEVE_DAY, request, response)) {
            return;
        }

        User user = getUserAttribute(request);
        DateTime date = getDate(request.getParameter(RegistrationConstants.DATE));
        List<HourRegistration> hourRegistrationList = hourRegDao.getAllHoursForDate(user.getUsername(), date);
        fillInSuccessResponse(response, APPLICATION_JSON, JSONBuilder.createFromHours(hourRegistrationList, date).toString());
    }

    private static User getUserAttribute(HttpServletRequest req) {
        return (User) req.getAttribute(RegistrationConstants.USER);
    }

    private boolean resolveBillable(String parameter, String user, String projectKey) throws IOException {
        Boolean billable;
        if (StringUtils.isBlank(parameter)) billable = resolveBillable(user, projectKey);
        else billable = Boolean.parseBoolean(parameter);
        return billable;
    }

    private Boolean resolveBillable(String user, String projectKey) throws IOException {
        boolean billable = false;

        List<UserFavourites> favorites =  hourRegDao.getUserFavourites(user);
        for (UserFavourites userFavorite : favorites) {
            if (StringUtils.equals(projectKey,
                    UtilityMethods.getProjectKey(userFavorite.getProjectNumber(),userFavorite.getActivityCode()))) {
                billable = userFavorite.getBillable() == 1;
                break;
            }
        }

        return billable;
    }

    private DateTime getDate(String date) {
        return RegistrationConstants.dateTimeFormatter.parseDateTime(date);
    }

    private void fillInSuccessResponse(HttpServletResponse response, String contentType, String text) throws IOException {
        response.setContentType(contentType);
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().print(text);
    }

    private void fillInSuccessResponse(HttpServletResponse response) throws IOException {
        fillInSuccessResponse(response, TEXT_PLAIN, RegistrationConstants.TEXT_SUCCESS);
    }
}
