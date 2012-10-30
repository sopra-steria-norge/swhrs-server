package no.steria.swhrs;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.GenericKeyedObjectPoolFactory;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.eclipse.jetty.plus.jndi.EnvEntry;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.*;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class JettyServer implements JettyServerMBean {
    private static final Logger logger = LoggerFactory.getLogger(JettyServer.class);
    public static final String DB_JNDI = "jdbc/registerHoursDS";
    public static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static final String VALIDATION_QUERY = "sql select 1";

    private Server server;
    private Integer httpPort;

    public String dbServerAddress;
    public String dbName;
    public String dbUser;
    public String dbPassword;
    public String dbConnectUri;
    private volatile boolean running = true;
    private WebAppContext webAppContext;

    public JettyServer() {
        loadJdbcDriver();
        loadConfigFile();
    }


    @Override
    public void setUserWhitelist(String userWhitelist) {
        if (isCommaSeparatedString(userWhitelist)) {
            webAppContext.setAttribute("userWhitelist", Arrays.asList(StringUtils.split(userWhitelist, ",")));
        } else {
            throw new IllegalArgumentException("Not a comma-separated list of valid user names.");
        }
    }

    @Override
    public String getUserWhitelist() {
        return StringUtils.join((List<String>) webAppContext.getAttribute("userWhitelist"), ",");
    }

    @Override
    public void setUserBlacklist(String userBlacklist) {
        if (isCommaSeparatedString(userBlacklist)) {
            webAppContext.setAttribute("userBlacklist", Arrays.asList(StringUtils.split(userBlacklist, ",")));
        } else {
            throw new IllegalArgumentException("Not a comma-separated list of valid user names.");
        }
    }

    @Override
    public String getUserBlacklist() {
        return StringUtils.join((List<String>) webAppContext.getAttribute("userBlacklist"), ",");
    }

    @Override
    public void setOnlyAllowUsersFromWhitelist(Boolean onlyAllowUsersFromWhitelist) {
        webAppContext.setAttribute("onlyAllowUsersFromWhitelist", onlyAllowUsersFromWhitelist);
    }

    @Override
    public Boolean getOnlyAllowUsersFromWhitelist() {
        return (Boolean) webAppContext.getAttribute("onlyAllowUsersFromWhitelist");
    }

    @Override
    public void restartServer() throws Exception {
        server.stop();
        server.join();
        logger.info("Server restarted");
    }

    @Override
    public void stopServer() throws Exception {
        running = false;
        server.stop();
        server.join();
        logger.info("Server stopped");
    }

    public void runServer() throws Exception {
        while (running) {
            configureServer();
            startServer();
            server.join();
        }
    }

    public void configureServer() throws Exception {
        DataSource dataSource = makeSqlServerPoolingDatasource();
        logger.info("Validating database connection ...");
        if (dataSource.getConnection().isValid(0)) {
            logger.info("Database connection valid");
        }
        new EnvEntry(DB_JNDI, dataSource);

        if (httpPort == null) {
            httpPort = Integer.valueOf(System.getProperty("swhrs.serverPort"));
        }

        server = new Server(httpPort);
        webAppContext = new WebAppContext("src/main/webapp", "/");

        setUserWhitelist(System.getProperty("swhrs.userWhitelist") != null ? System.getProperty("swhrs.userWhitelist") : "");
        setUserBlacklist(System.getProperty("swhrs.userBlacklist") != null ? System.getProperty("swhrs.userBlacklist") : "");
        setOnlyAllowUsersFromWhitelist(System.getProperty("swhrs.onlyAllowUsersFromWhitelist") != null ? Boolean.valueOf(System.getProperty("swhrs.onlyAllowUsersFromWhitelist")) : true);

        server.setHandler(webAppContext);
        server.setStopAtShutdown(true);
    }

    private void configureJmx() throws MalformedObjectNameException, MBeanRegistrationException, InstanceAlreadyExistsException, NotCompliantMBeanException {
        MBeanServer platformMBeanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName objectName = new ObjectName("no.steria.swhrs:type=JettyServer");
        platformMBeanServer.registerMBean(this, objectName);
    }

    public void loadConfigFile() {
        InputStream resourceAsStream = JettyServerMBean.class.getResourceAsStream("/config.properties");
        Properties properties = System.getProperties();
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
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

    public void startServer() throws Exception {
        server.start();
        logger.info("Server started! - httpPort " + httpPort);
    }

    public static void main(String[] args) {
        try {
            JettyServer server = new JettyServer();
            server.configureJmx();
            server.runServer();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    private static boolean isCommaSeparatedString(String input) {
        String[] tokens = input.split(",");
        for (String token : tokens) {
            if (!token.matches("[a-zA-Z]*")) {
                return false;
            }
        }
        return true;
    }

    public void setHttpPort(int port) {
        this.httpPort = port;
    }
}
