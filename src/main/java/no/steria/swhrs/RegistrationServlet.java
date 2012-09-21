package no.steria.swhrs;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * @author xsts
 * @author chrm@steria.no
 */
public class RegistrationServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(JettyServer.class);
	private static final long serialVersionUID = -1090477374982937503L;
    private static final String APPLICATION_JSON = "application/json";
    private static final String TEXT_PLAIN = "text/plain";

	private HourRegDao db;

    @Override
    public void init() throws ServletException {
        try {
            db = MSSQLHourRegDao.createInstance();
        } catch (NamingException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getRequestURL().toString().contains("hours/daylist")){
            getDayListResponseAsJSON(req, resp);
        } else if(req.getRequestURL().toString().contains("hours/favourite")){
            getFavorites(req, resp);
        } else if(req.getRequestURL().toString().contains("hours/week")){
            getWeekDetails(req, resp);
        } else if(req.getRequestURL().toString().contains("hours/searchFavourites")){
            searchFavourites(req, resp);
        }
    }

    @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)	throws ServletException, IOException {
        if (req.getRequestURL().toString().contains("hours/registration")) {
            addHourRegistration(req, resp);
        } else if(req.getRequestURL().toString().contains("hours/deleteRegistration")){
            deleteHourRegistration(req, resp);
		} else if(req.getRequestURL().toString().contains("hours/addFavourites")){
			addFavourites(req, resp);
		} else if(req.getRequestURL().toString().contains("hours/submitPeriod")){
			submitPeriod(req, resp);
		} else if(req.getRequestURL().toString().contains("hours/reopenPeriod")){
            reopenPeriod(req, resp);
        } else if(req.getRequestURL().toString().contains("hours/deleteFavourite")){
			deleteFavourite(req, resp);
		} else if(req.getRequestURL().toString().contains("hours/updateRegistration")){
			updateHourRegistration(req, resp);
		}
 	}

    /**
     * Adds an hour registration the database
     * @param request The HTTP request containing parameters of "ProjectNr", "hours", "lunchNumber" and "description"
     */
    private void addHourRegistration(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = getUserAttribute(request);
        String username = user.getUsername();
        String projectNumber = request.getParameter(RegistrationConstants.PROJECT_NUMBER);
        String activityCode = request.getParameter(RegistrationConstants.ACTIVITY_CODE);
        double hours = Double.parseDouble(request.getParameter(RegistrationConstants.HOURS));
        String description = request.getParameter(RegistrationConstants.DESCRIPTION);
        boolean billable = resolveBillable(request.getParameter(RegistrationConstants.BILLABLE));
        DateTime date = getDate(request.getParameter(RegistrationConstants.DATE));
        String workType = request.getParameter(RegistrationConstants.WORK_TYPE);

        db.addHourRegistrations(username, username, projectNumber, activityCode, date, hours, billable,
                workType, description, false);
        fillInSuccessResponse(response);
    }

    /**
	 * This method updates an hour registration with a new description for time entry (comment) and new hours number
	 * @param request HttpServletRequest
	 */
	private void updateHourRegistration(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = getUserAttribute(request);
        String userId = user.getUsername();
		String taskNumber = request.getParameter(RegistrationConstants.TASK_NUMBER);
		double hours = Double.parseDouble(request.getParameter(RegistrationConstants.HOURS));
		String description = request.getParameter(RegistrationConstants.DESCRIPTION);
        String activity = request.getParameter(RegistrationConstants.ACTIVITY_CODE);
        String projectNumber = request.getParameter(RegistrationConstants.PROJECT_NUMBER);
        DateTime date = getDate(request.getParameter(RegistrationConstants.DATE));
        boolean billable = resolveBillable(request.getParameter(RegistrationConstants.BILLABLE));
        String workType = request.getParameter(RegistrationConstants.WORK_TYPE);

        db.updateHourRegistration(userId, taskNumber, projectNumber, activity, date, hours, billable, workType, description);
        fillInSuccessResponse(response);
	}

    /**
     * This method will delete an hour registration from the database
     * @param request The HTTP request contains taskNumber which is the unique identifier for each registration in the database
     * @param response The HTTP response will return plain text with either
     * 			   "ERROR: Already submitted" if the deleteHourRegistration returns false, meaning that the registration is locked
     * 			or "success" if the deletion was successful
     * @throws IOException
     */
    private void deleteHourRegistration(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = getUserAttribute(request);
        String username = user.getUsername();
        String taskNumber = request.getParameter(RegistrationConstants.TASK_NUMBER);
        db.deleteHourRegistration(username, taskNumber);
        fillInSuccessResponse(response);
    }

	/**
	 * This method removes a project from the users favourite list in the database
	 * @param request The HTTP request contains project number and activity code
	 */
	private void deleteFavourite(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = getUserAttribute(request);
        String projectNumber = request.getParameter(RegistrationConstants.PROJECT_NUMBER);
		String activityCode = request.getParameter(RegistrationConstants.ACTIVITY_CODE);
		db.deleteFavourite(user.getUsername(), projectNumber, activityCode);
        fillInSuccessResponse(response);
	}

	/**
	 * This method adds new projects to the users favourite list
	 * @param request The HTTP request contains project number and activity code
	 */
	private void addFavourites(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = getUserAttribute(request);
        String projectNumber = request.getParameter(RegistrationConstants.PROJECT_NUMBER);
        String activityCode = request.getParameter(RegistrationConstants.ACTIVITY_CODE);
		db.addFavourites(user.getUsername(), projectNumber, activityCode);
        fillInSuccessResponse(response);
	}

    /**
     * Returns a HTTP response containing a JSON object of the users favourites stored in the database
     *
     * @param response HTTP request containing a JSON object of the users favourites
     * @throws IOException
     */
    private void getFavorites(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            User user = getUserAttribute(request);
            List<UserFavourites> userList = db.getUserFavourites(user.getUsername());
            fillInSuccessResponse(response, APPLICATION_JSON, JSONBuilder.createFromFavourites(userList).toString());
        } catch (Throwable t) {
            logger.debug(request.toString());
            logger.debug(response.toString());
            t.printStackTrace();
            response.setStatus(500);
            response.getWriter().append(t.getMessage());
        }
    }

    /**
	 * This method will search through ProjectDetail with the search input, and
	 * return a JSON string with project number, activity code and description
	 * @param request The HTTP request contains a search input which is used to search through
	 * projects in the database
	 * @param response THE HTTP response will return a JSON String with project number, activity code, and description.
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	private void searchFavourites(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String searchInput = request.getParameter("search");
		List<ProjectDetail> project = db.searchProjects(searchInput);
        fillInSuccessResponse(response, APPLICATION_JSON, JSONBuilder.createProjects(project).toString());
	}
	
	/**
	 * This method will submit a period entry - start date of the period needs to be passed as an argument.
	 * @param request The HTTP request
	 * @param response The HTTP response
	 * @throws IOException
	 */
	private void submitPeriod(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = getUserAttribute(request).getUsername();
        DateTime startDate = getDate(request.getParameter(RegistrationConstants.DATE));
        db.submitHours(username, username, startDate);
        fillInSuccessResponse(response);
    }

    /**
     * This method will attempt to reopen a period entry - start date of the period needs to be passed as an argument.
     * @param request The HTTP request
     * @param response The HTTP response
     * @throws IOException
     */
    private void reopenPeriod(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = getUserAttribute(request).getUsername();
        DateTime startDate = getDate(request.getParameter(RegistrationConstants.DATE));
        db.reopenHours(username, username, startDate);
        fillInSuccessResponse(response);
    }
	
	/**
	 * This method returns a HTTP request containing JSON data for a period
	 * @param request The HTTP request contains the week parameter which contains strings of either "thisWeek", "prevWeek" or "nextWeek"
	 * @param response The HTTP response will return a json object containing data about weekdays, dates and hours for the week requested
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	private void getWeekDetails(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = getUserAttribute(request);
        String userId = user.getUsername();
        DateTime date = RegistrationConstants.dateTimeFormatter.parseDateTime(request.getParameter(RegistrationConstants.DATE));
        PeriodDetails periodDetails = db.getPeriodDetails(userId, date);
        WeekDetails weekDetails = db.getWeekList(userId, userId, "EMP", periodDetails.getStartDate());
        fillInSuccessResponse(response, APPLICATION_JSON, JSONBuilder.createFromWeekDetails(weekDetails).toString());
	}

	/**
	 * Returns a HTTP response containing all hour registrations for a certain day stored in a JSON object
	 * @param request The HTTP request containing a parameter "day" containing either "today", "prevDay"
	 * @param response The HTTP response contains a json object with all data about a registration needed to display it in the app
	 * @throws IOException
	 */
	private void getDayListResponseAsJSON(HttpServletRequest request, HttpServletResponse response)	throws IOException {
        User user = getUserAttribute(request);
        DateTime date = getDate(request.getParameter(RegistrationConstants.DATE));
		List<HourRegistration> hourRegistrationList = db.getAllHoursForDate(user.getUsername(), date);
        fillInSuccessResponse(response, APPLICATION_JSON, JSONBuilder.createFromHours(hourRegistrationList, date).toString());
	}

    private static User getUserAttribute(HttpServletRequest req) {
        return (User) req.getAttribute(RegistrationConstants.USER);
    }

    private boolean resolveBillable(String parameter) {
        return StringUtils.equals("1", parameter);
    }

    private DateTime getDate(String date) {
        return RegistrationConstants.dateTimeFormatter.parseDateTime(date);
    }

    private void fillInSuccessResponse(HttpServletResponse response, String contentType, String text) throws IOException {
        response.setContentType(contentType);
        response.setStatus(RegistrationConstants.HTTP_STATUS_CODE_SUCCESS);
        response.getWriter().append(text);
    }

    private void fillInSuccessResponse(HttpServletResponse response) throws IOException {
        fillInSuccessResponse(response, TEXT_PLAIN, RegistrationConstants.TEXT_SUCCESS);
    }
}
