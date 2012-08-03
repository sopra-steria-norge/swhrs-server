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
import org.junit.Ignore;
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

	
	@Test
	public void shouldValidateUser() throws Exception {
		String username = "AK";
		String password = "password";
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
		List<UserFavourites> userFavourites = hourRegDao.getUserFavourites("SOLJ");
		hourRegDao.endTransaction(false);
		assertThat(userFavourites).hasSize(8);
	}
	
	
	@Test
	public void shouldSearchForProjects() throws Exception {
		hourRegDao.beginTransaction();
		List<Projects> projects = hourRegDao.searchProjects("LARM");
		hourRegDao.endTransaction(false);
		assertThat(projects).hasSize(27);
	}
	

	@Test
	public void shouldGetWeekRegistrations() throws Exception {
		hourRegDao.beginTransaction();
		List<WeekRegistration> weeklist = hourRegDao.getWeekList("SOLJ", "2012-05-01 00:00:00.0", "2012-05-10 00:00:00.0");
		hourRegDao.endTransaction(false);
		assertThat(weeklist).hasSize(7);
	}
	
	
	@Test
	public void shouldGetPeriod() throws Exception {
		hourRegDao.beginTransaction();
		DatePeriod period = hourRegDao.getPeriod("AK","2012-11-07");
		hourRegDao.endTransaction(false);
		assertThat(period.getFromDate()).contains("2012-11-05");
	}
	
	@Ignore
	@Test
	public void shouldDeleteRegistration() throws Exception {
		hourRegDao.beginTransaction();
		boolean deleted = hourRegDao.deleteHourRegistration("2101620");
		hourRegDao.endTransaction(false);
		assertTrue(deleted);
	}
	
	@Ignore
	@Test
	public void shouldAddFavourites() throws Exception {
		hourRegDao.beginTransaction();
		boolean addFavourite = hourRegDao.addFavourites("IHH", "1095754", "7");
		hourRegDao.endTransaction(false);
		
		assertTrue(addFavourite);
		
	}

	@Ignore
	@Test
	public void shouldAddRegistration() throws Exception {
		hourRegDao.beginTransaction();
		boolean isAdded = hourRegDao.addHourRegistrations("1095754", "3", "AK", "","2012-05-25", 6, "Ny insert", 0, 0, 1, 10101, 0, 0, "HRA", "", "1095754", "", 0, 0, "", "", "2012-05-30", "HRA", "", 0, 0);
		hourRegDao.endTransaction(false);
		
		assertTrue(isAdded);
	}
	
}
