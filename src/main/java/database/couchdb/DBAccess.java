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

        properties.setDbName(applicationSetup.getProperties().getProperty("couchdb.database.name"));
        properties.setCreateDbIfNotExist(true);
        properties.setHost(applicationSetup.getProperties().getProperty("couchdb.database.url"));
        properties.setPort(Integer.parseInt(applicationSetup.getProperties().getProperty("couchdb" +
                ".connection.port")));
        properties.setProtocol(applicationSetup.getProperties().getProperty("couchdb.connection" +
                ".protocol"));
        properties.setUsername(applicationSetup.getProperties().getProperty("couchdb.database" +
                ".user"));
        properties.setPassword(applicationSetup.getProperties().getProperty("couchdb.database" +
                ".password"));

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
