package no.steria.swhrs;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
			json.put(hr.getItem()+":"+hr.getDescription(), hr.getHours());
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
			System.out.println("NEWDAY ="+newDay);
			
			resp.setContentType("application/json");
			if(newDay.equals("prevDay")) date = date.minusDays(1);
			else if(newDay.equals("nextDay")) date = date.plusDays(1);
			else if(newDay.equals("today")){
				System.out.println("nothing happens");
			}else{
				System.out.println("WEEKNAVIGATION");
				LocalDate weekDate = new LocalDate(newDay);
				date = weekDate;
				System.out.println("NEWWEEKDATE: "+date);
			}
			List<HourRegistration> hrlist = db.getAllHoursForDate(username, date.toString());

			String stringDate = date.toString();
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
			writer.append(jsonText);
		}
		
		
		if (req.getRequestURL().toString().contains(("hours/registration"))) {
			String projectNumber = req.getParameter("projectNr");
			double hours = Double.parseDouble(req.getParameter("hours"));
			String lunchNumber = req.getParameter("lunchNumber");
			String description = req.getParameter("description");
			
			db.addHourRegistrations(projectNumber, "2", username, "", date.toString(), hours, description, 0, 0, 1, 10101, 0, 0, "HRA", "", projectNumber, "", 0, 0, "", "", "2012-05-30", "HRA", "", 0, 0);
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
			
			
			System.out.println("fromDate: "+period.getFromDate()+" toDate: "+period.getToDate()+" Description: "+period.getDescription());
			
			List<WeekRegistration> weeklist = db.getWeekList(username, period.getFromDate(), period.getToDate());
			JSONObject json = new JSONObject();
			int order = 0;
			String weekDescription = period.getDescription();
			
			
			JSONObject obj = new JSONObject();
			for(int i=0; i<dateArray.size(); i++){
				String dateArr = dateArray.get(i).toString().split(":")[0];
				String dayOfWeek = dateArray.get(i).toString().split(":")[1];
				boolean found = false;
				for(WeekRegistration wr2: weeklist){
					if(wr2.getDate().split(" ")[0].equals(dateArr)){
						//json.put(dateArr, wr2.getHours()+":"+dayOfWeek);
						List list = new LinkedList();
						list.add(dayOfWeek);
						list.add(wr2.getHours());
						obj.put(wr2.getDate().split(" ")[0], list);
						found = true;
						break;
					}
				}
				if (!found) {
					List list = new LinkedList();
					list.add(dayOfWeek);
					list.add(0);
					obj.put(dateArr, list);
				}
				
			}
			
//			for (WeekRegistration wr: weeklist) {
//				order++;
//				System.out.println("Date: "+wr.getDate()+" Hours: "+wr.getHours()+" Approved: "+wr.getApproved()); 
//				json.put(wr.getDate(), order+":"+wr.getHours());
//			}
			obj.put("weekNumber", weekDescription);
			obj.put("dateHdr", date.getDayOfWeek()+" "+date.toString());
			//json.put("weekNumber", weekDescription);
			//json.put("hoho", date.getDayOfWeek()+" "+date.toString());
			resp.setContentType("text/json");
			PrintWriter writer = resp.getWriter();
			//String jsonText = json.toString();
			//writer.append(jsonText);
			String jsonText = obj.toJSONString();
			writer.append(jsonText);
			
		}
		
		if(req.getRequestURL().toString().contains(("hours/delete"))){
			String taskNumber = req.getParameter("projectID");
			System.out.println(taskNumber);
			boolean success = db.deleteHourRegistration(taskNumber);
			System.out.println(success);
			resp.setContentType("text/plain");
			if (!success) {
				resp.getWriter().append("ERROR: Already submitted");
			}else{
				resp.getWriter().append("success");
			}
		}
 	}

	@SuppressWarnings("unchecked")
	private JSONObject createJsonObjectFromFavourites(
			List<UserFavourites> userList) {
		JSONObject json = new JSONObject();
		for (UserFavourites ul: userList) {
			//json.put(hr.getItem(), hr.getHours());
			json.put(ul.getProjectNumber() + "<:>" + ul.getActivityCode(), ul.getDescription());
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
