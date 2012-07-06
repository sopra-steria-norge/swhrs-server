package no.steria.swhrs;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RegistrationServlet extends HttpServlet{
	
	private static final long serialVersionUID = -1090477374982937503L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();
		
		writer.append("<html><body>Dette er registrationservleten</body></html>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setContentType("application/json");
		PrintWriter writer = resp.getWriter();		
		System.out.println("Dette er registrationservleten POST");
		writer.append("<html><body>Dette er registrationservletenPOST</body></html>");
		
		String favourite = req.getParameter("fav");
		String hours = req.getParameter("hours");
		String lunch = req.getParameter("lunch");
		String date = req.getParameter("date");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		System.out.println(favourite+"," +hours+ ", "+lunch+", "+date+", "+username+", "+password);
		
		
 	}

}
