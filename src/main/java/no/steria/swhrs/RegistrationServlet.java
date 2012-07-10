package no.steria.swhrs;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.LocalDate;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class RegistrationServlet extends HttpServlet{
	
	private static final long serialVersionUID = -1090477374982937503L;
	private HibernateHourRegDao db;
		
	public void init() throws ServletException {
		db = new HibernateHourRegDao(Parametere.DB_JNDI);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();
		writer.append("<html><body>Dette er registrationservletenPP</body></html>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		
		if (req.getRequestURL().toString().contains(("hours/registration"))) {
			int personId = Integer.parseInt(req.getParameter("personId"));
			String favourite = req.getParameter("fav");
			String pNr = req.getParameter("projectNr");
			int projectNr = Integer.parseInt(req.getParameter("projectNr").trim());
			double hours = Double.parseDouble(req.getParameter("hours"));
			String lunch = req.getParameter("lunch");
			String date = req.getParameter("date");
			
			System.out.println(projectNr + "," +hours+ ", "+lunch+", "+date);
			
			//saveRegToDatabase(personId, projectNr, LocalDate.now(), hours);
		}
		
		if (req.getRequestURL().toString().contains(("hours/login"))) {
			System.out.println("Kom hit");
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			System.out.println("Username:" +username+" Password: "+password);
			int autoLoginExpire = (60*60*24);
			//Byttes ut når database er oppe
			//if(db.validateUser(username, password) == true){
			if(username.equals("steria") && password.equals("123")){
				Cookie loginCookie = new Cookie("USERNAME", username);
				loginCookie.setMaxAge(autoLoginExpire);
				resp.setContentType("text/plain");
				PrintWriter writer = resp.getWriter();
				writer.append("Login ok");
			}else{
				resp.setStatus(403);
				System.out.println("FAIL");
			}
		}
		
 	}

	private void saveRegToDatabase(int personId, int projectNr, LocalDate date, double hours) {
		HourRegistration reg = HourRegistration.createRegistration(personId, projectNr, LocalDate.now(), hours);
		db.saveHours(reg);
		System.out.println("Saving registration with data: " + projectNr + "," +hours+ ", " +LocalDate.now());
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		db.beginTransaction();
		super.service(req, resp);
		db.endTransaction(true);
		//TODO sleng på en finally her så den ender transaksjonen hvis servleten kræsjer
	}

}
