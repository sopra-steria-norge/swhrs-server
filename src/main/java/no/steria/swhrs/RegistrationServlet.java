package no.steria.swhrs;

import java.io.IOException;
import java.io.PrintWriter;
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


public class RegistrationServlet extends HttpServlet{
	
	private static final long serialVersionUID = -1090477374982937503L;
	private HourRegDao db;
	
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
			System.out.println(hr.getProjectnumber());
			json.put(Integer.toString(hr.getProjectnumber()), hr.getHours());
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
			List<HourRegistration> hrlist = db.getAllHoursForDate("AK", "2012-05-30 00:00:00.0");
			
			String stringDate = date.toString();
			JSONObject json = createJsonObjectFromHours(hrlist, stringDate);

			PrintWriter writer = resp.getWriter();
			String jsonText = json.toString();
			writer.append(jsonText);
			
		}
		
		
		if (req.getRequestURL().toString().contains(("hours/registration"))) {
			int personId = Integer.parseInt(req.getParameter("personId"));
			String pNr = req.getParameter("projectNr");
			int projectNr = Integer.parseInt(req.getParameter("projectNr").trim());
			double hours = Double.parseDouble(req.getParameter("hours"));
			//req.getParameter("date");
			
			System.out.println("Trying to save project: " + pNr);
			//TODO currently hardcoded as LocalDate.now() change to get date parameter from javascript
			saveRegToDatabase(personId, projectNr, date, hours);
		}
		
		if (req.getRequestURL().toString().contains(("hours/login"))) {
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			String country = req.getParameter("country");
			System.out.println("Username: " +username+" Password: "+password+" Country: "+country);
			int autoLoginExpire = (60*60*24);
			//Change this when database is up
			//if(db.validateUser(username, password, country) == true){
			if(username.equals("steria") && password.equals("123") && country.equals("norway")){
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
			List<WeekRegistration> weeklist = db.getWeekList(weekString, dateFrom, dateTo);
			JSONObject json = new JSONObject();
			int order = 0;
			
			/*for (WeekRegistration wr: weeklist) {
				order++;
				json.put(wr.getDate(), order+":"+wr.getWeekHours());
			}*/
			json.put("weekNumber", date.getWeekOfWeekyear());
			json.put("hoho", ""+date);
			resp.setContentType("text/json");
			PrintWriter writer = resp.getWriter();
			String jsonText = json.toString();
			writer.append(jsonText);
		}
		
		if(req.getRequestURL().toString().contains(("hours/delete"))){
			String projectID = req.getParameter("projectID");
			System.out.println(projectID);
			db.deleteHourRegistration(projectID);
		}
		
 	}


	private void saveRegToDatabase(int personId, int projectNr, LocalDate date, double hours) {
		HourRegistration reg = HourRegistration.createRegistration(personId, projectNr, date, hours);
		System.out.println("Saving registration with data: " + projectNr + "," +hours+ ", " +date);
		db.saveHours(reg);
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
