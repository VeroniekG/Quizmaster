package config;

import database.mysql.DBAccess;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static config.ApplicationProperties.*;

public class ApplicationSetup {

    private static final String PROPERTIES_FILE_PATH = "src/main/resources/config.properties";
    private static final Logger LOG = LogManager.getLogger(ApplicationSetup.class);
    private static DBAccess dbAccess = null;
    private static ApplicationSetup applicationSetupInstance = null;
    private Properties properties;

    private ApplicationSetup() {
        this.properties = null;
    }

    public void load() {
        setDefaultProperties();
        setCustomProperties();
        //setDbAccess();
        //dbAccess.loadDriver();
    }

    private void setDefaultProperties() {
        properties = new Properties();
        properties.setProperty(JDBC_DATABASE_NAME.getKey(), JDBC_DATABASE_NAME.getValue());
        properties.setProperty(JDBC_DATABASE_URL.getKey(), JDBC_DATABASE_URL.getValue());
        properties.setProperty(JDBC_DATABASE_USER.getKey(), JDBC_DATABASE_USER.getValue());
        properties.setProperty(JDBC_DATABASE_PASSWORD.getKey(),
                JDBC_DATABASE_PASSWORD.getValue());
        properties.setProperty(JDBC_DATABASE_DRIVER.getKey(),
                JDBC_DATABASE_DRIVER.getValue());
        properties.setProperty(JDBC_CONNECTION_SETTINGS.getKey(),
                JDBC_CONNECTION_SETTINGS.getValue());
        LOG.warn("Default application properties set.");
    }

    private Properties loadPropertiesFile() {
        Properties propertiesFromFile = new Properties();
        try (InputStream fileInputStream = new FileInputStream(PROPERTIES_FILE_PATH)) {
            propertiesFromFile.load(fileInputStream);
            LOG.info("Properties file '" + PROPERTIES_FILE_PATH + "' successfully loaded.");
        } catch (IOException ioException) {
            LOG.warn("Error loading properties file! Using default properties.");
            LOG.info(properties);
        }
        return propertiesFromFile;
    }

    private void setDbAccess() {
        String dbName = properties.getProperty("jdbc.database.name");
        String dbUser = properties.getProperty("jdbc.database.user");
        String dbPasword = properties.getProperty("jdbc.database.password");
        if (dbAccess == null) {
            dbAccess = new DBAccess(dbName, dbUser, dbPasword);
        }
    }

    private void setCustomProperties() {
        Properties propertiesFromFile = loadPropertiesFile();
        properties.forEach(
                (propertyKey, propertyValue) -> {
                    if (properties.containsKey(propertyKey)) {
                        propertyValue = properties.get(propertyKey);
                    }
                    properties.replace(propertyKey, propertyValue);
                }
        );
        LOG.info("Custom properties set.");
        LOG.info(properties);
    }

    public static ApplicationSetup getInstance() {
        if (applicationSetupInstance == null) {
            applicationSetupInstance = new ApplicationSetup();
        }
        return applicationSetupInstance;
    }

    public DBAccess getDbAccess() {
        return dbAccess;
    }

    public Properties getProperties() {
        return properties;
    }

}
