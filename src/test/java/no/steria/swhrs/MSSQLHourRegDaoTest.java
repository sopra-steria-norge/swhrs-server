package no.steria.swhrs;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import org.joda.time.DateTime;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class MSSQLHourRegDaoTest {
    private static final Logger logger = LoggerFactory.getLogger(MSSQLHourRegDaoTest.class);

	private static MSSQLHourRegDao hourRegDao;

    @BeforeClass
	public static void createRegDao() throws Exception{
		hourRegDao = createHourRegDao();
	}

	private static MSSQLHourRegDao createHourRegDao() throws SQLException, IOException {
		InputStream resourceAsStream = MSSQLHourRegDao.class.getResourceAsStream("/config.properties");
		Properties properties = System.getProperties();
		properties.load(resourceAsStream);
		String serverAddress = System.getProperty("swhrs.dbServer");
		String databaseName = System.getProperty("swhrs.dbName");
		String user = System.getProperty("swhrs.dbUsername");
		String password = System.getProperty("swhrs.dbPassword");

		int port = 1433;

		logger.info("Connecting to '" + serverAddress + "' db '" + databaseName + "' port '" + port + "' user '" + user + "'");

        SQLServerDataSource dataSource = new SQLServerDataSource();
		dataSource.setServerName(serverAddress);
		dataSource.setPortNumber(port);
		dataSource.setDatabaseName(databaseName);
		dataSource.setUser(user);
		dataSource.setPassword(password);
		return new MSSQLHourRegDao(dataSource);
	}

	@Test
	public void shouldValidateUser() throws Exception {
		String username = "AK";
		Password password = Password.fromPlaintext("salt", "password");
		boolean validate = hourRegDao.findUser(username, password) != null;
		assertTrue(validate);
	}
	
	
	@Test
	public void shouldGetRegistrations() throws Exception {
		List<HourRegistration> allHoursForDate = hourRegDao.getAllHoursForDate("AK", new DateTime(2012, 5, 30, 0, 0));
		assertThat(allHoursForDate).hasSize(5);
	}
	
	@Test
	public void shouldGetUserFavourites() throws Exception {
		List<UserFavourites> userFavourites = hourRegDao.getUserFavourites("AK");
		assertThat(userFavourites).hasSize(13);
	}
	
	
	@Test
	public void shouldSearchForProjects() throws Exception {
		List<Projects> projects = hourRegDao.searchProjects("LARM");
		assertThat(projects).hasSize(27);
	}
	

	@Test
	public void shouldGetWeekRegistrations() throws Exception {
		List<WeekRegistration> weeklist = hourRegDao.getWeekList("SOLJ", "2012-05-01 00:00:00.0", "2012-05-10 00:00:00.0");
		assertThat(weeklist).hasSize(7);
	}
	
	
	@Test
	public void shouldGetPeriod() throws Exception {
		DatePeriod period = hourRegDao.getPeriod("AK","2012-11-07");
		assertThat(period.getFromDate()).contains("2012-11-05");
	}

    @Ignore
	@Test
	public void shouldDeleteRegistration() throws Exception {
        hourRegDao.deleteHourRegistration("ROR", "2101620");
	}

    @Ignore
	@Test
	public void shouldAddFavourites() throws Exception {
		boolean addFavourite = hourRegDao.addFavourites("IHH", "1095754", "7");
		assertTrue(addFavourite);
	}

    @Ignore
    @Test
    public void shouldAddRegistrationStoreProcedure() throws Exception {
        String username = "ROR";
        String userRegisteredFor = "ROR";
        String projectNumber = "1114330";
        String activity = "1";
        DateTime date = new DateTime(2012, 9, 3, 0, 0);
        double hours = 1.0;
        boolean isChargedHours = true;
        String workType = "";
        String description = "";
        boolean bypassChecks = false;

        Integer entryId = hourRegDao.addHourRegistrations(username, userRegisteredFor, projectNumber, activity,
                date, hours, isChargedHours, workType, description, bypassChecks);

        assertNotNull(entryId);
    }

    @Ignore
    @Test
    public void shouldUpdateRegistration() throws Exception {
        String username = "ROR";
        String entryId = "2105803";
        String projectNumber = "1112790";
        String activity = "1";
        DateTime date = new DateTime(2012, 9, 3, 0, 0);
        double hours = 1.0;
        boolean isChargedHours = true;
        String workType = "";
        String description = "updated";

        hourRegDao.updateHourRegistration(username, entryId,  projectNumber, activity, date, hours, isChargedHours,
                workType, description);

        assertNotNull(entryId);
    }


    @Test
    public void shouldSubmitHours() throws Exception {
        String username = "ROR";
        DateTime date = new DateTime(2012, 9, 3, 0, 0);
        hourRegDao.submitHours(username, username, date);
    }


    @Test
    public void shouldReOpenHours() throws Exception {
        String username = "ROR";
        DateTime date = new DateTime(2012, 9, 3, 0, 0);
        hourRegDao.reopenHours(username, username, date);
    }

}
