package no.steria.swhrs;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.joda.time.LocalDate;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author xsts
 *
 */
public class RegistrationServlet extends HttpServlet{
	private static final long serialVersionUID = -1090477374982937503L;
    private static final Logger logger = LoggerFactory.getLogger(RegistrationServlet.class);

	private HourRegDao db;
	private String username = null;

	LocalDate date = LocalDate.now();

    public void init() throws ServletException {
		if ("true".equals(System.getProperty("swhrs.useSqlServer"))) {
			try {
				db = new MSSQLHourRegDao((DataSource) new InitialContext().lookup("jdbc/registerHoursDS"));
			} catch (NamingException e) {
				throw new ServletException(e);
			}
		} else {
			//Create a memory database
		}
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		

		if(req.getRequestURL().toString().contains(("hours/daylist"))){
			getDaylistResponseAsJSON(req, resp);
		} else if(req.getRequestURL().toString().contains(("hours/favourite"))){
			getFavouritesResponse(req, resp);
		} else if (req.getRequestURL().toString().contains(("hours/registration"))) {
			addHourRegistationToDatabase(req);
		} else if (req.getRequestURL().toString().contains(("hours/login"))) {
			loginUserAndSetCookies(req, resp);
		} else if(req.getRequestURL().toString().contains(("hours/week"))){
			getWeeklistResponseAsJSON(req, resp);
		} else if(req.getRequestURL().toString().contains(("hours/deleteRegistration"))){
			deleteHourRegistrationInDatabase(req, resp);
		} else if(req.getRequestURL().toString().contains(("hours/setUsername"))){
			setUsername(req, resp);
		} else if(req.getRequestURL().toString().contains(("hours/searchFavourites"))){
			searchFavourites(req, resp);
		} else if(req.getRequestURL().toString().contains(("hours/addFavourites"))){
			addFavourites(req);
		} else if(req.getRequestURL().toString().contains(("hours/updatePeriod"))){
			updatePeriod(req, resp);
		} else if(req.getRequestURL().toString().contains(("hours/deleteFavourite"))){
			deleteFavourite(req);
		} else if(req.getRequestURL().toString().contains(("hours/updateRegistration"))){
			updateRegistration(req);
		}
 	}


	/**
	 * This method updates an hour registration with a new description for time entry (comment) and new hours number
	 * @param req
	 */
	private void updateRegistration(HttpServletRequest req) {
		int taskNumber = Integer.parseInt(req.getParameter("taskNumber"));
		double hours = Double.parseDouble(req.getParameter("hours"));
		String description = req.getParameter("description");
		
		db.updateRegistration(taskNumber, hours, description);
	}


	/**
	 * This method removes a project from the users favourite list in the database
	 * @param req The HTTP request contains project number and activity code
	 */
	private void deleteFavourite(HttpServletRequest req){
		String projectNumber = req.getParameter("projectNumber");
		String activityCode = req.getParameter("activityCode");
		db.deleteFavourite(username, projectNumber, activityCode);
	}


	/**
	 * This method adds new projects to the users favourite list
	 * @param req The HTTP request contains project number and activity code
	 */
	private void addFavourites(HttpServletRequest req) {
		String projectNumber = req.getParameter("projectNumber");
		String activityCode = req.getParameter("activityCode");
		db.addFavourites(username, projectNumber, activityCode);
		
	}


	/**
	 * This method will search through Projects with the search input, and 
	 * return a JSON string with project number, activity code and description
	 * @param req The HTTP request contains a search input which is used to search through
	 * projects in the database
	 * @param resp THE HTTP response will return a JSON String with project number, activity code, and description.
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	private void searchFavourites(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		resp.setContentType("application/json");
		String searchInput = req.getParameter("search");
		List<Projects> project = db.searchProjects(searchInput);
		int counter = 0;
		JSONObject projectJson = new JSONObject();
		for(Projects po: project){
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("projectnumber", po.getProjectNumber());
			map.put("activitycode", po.getActivityCode());
			map.put("description", po.getDescription());
			counter++;
			projectJson.put(counter, map);
		}
		PrintWriter writer = resp.getWriter();
		String jsonText = projectJson.toString();
		writer.append(jsonText);
	}


	private void setUsername(HttpServletRequest req, HttpServletResponse resp) {
		String loginUsername = req.getParameter("UN");
		username = loginUsername;
	}


	/**
	 * This method will delete an hour registration from the database
	 * @param req The HTTP request contains taskNumber which is the unique identifier for each registration in the database
	 * @param resp The HTTP response will return plain text with either 
	 * 			   "ERROR: Already submitted" if the deleteHourRegistration returns false, meaning that the registration is locked
	 * 			or "success" if the deletion was successful
	 * @throws IOException
	 */
	private void deleteHourRegistrationInDatabase(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		String taskNumber = req.getParameter("taskNumber");
		boolean success = db.deleteHourRegistration(taskNumber);
		resp.setContentType("text/plain");
		if (!success) {
			resp.getWriter().append("ERROR: Already submitted");
		}else{
			resp.getWriter().append("success");
		}
	}
	
	
	/**
	 * This method will update a users period based on the HTTP request, it will either submit the period if the request is 1 
	 * or reopen if the request is 0.
	 * @param req The HTTP request contains option which decides whether to submit(1) or reopen(0) period
	 * @param resp The HTTP response returns in plain text if the period is submitted or reopened
	 * @throws IOException
	 */
	private void updatePeriod(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		DatePeriod period = db.getPeriod(username, date.toString());
		int option = Integer.parseInt(req.getParameter("option"));
		db.updatePeriod(username, option, period.getFromDate(), period.getToDate());
		resp.setContentType("text/plain");
		if(option == 1){
			resp.getWriter().append("Period is submitted");
		}else{
			resp.getWriter().append("Period is reopened");
		}
		
	}
	
	
	/**
	 * This method returns a HTTP request containing JSON data for a period
	 * @param req The HTTP request contains the week parameter which contains strings of either "thisWeek", "prevWeek" or "nextWeek"
	 * @param resp The HTTP response will return a json object containing data about weekdays, dates and hours for the week requested
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	private void getWeeklistResponseAsJSON(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		String week = req.getParameter("week");		
		resp.setContentType("application/text");
		DatePeriod period2 = db.getPeriod(username, date.toString());
		
		LocalDate localFromDate = new LocalDate(period2.getFromDate().split(" ")[0]);
		LocalDate localToDate = new LocalDate(period2.getToDate().split(" ")[0]);
		
		if(week.equals("nextWeek")) date = localToDate.plusDays(1);
		if(week.equals("prevWeek")) date = localFromDate.minusDays(1);
		
		DatePeriod period = db.getPeriod(username, date.toString());
		
		LocalDate localFromDate2 = new LocalDate(period.getFromDate().split(" ")[0]);
		LocalDate localToDate2 = new LocalDate(period.getToDate().split(" ")[0]);
		
		ArrayList<String> dateArray = new ArrayList<String>();
		while(localFromDate2.compareTo(localToDate2) <= 0  ){
			dateArray.add(localFromDate2.toString()+":"+localFromDate2.getDayOfWeek());
			localFromDate2 = localFromDate2.plusDays(1);
		}
		
		List<WeekRegistration> weeklist = db.getWeekList(username, period.getFromDate(), period.getToDate());
		String weekDescription = period.getDescription();
		
		//This will get the norm time for each day of the week and should be added together with the weekHours.
		//List<NormTime> norm = db.getNormTime(username);
		
		
		JSONObject obj = new JSONObject();
		//JSONObject weekJson = new JSONObject();
		for(int i=0; i<dateArray.size(); i++){
			String dateArr = dateArray.get(i).toString().split(":")[0];
			String dayOfWeek = dateArray.get(i).toString().split(":")[1];
			boolean found = false;
			for(WeekRegistration wr2: weeklist){
				if(wr2.getDate().split(" ")[0].equals(dateArr)){
					@SuppressWarnings("rawtypes")
					List list = new LinkedList();
					list.add(dayOfWeek);
					list.add(wr2.getHours());
					list.add(wr2.getApproved());
					obj.put(wr2.getDate().split(" ")[0], list);
					found = true;
					break;
				}
			}
			
			if (!found) {
				@SuppressWarnings("rawtypes")
				List list = new LinkedList();
				list.add(dayOfWeek);
				list.add(0);
				obj.put(dateArr, list);
			}
			/*
			for(WeekRegistration wr: weeklist){
				if(wr.getDate().split(" ")[0].equals(dateArr)){
					HashMap map = new HashMap();
					map.put("dayOfWeek", dayOfWeek);
					map.put("hours", wr.getHours());
					weekJson.put(wr.getDate().split(" ")[0], map);
				}
			}
			
			if (!found) {
				HashMap map = new HashMap();
				map.put("dayOfWeek", dayOfWeek);
				map.put("hours", 0);
				weekJson.put(dateArr, map);
			}*/
			
		}
		
		obj.put("weekNumber", weekDescription);
		obj.put("dateHdr", date.getDayOfWeek()+" "+date.toString());
		//weekJson.put("weekNumber", weekDescription);
		//weekJson.put("dateHdr", date.getDayOfWeek()+" "+date.toString());
		resp.setContentType("text/json");
		PrintWriter writer = resp.getWriter();
		String jsonText = obj.toJSONString();
		writer.append(jsonText);
	}


	/**
	 * Sets login cookies
	 * @param req The HTTP request containing username and password
	 * @param resp The HTTP request will return plain text of either "Login approved" if successful or set status to 403 if login failed
	 * @throws IOException
	 */
	private void loginUserAndSetCookies(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		username = req.getParameter("username").toUpperCase();
		String password = req.getParameter("password");
		int autoLoginExpire = (60*60*24);
		if(db.validateUser(username, password) == true){
			Cookie loginCookie = new Cookie("USERNAME", username);
			loginCookie.setMaxAge(autoLoginExpire);
			resp.setContentType("text/plain");
			PrintWriter writer = resp.getWriter();
			writer.append("Login approved");
		}else{
			resp.setStatus(403);
			logger.debug("Access denied");
		}
	}


	/**
	 * Adds an hour registration the database
	 * @param req The HTTP request containing parameters of "ProjectNr", "hours", "lunchNumber" and "description"
	 */
	private void addHourRegistationToDatabase(HttpServletRequest req) {
		String projectNumber = req.getParameter("projectNr");
		String activityCode = req.getParameter("activityCode");
		double hours = Double.parseDouble(req.getParameter("hours"));
		String lunchNumber = req.getParameter("lunchNumber");
		String description = req.getParameter("description");
		int billable = Integer.parseInt(req.getParameter("billable"));
		int internal = Integer.parseInt(req.getParameter("internalproject"));
		
		
		db.addHourRegistrations(projectNumber, activityCode, username, "", date.toString(), hours, description, 0, 0, billable, 10101, internal, 0, "HRA", "", projectNumber, "", 0, 0, "", "", "2012-05-30", "HRA", "", 0, 0);
		if(lunchNumber.equals("1")){
			db.addHourRegistrations("LUNSJ", "LU", username, "", date.toString(), 0.5, "Lunsj", 0, 0, 1, 10101, 0, 0, "HRA", "", lunchNumber, "", 0, 0, "", "", "2012-05-30", "HRA", "", 0, 0);
		}
	}


	/**
	 * Returns a HTTP response containing a JSON object of the users favourites stored in the database
	 * @param resp HTTP request containing a JSON object of the users favourites
	 * @throws IOException
	 */
	private void getFavouritesResponse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("application/json");
		List<UserFavourites> userList = db.getUserFavourites(username);
		JSONObject json = createJsonObjectFromFavourites(userList);

		PrintWriter writer = resp.getWriter();
		String jsonText = json.toString();
		writer.append(jsonText);
	}


	/**
	 * Returns a HTTP response containing all hour registrations for a certain day stored in a JSON object
	 * @param req The HTTP request containing a parameter "day" containing either "today", "prevDay"
	 * @param resp The HTTP response contains a json object with all data about a registration needed to display it in the app
	 * @throws IOException
	 */
	private void getDaylistResponseAsJSON(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String newDay = req.getParameter("day");
		
		resp.setContentType("application/json");
		if(newDay.equals("prevDay")) date = date.minusDays(1);
		else if(newDay.equals("nextDay")) date = date.plusDays(1);
		else if(newDay.equals("today")){
			logger.info("getting todays daylist from server");
		}else{
			LocalDate weekDate = new LocalDate(newDay);
			date = weekDate;
		}
		List<HourRegistration> hrlist = db.getAllHoursForDate(username, date.toString());

		String stringDate = date.toString();
		JSONObject json = createJsonObjectFromHours(hrlist, date.getDayOfWeek()+" "+stringDate);

		PrintWriter writer = resp.getWriter();
		String jsonText = json.toString();
		writer.append(jsonText);
	}

	/**
	 * Helper method to make a JSON object from a list of HourRegistrations
	 * @param hrlist the list of HourRegistration objects
	 * @param stringDate the date of the registrations
	 * @return A json object of the format: key: taskNumber values: [description, hours]
	 */
	@SuppressWarnings("unchecked")
	private JSONObject createJsonObjectFromHours(List<HourRegistration> hrlist, String stringDate) {
		JSONObject json = new JSONObject();
		for (HourRegistration hr: hrlist) {
			@SuppressWarnings("rawtypes")
			HashMap map = new HashMap();
			map.put("projectnumber", hr.getProjectnumber());
			map.put("activitycode", hr.getActivityCode());
			map.put("description", hr.getDescription());
			map.put("approved", hr.isApproved());
			map.put("submitted", hr.isSubmitted());
			map.put("hours", hr.getHours());
			json.put(hr.getTaskNumber(), map);
		}
		json.put("date", stringDate);
		return json;
	}
	
	/**
	 * Helper method to create JSON object from a list of UserFavourites objects
	 * @param userList The list containing user favourite objects stored in the database
	 * @return JSON object with the format {"projectNumber":{"internalproject":value,"activitycode":value,"description": value,"projectname": value,"customername": value,"billable": value}
	 *         Keys are generated from 1 and up so it's easy to sort later, they contain a map with the rest of the values
	 */
	@SuppressWarnings("unchecked")
	private JSONObject createJsonObjectFromFavourites(
			List<UserFavourites> userList) {
		JSONObject json = new JSONObject();
		int counter = 0;
		for (UserFavourites uf: userList) {
			@SuppressWarnings("rawtypes")
			HashMap map = new HashMap();
			map.put("projectnumber", uf.getProjectNumber());
			map.put("activitycode", uf.getActivityCode());
			map.put("description", uf.getDescription());
			map.put("billable", uf.getBillable());
			map.put("projectname", uf.getProjectName());
			map.put("customername", uf.getCustomer());
			map.put("internalproject", uf.getInternalProject());
			
			json.put(counter++, map);
			
		}
		return json;
		
	}


	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			db.beginTransaction();
			super.service(req, resp);
		} finally {
			db.endTransaction();
		}
	}

}
