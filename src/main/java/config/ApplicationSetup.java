package config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static config.ApplicationProperties.*;

/**
 * Sets all default properties to be used by the application, as defined in class
 * {@link ApplicationProperties}. If a file 'config.properties' exists in the resources root, the
 * property-values set in this file will override the default values.
 *
 * @author Daniel Leertouwer
 * @version 1.0.5
 * @see ApplicationSetup
 * @see Properties
 * @since 1.0
 */
public class ApplicationSetup {

    private static final String PROPERTIES_FILE_PATH = "src/main/resources/config.properties";
    private static final Logger LOG = LogManager.getLogger(ApplicationSetup.class);
    private static ApplicationSetup applicationSetupInstance = null;
    private Properties properties;

    private ApplicationSetup() {
        this.properties = null;
    }

    public void load() {
        setDefaultProperties();
        setCustomProperties();
    }

    private void setDefaultProperties() {
        properties = new Properties();
        properties.setProperty(MYSQL_DATABASE_NAME.getKey(), MYSQL_DATABASE_NAME.getValue());
        properties.setProperty(MYSQL_DATABASE_URL.getKey(), MYSQL_DATABASE_URL.getValue());
        properties.setProperty(MYSQL_DATABASE_USER.getKey(), MYSQL_DATABASE_USER.getValue());
        properties.setProperty(MYSQL_DATABASE_PASSWORD.getKey(),
                MYSQL_DATABASE_PASSWORD.getValue());
        properties.setProperty(MYSQL_JDBC_DRIVER.getKey(), MYSQL_JDBC_DRIVER.getValue());
        properties.setProperty(MYSQL_JDBC_CONNECTION_SETTINGS.getKey(),
                MYSQL_JDBC_CONNECTION_SETTINGS.getValue());
        properties.setProperty(COUCHDB_DATABASE_NAME.getKey(), COUCHDB_DATABASE_NAME.getValue());
        properties.setProperty(COUCHDB_DATABASE_URL.getKey(), COUCHDB_DATABASE_URL.getValue());
        properties.setProperty(COUCHDB_CONNECTION_PORT.getKey(),
                COUCHDB_CONNECTION_PORT.getValue());
        properties.setProperty(COUCHDB_CONNECTION_PROTOCOL.getKey(),
                COUCHDB_CONNECTION_PROTOCOL.getValue());
        properties.setProperty(COUCHDB_DATABASE_USER.getKey(), COUCHDB_DATABASE_USER.getValue());
        properties.setProperty(COUCHDB_DATABASE_PASSWORD.getKey(),
                COUCHDB_DATABASE_PASSWORD.getValue());

        LOG.info("Default application properties set.");
    }

    private void setCustomProperties() {
        Properties propertiesFromFile = loadPropertiesFile();
        properties.forEach(
                (propertyKey, propertyValue) -> {
                    if (properties.containsKey(propertyKey)) {
                        propertyValue = propertiesFromFile.get(propertyKey);
                    }
                    properties.replace(propertyKey, propertyValue);
                }
        );
        LOG.info("Custom properties set.");
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

    public static ApplicationSetup getInstance() {
        if (applicationSetupInstance == null) {
            applicationSetupInstance = new ApplicationSetup();
        }
        return applicationSetupInstance;
    }

    public Properties getProperties() {
        return properties;
    }

}

