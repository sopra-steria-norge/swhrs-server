package no.steria.swhrs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.steadystate.css.parser.ParseException;

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
		// TODO Auto-generated method stub

		resp.setContentType("application/json");
		PrintWriter writer = resp.getWriter();		
		System.out.println("Dette er registrationservleten POST");
		writer.append("<html><body>Dette er registrationservletenPOST</body></html>");
		
		
	     JSONObject jObj = new JSONObject();
	     try {
			JSONObject newObj = jObj.getJSONObject(req.getParameter("dat"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	}

}
