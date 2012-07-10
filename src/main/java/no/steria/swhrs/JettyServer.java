package no.steria.swhrs;

import org.eclipse.jetty.plus.jndi.EnvEntry;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.hibernate.cfg.Environment;
import org.hsqldb.jdbc.JDBCDataSource;

public class JettyServer {
	public static void main(String[] args) throws Exception {
		JDBCDataSource jdbcDataSource = new JDBCDataSource();
		jdbcDataSource.setDatabase("jdbc:hsqldb:mem:testDb");
//		jdbcDataSource.setDatabase("jdbc:sqlserver://localhost;databaseName=SuperComputer;integratedSecurity=true;");
		jdbcDataSource.setUser("sa");
		jdbcDataSource.setPassword("");
		System.setProperty(Environment.HBM2DDL_AUTO, "create");
		new EnvEntry(Parameters.DB_JNDI, jdbcDataSource);
		
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
