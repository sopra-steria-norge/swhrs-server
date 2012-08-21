package no.steria.swhrs;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private HourRegDao hourRegDao;

    @Override
    public void init() throws ServletException {
        hourRegDao = MSSQLHourRegDao.createInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = hourRegDao.findUser(req.getParameter("username"), req.getParameter("password"));

        if (user != null && session != null) {
            session.setAttribute("user", user);
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Username and password was not recognized.");
        }
    }
}
