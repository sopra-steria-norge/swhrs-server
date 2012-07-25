package no.steria.swhrs;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.simple.JSONObject;


public class RegistrationServlet extends HttpServlet{
	
	private static final long serialVersionUID = -1090477374982937503L;
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
			db = new HibernateHourRegDao(Parameters.DB_JNDI);
		}
		
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		if (req.getRequestURL().toString().contains(("hours/projects"))) { 
			resp.setContentType("application/json");
			//TODO return a list of all projects.
		}
		
	}

	@SuppressWarnings("unchecked")
	private JSONObject createJsonObjectFromHours(List<HourRegistration> hrlist, String stringDate) {
		JSONObject json = new JSONObject();
		for (HourRegistration hr: hrlist) {
			//json.put(hr.getItem(), hr.getHours());
			json.put(hr.getItem()+": "+hr.getDescription(), hr.getHours());
		}
		json.put("date", stringDate);
		return json;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		if(req.getRequestURL().toString().contains(("hours/daylist"))){
			String newDay = req.getParameter("day");
			System.out.println(newDay);
			
			resp.setContentType("application/json");
			if(newDay.equals("prevDay")) date = date.minusDays(1);
			if(newDay.equals("nextDay")) date = date.plusDays(1);
			System.out.println(date);
			List<HourRegistration> hrlist = db.getAllHoursForDate(username, date.toString());

			String stringDate = date.toString();
			System.out.println(date.getDayOfWeek());
			JSONObject json = createJsonObjectFromHours(hrlist, date.getDayOfWeek()+" "+stringDate);

			PrintWriter writer = resp.getWriter();
			String jsonText = json.toString();
			writer.append(jsonText);
			
		}
		
		if(req.getRequestURL().toString().contains(("hours/favourite"))){
			resp.setContentType("application/json");
			List<UserFavourites> userList = db.getUserFavourites(username);
			JSONObject json = createJsonObjectFromFavourites(userList);

			PrintWriter writer = resp.getWriter();
			String jsonText = json.toString();
			System.out.println(jsonText);
			writer.append(jsonText);
		}
		
		
		if (req.getRequestURL().toString().contains(("hours/registration"))) {
			String projectNumber = req.getParameter("projectNr");
			double hours = Double.parseDouble(req.getParameter("hours"));
			String lunchNumber = req.getParameter("lunchNumber");
			String description = req.getParameter("description");
			
			db.addHourRegistrations(projectNumber, "2", username, "", date.toString(), hours, description, 0, 0, 1, 10101, 0, 0, "HRA", "", projectNumber, "", 0, 0, "", "", "2012-05-30", "HRA", "", 0, 0);
			System.out.println(lunchNumber);
			if(lunchNumber.equals("1")){
				db.addHourRegistrations(lunchNumber, "1", username, "", date.toString(), 0.5, "Lunsj", 0, 0, 1, 10101, 0, 0, "HRA", "", lunchNumber, "", 0, 0, "", "", "2012-05-30", "HRA", "", 0, 0);
			}
			System.out.println("Trying to save project: " + projectNumber);
		}
		
		if (req.getRequestURL().toString().contains(("hours/login"))) {
			username = req.getParameter("username");
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
				System.out.println("You dont fool me, fool");
			}
		}
		
		if(req.getRequestURL().toString().contains(("hours/week"))){
			String week = req.getParameter("week");		
			resp.setContentType("application/text");
			if(week.equals("nextWeek")) date = date.plusWeeks(1);
			if(week.equals("prevWeek")) date = date.minusWeeks(1);
			String weekString = ""+date.getWeekOfWeekyear();
			String dateFrom = date.toString();
			String dateTo = date.toString();
			
			DatePeriod period = db.getPeriod(username, date.toString());
			System.out.println("fromDate: "+period.getFromDate()+" toDate: "+period.getToDate()+" Description: "+period.getDescription());
			
			List<WeekRegistration> weeklist = db.getWeekList(username, period.getFromDate(), period.getToDate());
			JSONObject json = new JSONObject();
			int order = 0;
			String weekDescription = period.getDescription();
			for (WeekRegistration wr: weeklist) {
				order++;
				System.out.println("Date: "+wr.getDate()+" Hours: "+wr.getHours()+" Approved: "+wr.getApproved()); 
				json.put(wr.getDate(), order+":"+wr.getHours());
			}
			json.put("weekNumber", weekDescription);
			json.put("hoho", date.getDayOfWeek()+" "+date.toString());
			resp.setContentType("text/json");
			PrintWriter writer = resp.getWriter();
			String jsonText = json.toString();
			writer.append(jsonText);
		}
		
		if(req.getRequestURL().toString().contains(("hours/delete"))){
			String taskNumber = req.getParameter("projectID");
			System.out.println(taskNumber);
			boolean success = db.deleteHourRegistration(taskNumber);
			if (!success) {
				resp.setContentType("text/plain");
				resp.getWriter().append("ERROR: Already submitted");
			}
		}
		
		
 	}


	@SuppressWarnings("unchecked")
	private JSONObject createJsonObjectFromFavourites(
			List<UserFavourites> userList) {
		JSONObject json = new JSONObject();
		for (UserFavourites ul: userList) {
			//json.put(hr.getItem(), hr.getHours());
			json.put(ul.getProjectNumber(), ul.getActivityCode()+":"+ul.getDescription());
		}
		return json;
		
	}


	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		db.beginTransaction();
		super.service(req, resp);
		db.endTransaction(true);
		//TODO add a finally here so that it ends the transaction of the servlet crashes
	}

}
