 package no.steria.swhrs;

import static org.fest.assertions.Assertions.assertThat;

import java.util.List;

import javax.naming.NamingException;

import org.eclipse.jetty.plus.jndi.EnvEntry;
import org.hibernate.cfg.Environment;
import org.hsqldb.jdbc.JDBCDataSource;
import org.joda.time.LocalDate;
import org.junit.Test;

public class HibernateHourRegDaoTest {
	
	private HourRegDao createHourRegDao() throws NamingException{
		JDBCDataSource jdbcDataSource = new JDBCDataSource();
		jdbcDataSource.setDatabase("jdbc:hsqldb:mem:testDb");
		jdbcDataSource.setUser("sa");
		jdbcDataSource.setPassword("");
		System.setProperty(Environment.HBM2DDL_AUTO, "create");
		new EnvEntry("jdbc/personDaoTest", jdbcDataSource);
		return new HibernateHourRegDao("jdbc/personDaoTest");
	}

	@Test
	public void shouldGetHoursFromDate() throws Exception {
		HourRegDao personDao = createHourRegDao();
		personDao.beginTransaction();
		HourRegistration dummyHourReg = HourRegistration.createRegistration(1, 1337, LocalDate.parse("2012-07-03"), 3.0);
		HourRegistration dummyHourRegNow = HourRegistration.createRegistration(1, 10, LocalDate.parse("2012-07-11"), 4.5);
		//saves the date we want to get
		personDao.saveHours(dummyHourReg);
		//saves the date we don't want to get
		personDao.saveHours(dummyHourRegNow);
		
		List<HourRegistration> regs = personDao.getAllHoursForDate("1", "LocalDate.parse(2012-07-03)");

		assertThat(regs.size()).isEqualTo(1);
		//check if the HourRegistration object has correct data
		assertThat(LocalDate.parse("2012-07-03").toString()).isEqualTo(regs.get(0).getDate().toString());
		assertThat(regs.get(0).getProjectnumber()).isEqualTo(1337);
		assertThat(regs.get(0).getPersonId()).isEqualTo(1);
		assertThat(regs.get(0).getHours()).isEqualTo(3.0);
		
		personDao.endTransaction(true);
	}
	
	
	@Test
	public void shouldApproveLogin() throws Exception {
		
	}
	
}
