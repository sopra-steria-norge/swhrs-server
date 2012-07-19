package no.steria.swhrs;

import static org.fest.assertions.Assertions.assertThat;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import net.sourceforge.jtds.jdbc.Driver;
import net.sourceforge.jtds.jdbcx.JtdsDataSource;

import org.joda.time.LocalDate;
import org.junit.Test;

public class MSSQLHourRegDaoTest {
	
	@Test
	public void shouldConnectToDatabase() throws Exception {
		InputStream resourceAsStream = getClass().getResourceAsStream("/config.properties");
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
		
		MSSQLHourRegDao hourRegDao = new MSSQLHourRegDao(datasource);
		
		hourRegDao.beginTransaction();
		List<HourRegistration> allHoursForDate = hourRegDao.getAllHoursForDate(1, new LocalDate());
		hourRegDao.endTransaction(false);
		
		assertThat(allHoursForDate).hasSize(4);
		
		

		
	}

}
