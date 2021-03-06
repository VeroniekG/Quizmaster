package database.mysql;

import config.ApplicationSetup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBAccess {

    private static final ApplicationSetup applicationSetup = ApplicationSetup.getInstance();
    private static final Logger log = LogManager.getLogger(DBAccess.class);
    private static final String SQL_EXCEPTION = "SQL Exception: ";
    private static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String PREFIX_CONNECTION_URL = "jdbc:mysql://localhost:3306/";
    private static final String CONNECTION_SETTINGS = "?useSSL=false" +
            "&allowPublicKeyRetrieval=true" +
            "&useJDBCCompliantTimezoneShift=true" +
            "&useLegacyDatetimeCode=false" +
            "&serverTimezone=UTC";
    private static DBAccess dbAccessInstance = null;
    private Connection connection;
    private String databaseName;
    private String mainUser;
    private String mainUserPassword;

    private DBAccess() {

    }

    /**
     * Close database connection
     */
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException connectionError) {
            log.error("Error while closing connection: " + connectionError.getMessage());
        } finally {
            log.info("Connection to database '" + databaseName + "' closed.");
        }
    }

    /**
     * Gets the JDBC connection object needed to create statements
     *
     * @return an object of type Connection
     */
    public Connection getConnection() {
        if (connection == null) {
            this.openConnection();
        }
        try {
            if (connection.isClosed()) {
                this.openConnection();
            }
        } catch (SQLException connectionError) {
            log.error("Connection error!");
        }
        return connection;
    }

    /**
     * Open database connection
     */
    public void openConnection() {
        String connectionURL = PREFIX_CONNECTION_URL + databaseName + CONNECTION_SETTINGS;
        try {
            connection = DriverManager.getConnection(connectionURL, mainUser, mainUserPassword);
            log.info("Connection to database '" + databaseName + "' open.");
        } catch (SQLException sqlFout) {
            log.error(SQL_EXCEPTION + sqlFout.getMessage());
        }
    }

    public static DBAccess getInstance() {
        if (dbAccessInstance == null) {
            dbAccessInstance = new DBAccess();
            dbAccessInstance.init();
        }
        return dbAccessInstance;
    }

    public void init() {
        databaseName = applicationSetup.getProperties().getProperty("mysql.database.name");
        mainUser = applicationSetup.getProperties().getProperty("mysql.database.user");
        mainUserPassword = applicationSetup.getProperties().getProperty("mysql.database.password");
        loadDriver();
    }

    public void loadDriver() { // Driver loaded in ApplicationSetup
        try {
            Class.forName(MYSQL_DRIVER); // Explicitly load the JDBC-driver.
            log.trace("Driver successfully loaded.");
        } catch (ClassNotFoundException driverFout) {
            log.warn("Driver not found!");
        }
    }

}