package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.ApplicationAlert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.LoginService;
import view.Main;

/**
 * Controller for login view (view.fxml.login.fxml). Controls data flow and updates the view. This
 * class has methods to process username and password, exit the application and show alerts.
 * TODO: Password-encryption
 *
 * @author Huub van Thienen, Daan Leertouwer
 * @version 1.0.7
 * @see view.Main
 * @see view.SceneManager
 * @see model.User
 * @see database.mysql.UserDAO
 * @since 1.0
 */
public class LoginController {

    private static final Logger LOGGER = LogManager.getLogger(LoginController.class);

    private final LoginService loginService;

    @FXML
    private TextField nameTextField;
    @FXML
    private PasswordField passwordField;

    public LoginController() {
        loginService = new LoginService();
    }

    @FXML
    public void doLogin() {
        boolean loginSuccess = loginService.login(nameTextField, passwordField);
        if (loginSuccess) {
            Main.getSceneManager().showWelcomeScene();
        } else {
            ApplicationAlert alert = createAlert();
            alert.show();
        }
    }

    public ApplicationAlert createAlert() {
        return new ApplicationAlert.Builder()
                .withAlertType(Alert.AlertType.WARNING)
                .withHeaderText("Fout bij inloggen")
                .withContentText("Het inloggen is niet gelukt! Probeer het nog eens.")
                .build();
    }

    @FXML
    public void doQuit() {
        LOGGER.info("Quitting application...");
        System.exit(0);
    }

}
