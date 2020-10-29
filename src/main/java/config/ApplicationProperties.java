package config;

public enum ApplicationProperties {
    MYSQL_DATABASE_NAME("mysql.database.name", "Quizmaster"),
    MYSQL_DATABASE_URL("mysql.database.url", "localhost"),
    MYSQL_DATABASE_USER("mysql.database.user", "userQuizmaster"),
    MYSQL_DATABASE_PASSWORD("mysql.database.password", "pwQuizmaster"),
    MYSQL_JDBC_DRIVER("mysql.jdbc.driver", "com.mysql.cj.jdbc.Driver"),
    MYSQL_JDBC_CONNECTION_SETTINGS(
            "mysql.jdbc.connection.settings",
            "?useSSL=false" +
                    "&allowPublicKeyRetrieval=true" +
                    "&useJDBCCompliantTimezoneShift=true" +
                    "&useLegacyDatetimeCode=false" +
                    "&serverTimezone=UTC"
    ),
    COUCHDB_DATABASE_NAME("couchdb.database.name", "quizmaster"),
    COUCHDB_DATABASE_URL("couchdb.database.url", "localhost"),
    COUCHDB_CONNECTION_PORT("couchdb.connection.port", "5984"),
    COUCHDB_CONNECTION_PROTOCOL("couchdb.connection.protocol", "http"),
    COUCHDB_DATABASE_USER("couchdb.database.user", "admin"),
    COUCHDB_DATABASE_PASSWORD("couchdb.database.password", "");

    private String key;
    private String value;

    private ApplicationProperties(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

}
