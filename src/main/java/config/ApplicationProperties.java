package config;

public enum ApplicationProperties {
    JDBC_DATABASE_NAME("jdbc.database.name", "Quizmaster"),
    JDBC_DATABASE_URL("jdbc.database.url", "localhost"),
    JDBC_DATABASE_USER("jdbc.database.user", "userQuizmaster"),
    JDBC_DATABASE_PASSWORD("jdbc.database.password", "pwQuizmaster"),
    JDBC_DATABASE_DRIVER("jdbc.driver", "com.mysql.cj.jdbc.Driver"),
    JDBC_CONNECTION_SETTINGS(
            "jdbc.connection.settings",
            "?useSSL=false" +
                    "&allowPublicKeyRetrieval=true" +
                    "&useJDBCCompliantTimezoneShift=true" +
                    "&useLegacyDatetimeCode=false" +
                    "&serverTimezone=UTC"
    );

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
