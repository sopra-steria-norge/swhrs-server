package no.steria.swhrs;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import net.sourceforge.jtds.jdbc.Driver;
import net.sourceforge.jtds.jdbcx.JtdsDataSource;

import org.junit.BeforeClass;
import org.junit.Test;

public class MSSQLHourRegDaoTest {
	
	private static MSSQLHourRegDao hourRegDao;
	
	@BeforeClass
	public static void createRegDao() throws Exception{
		hourRegDao = createHourRegDao();
	}
	
	private static MSSQLHourRegDao createHourRegDao() throws SQLException, IOException{
		InputStream resourceAsStream = MSSQLHourRegDao.class.getResourceAsStream("/config.properties");
		Properties properties = System.getProperties();
		properties.load(resourceAsStream);
		String serverAddress = System.getProperty("swhrs.dbServer");
		String databaseName = System.getProperty("swhrs.dbName");
		String user = System.getProperty("swhrs.dbUsername");
		String password = System.getProperty("swhrs.dbPassword");
		
		int port = 1433;

		System.out.println("Connecting to '" + serverAddress + "' db '" + databaseName + "' port '" + port + "' user '" + user + "'");

		JtdsDataSource datasource = new JtdsDataSource();
		datasource.setServerType(Driver.SQLSERVER);
		datasource.setServerName(serverAddress);
		datasource.setPortNumber(port);
		datasource.setDatabaseName(databaseName);
		datasource.setUser(user);
		datasource.setPassword(password);
		return new MSSQLHourRegDao(datasource);
	}

//	@Test
//	public void shouldConnectToDatabase() throws Exception {
//		hourRegDao.beginTransaction();
//		List<HourRegistration> allHoursForDate = hourRegDao.getAllHoursForDate(1, new LocalDate());
//		hourRegDao.endTransaction(false);
//		
//		assertThat(allHoursForDate).hasSize(4);
//	}
//	
	@Test
	public void shouldValidateUser() throws Exception {
		String username = "AK";
		String password = "bingo521";
		hourRegDao.beginTransaction();
		boolean validate = hourRegDao.validateUser(username, password);
		hourRegDao.endTransaction(false);
		assertTrue(validate);
	}
	
	@Test
	public void shouldGetRegistrations() throws Exception {
		hourRegDao.beginTransaction();
		List<HourRegistration> allHoursForDate = hourRegDao.getAllHoursForDate("AK", "2012-05-30 00:00:00.0");
		hourRegDao.endTransaction(false);
		assertThat(allHoursForDate).hasSize(5);
		
	}
	
	@Test
	public void shouldGetUserFavourites() throws Exception {
		hourRegDao.beginTransaction();
		List<UserFavourites> userFavourites = hourRegDao.getUserFavouirtes("AK");
		hourRegDao.endTransaction(false);
		assertThat(userFavourites).hasSize(25);
		
	}
	
	@Test
	public void shouldSearchForProjects() throws Exception {
		hourRegDao.beginTransaction();
		List<Projects> projects = hourRegDao.getProjects("LARM");
		hourRegDao.endTransaction(false);
		assertThat(projects).hasSize(27);
	}
	
	@Test
	public void shouldGetWeekRegistrations() throws Exception {
		hourRegDao.beginTransaction();
		List<WeekRegistration> weeklist = hourRegDao.getWeekList("AK", "2012-06-10 00:00:00.0", "2012-06-15 00:00:00.0");
		hourRegDao.endTransaction(false);
		assertThat(weeklist).hasSize(14);
	}
	
	@Test
	public void shouldGetPeriod() throws Exception {
		hourRegDao.beginTransaction();
		DatePeriod period = hourRegDao.getPeriod("AK","2012-11-07");
		hourRegDao.endTransaction(false);
		assertThat(period.getFromDate()).contains("2012-11-05");
	}
	
	@Test
	public void shouldDeleteRegistration() throws Exception {
		hourRegDao.beginTransaction();
		boolean deleted = hourRegDao.deleteHourRegistration("2101620");
		hourRegDao.endTransaction(false);
		assertTrue(deleted);
	}

}
