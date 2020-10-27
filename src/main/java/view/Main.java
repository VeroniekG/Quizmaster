package view;

import config.ApplicationSetup;
import database.mysql.DBAccess;
import javafx.application.Application;
import javafx.stage.Stage;
import model.User;

public class Main extends Application {

    private static final ApplicationSetup applicationSetup = ApplicationSetup.getInstance();
    private static SceneManager sceneManager = null;
    private static Stage primaryStage = null;
    private static DBAccess dbAccess = null;
    private static User currentUser = null;

    public static void main(String[] args) {
        applicationSetup.load(); // Singleton -> Only one instance
        dbAccess = getDbAccess();
        dbAccess.loadDriver();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Main.primaryStage = primaryStage;
        primaryStage.setTitle("Make IT Work - Project 1");
        getSceneManager().setWindowTool();
        primaryStage.show();
    }

    public static SceneManager getSceneManager() {
        if (sceneManager == null) {
            sceneManager = new SceneManager(primaryStage);
        }
        return sceneManager;
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        Main.currentUser = currentUser;
    }

    public static DBAccess getDbAccess() {
        String dbName = applicationSetup.getProperties().getProperty("jdbc.database.name");
        String dbUser = applicationSetup.getProperties().getProperty("jdbc.database.user");
        String dbPasword = applicationSetup.getProperties().getProperty("jdbc.database.password");
        if (dbAccess == null) {
            dbAccess = new DBAccess(dbName, dbUser, dbPasword);
        }
        return dbAccess;
    }

}