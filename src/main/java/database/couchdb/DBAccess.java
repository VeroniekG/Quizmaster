package database.couchdb;

import com.google.gson.JsonObject;
import config.ApplicationSetup;
import org.lightcouch.CouchDbClient;
import org.lightcouch.CouchDbProperties;
import org.lightcouch.Response;

public class DBAccess {

    private static final ApplicationSetup applicationSetup = ApplicationSetup.getInstance();
    private CouchDbClient client;

    public void setupConnection() {
        CouchDbProperties properties = new CouchDbProperties();

        //Set the database name
        properties.setDbName(applicationSetup.getProperties().getProperty("couchdb.database.name"));

        //Create the database if it didn't already exist
        properties.setCreateDbIfNotExist(true);

        //Server is running on localhost
        properties.setHost(applicationSetup.getProperties().getProperty("couchdb.database.url"));

        //Set the port CouchDB is running on (5984)
        properties.setPort(Integer.parseInt(applicationSetup.getProperties().getProperty("couchdb" +
                ".connection.port")));

        properties.setProtocol(applicationSetup.getProperties().getProperty("couchdb.connection" +
                ".protocol"));

        // Set the database username
        properties.setUsername(applicationSetup.getProperties().getProperty("couchdb.database" +
                ".user"));
        // Set the database user password
        properties.setPassword(applicationSetup.getProperties().getProperty("couchdb.database" +
                ".password"));

        // Create the database client and setup the connection with given properties
        client = new CouchDbClient(properties);
    }

    public String saveDocument(JsonObject document) {
        Response response = client.save(document);
        return response.getId();
    }

    public CouchDbClient getClient() {
        return client;
    }

}
