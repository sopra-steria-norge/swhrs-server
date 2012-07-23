package no.steria.swhrs;

import java.io.InputStream;
import java.util.Properties;

import net.sourceforge.jtds.jdbc.Driver;
import net.sourceforge.jtds.jdbcx.JtdsDataSource;

import org.eclipse.jetty.plus.jndi.EnvEntry;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.hibernate.cfg.Environment;
import org.hsqldb.jdbc.JDBCDataSource;

public class JettyServer {
	public static void main(String[] args) throws Exception {
		InputStream resourceAsStream = JettyServer.class.getResourceAsStream("/config.properties");
		Properties properties = System.getProperties();
		properties.load(resourceAsStream);
		
		if ("true".equals(System.getProperty("swhrs.useSqlServer"))) {
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

			new EnvEntry("jdbc/registerHoursDS", datasource);
		} else {
		
			JDBCDataSource jdbcDataSource = new JDBCDataSource();
			jdbcDataSource.setDatabase("jdbc:hsqldb:mem:testDb");
	//		jdbcDataSource.setDatabase("jdbc:sqlserver://localhost;databaseName=SuperComputer;integratedSecurity=true;");
			jdbcDataSource.setUser("sa");
			jdbcDataSource.setPassword("");
			System.setProperty(Environment.HBM2DDL_AUTO, "create");
			new EnvEntry(Parameters.DB_JNDI, jdbcDataSource);
		}
		int localPort = 8081;
		String envPort = System.getenv("PORT");
		if (envPort != null && !envPort.isEmpty()) {
			localPort = Integer.parseInt(envPort);
		}
		
		Server server = new Server(localPort);
		server.setHandler(new WebAppContext("src/main/webapp", "/"));
		server.start();
		System.out.println("Server started! - port " + localPort);
		server.join();
		System.out.println("Dette gikk galt!");
		
	}
}
