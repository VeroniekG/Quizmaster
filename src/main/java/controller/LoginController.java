package controller;

import database.mysql.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.Main;

/**
 * Controller for login view (view.fxml.login.fxml). Controls data flow and updates the view. This
 * class has methods to process username and password, exit the application and show alerts.
 * TODO: Modify message (login failed)
 * TODO: Error labels instead of alerts
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

    private static final Logger log = LogManager.getLogger(LoginController.class);
    private UserDAO userDAO;

    @FXML
    private TextField nameTextField;
    @FXML
    private PasswordField passwordField;

    public LoginController() {
        userDAO = new UserDAO(Main.getDBaccessMySql());
    }

    @FXML
    public void doLogin() {
        String userNameInput = nameTextField.getText();
        CharSequence passwordInut = passwordField.getText();
        User currentUser = userDAO.getOneByName(userNameInput);
        // Als de gebruiker gevonden is in de database het wachtwoord valideren en anders de
        // gebruiker informeren.
        if (currentUser != null) {
            if (currentUser.getPassword().contentEquals(passwordInut)) {
                // Naar het volgende scherm als de wachtwoordvalidatie geslaagd is en de connectie
                // afbreken.
                Main.setCurrentUser(currentUser);
                Main.getSceneManager().showWelcomeScene();
            } else {
                showAlert("Inloggen is mislukt, probeer het opnieuw", AlertType.ERROR);
            }
        } else {
            showAlert("Gebruiker '" + userNameInput + "' niet gevonden!", AlertType.ERROR);
        }
    }

    @FXML
    public void doQuit(ActionEvent event) {
        log.info("Quitting application...");
        System.exit(0);
    }

    public void showAlert(String message, AlertType type) {
        Alert alert = new Alert(type);
        alert.setContentText(message);
        alert.show();
    }

}
