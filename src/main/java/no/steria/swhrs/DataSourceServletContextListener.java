package no.steria.swhrs;

import no.steria.swhrs.dao.HourRegDao;
import no.steria.swhrs.dao.MSSQLHourRegDao;

import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DataSourceServletContextListener implements ServletContextListener {

    public DataSourceServletContextListener() {
    }

    public void contextInitialized(ServletContextEvent sce) {
        try {
            HourRegDao hourRegDao = MSSQLHourRegDao.createInstance();
            sce.getServletContext().setAttribute("hourRegDao", hourRegDao);
        } catch (NamingException e) {
            sce.getServletContext().log(e.getMessage(), e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
