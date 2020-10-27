package database.mysql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBAccess {

    //private static final Log log = LogFactory.getLog(DBAccess.class);
    private static final Logger log = LogManager.getLogger(DBAccess.class);
    private static final String SQL_EXCEPTION = "SQL Exception: ";
    private static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    //private static final String MYSQL_DRIVER =
    //        Main.getApplicationConfig().getProperties().getProperty("jdbc.driver");
    private static final String PREFIX_CONNECTION_URL = "jdbc:mysql://localhost:3306/";
    private static final String CONNECTION_SETTINGS = "?useSSL=false" +
            "&allowPublicKeyRetrieval=true" +
            "&useJDBCCompliantTimezoneShift=true" +
            "&useLegacyDatetimeCode=false" +
            "&serverTimezone=UTC";
    private Connection connection;
    private String databaseName;
    private String mainUser;
    private String mainUserPassword;

    public DBAccess(String databaseName, String mainUser, String mainUserPassword) {
        this.databaseName = databaseName;
        this.mainUser = mainUser;
        this.mainUserPassword = mainUserPassword;
    }

    public void loadDriver() { // Driver loaded in ApplicationSetup
        try {
            Class.forName(MYSQL_DRIVER); // Explicitly load the JDBC-driver.
            log.trace("Driver successfully loaded.");
        } catch (ClassNotFoundException driverFout) {
            log.warn("Driver not found!");
        }
    }

    /**
     * Open database connection
     */
    public void openConnection() {
        log.info("Opening connection to database " + databaseName);
        String connectionURL = PREFIX_CONNECTION_URL + databaseName + CONNECTION_SETTINGS;
        try {
            connection = DriverManager.getConnection(connectionURL, mainUser, mainUserPassword);
            log.trace("OK, connection open.");
        } catch (SQLException sqlFout) {
            log.error(SQL_EXCEPTION + sqlFout.getMessage());
        }
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
            log.trace("Connection closed.");
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

}