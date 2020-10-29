module QuizMaster {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires java.sql;
    requires mysql.connector.java;
    requires lightcouch;
    requires gson;
    requires org.apache.logging.log4j;
    requires org.apache.logging.log4j.core;
    requires org.apache.logging.log4j.iostreams;

    opens view to javafx.graphics, javafx.fxml;
    opens controller to javafx.fxml;

    opens database.couchdb to gson, lightcouch, java.sql;
    opens model to gson;
}