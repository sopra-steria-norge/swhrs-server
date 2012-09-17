package no.steria.swhrs;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.GenericKeyedObjectPoolFactory;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.eclipse.jetty.plus.jndi.EnvEntry;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JettyServer {
    private static final Logger logger = LoggerFactory.getLogger(JettyServer.class);
    public static final String DB_JNDI = "jdbc/registerHoursDS";
    public static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static final String VALIDATION_QUERY = "sql select 1";

    private Server server;
    private Integer port;

    public String dbServerAddress;
    public String dbName;
    public String dbUser;
    public String dbPassword;
    public String dbConnectUri;

    public static void main(String[] args) throws Exception {
        JettyServer jettyServer = new JettyServer();
        jettyServer.startServer();
        jettyServer.server.join();
    }

    public void startServer() throws Exception {
        loadConfigFile();
        loadJdbcDriver();
        DataSource dataSource = makeSqlServerPoolingDatasource();
        logger.info("Validating database connection ...");
        if (dataSource.getConnection().isValid(0)) {
            logger.info("Database connection valid");
        }
        new EnvEntry(DB_JNDI, dataSource);

        if (port == null) {
            port = Integer.valueOf(System.getProperty("swhrs.serverPort"));
        }

        server = new Server(port);
        server.setHandler(new WebAppContext("src/main/webapp", "/"));
        server.start();
        logger.info("Server started! - port " + port);
    }

    public void loadConfigFile() throws IOException {
        InputStream resourceAsStream = JettyServer.class.getResourceAsStream("/config.properties");
        Properties properties = System.getProperties();
        properties.load(resourceAsStream);
        dbServerAddress = System.getProperty("swhrs.dbServer");
        dbName = System.getProperty("swhrs.dbName");
        dbUser = System.getProperty("swhrs.dbUsername");
        dbPassword = System.getProperty("swhrs.dbPassword");
        dbConnectUri = String.format("jdbc:sqlserver://%s;database=%s", dbServerAddress, dbName);
    }

    private void loadJdbcDriver() {
        try {
            Class.forName(JDBC_DRIVER);
            logger.info(String.format("JDBC driver '%s' loaded", JDBC_DRIVER));
        } catch (ClassNotFoundException e) {
            logger.error("Could not load JDBC driver", e);
        }
    }

    public DataSource makeSqlServerPoolingDatasource() throws NamingException {
        ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(dbConnectUri, dbUser, dbPassword);
        ObjectPool objectPool = new GenericObjectPool();
        PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory, objectPool, new GenericKeyedObjectPoolFactory(null), VALIDATION_QUERY, false, true);
        objectPool.setFactory(poolableConnectionFactory);
        return new PoolingDataSource(objectPool);
    }

    public void setPort(int port) {
        this.port = port;
    }
}
