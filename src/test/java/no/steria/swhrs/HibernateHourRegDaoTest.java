package no.steria.swhrs;

import java.util.List;
import static org.fest.assertions.Assertions.assertThat;
import javax.naming.NamingException;
import javax.swing.plaf.synth.SynthOptionPaneUI;

import no.steria.swhrs.HibernateHourRegDao;
import no.steria.swhrs.HourRegDao;

import org.eclipse.jetty.plus.jndi.EnvEntry;
import org.hibernate.cfg.Environment;
import org.hsqldb.jdbc.JDBCDataSource;
import org.joda.time.LocalDate;
import org.junit.Test;

public class HibernateHourRegDaoTest {

	@Test
	public void shouldAndGetSaveHours() throws Exception {
		HourRegDao personDao = createHourRegDao();
		personDao.beginTransaction();
		HourRegistration dummyHourReg = HourRegistration.createRegistration(1, 10, LocalDate.now(), 7.5);
		personDao.saveHours(dummyHourReg);
		List<HourRegistration> regs = personDao.getHours(1, LocalDate.now());
		assertThat(regs).contains(dummyHourReg);
	}
	
	private HourRegDao createHourRegDao() throws NamingException{
		JDBCDataSource jdbcDataSource = new JDBCDataSource();
		jdbcDataSource.setDatabase("jdbc:hsqldb:mem:testDb");
		jdbcDataSource.setUser("sa");
		jdbcDataSource.setPassword("");
		System.setProperty(Environment.HBM2DDL_AUTO, "create");
		new EnvEntry("jdbc/personDaoTest", jdbcDataSource);
		return new HibernateHourRegDao("jdbc/personDaoTest");
	}

}
