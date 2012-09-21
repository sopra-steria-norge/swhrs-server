package no.steria.swhrs;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Date: 21.09.12
 * Time: 10:41
 * All rights reserved Steria AS 2012
 *
 * @author chrm@steria.no
 */
public class TestServlet extends RegistrationServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)	throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
