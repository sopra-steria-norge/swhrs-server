package no.steria.swhrs;

import javax.naming.NamingException;

import no.steria.swhrs.HibernatePersonDao;
import no.steria.swhrs.PersonDao;

import org.eclipse.jetty.plus.jndi.EnvEntry;
import org.hibernate.cfg.Environment;
import org.hsqldb.jdbc.JDBCDataSource;
import org.joda.time.LocalDate;
import org.junit.Test;

public class HibernatePersonDaoTest {

	@Test
	public void shouldSaveHours() throws Exception {
		PersonDao personDao = createPersonDao();
		personDao.beginTransaction();
		HourRegistration dummyHourReg = HourRegistration.createRegistration(new Long(1), 10, LocalDate.now(), 7.5);
		personDao.saveHours(dummyHourReg);
		//sjekk at timene har blitt lagret
	}
	

	@Test
	public void shouldGetHours() throws Exception {
		
	}
	
	private PersonDao createPersonDao() throws NamingException{
		JDBCDataSource jdbcDataSource = new JDBCDataSource();
		jdbcDataSource.setDatabase("jdbc:hsqldb:mem:testDb");
		jdbcDataSource.setUser("sa");
		jdbcDataSource.setPassword("");
		System.setProperty(Environment.HBM2DDL_AUTO, "create");
		new EnvEntry("jdbc/personDaoTest", jdbcDataSource);
		return new HibernatePersonDao("jdbc/personDaoTest");
	}

}
