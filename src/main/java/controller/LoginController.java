package controller;

import database.mysql.DBAccess;
import database.mysql.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.User;
import view.Main;

/**
 * TODO: username- en password validatie case-sensitive (en evt. sessie-management en pw-encryptie)
 *
 * @Author dleertouwer
 */
public class LoginController {

    private UserDAO userDAO;
    private DBAccess dbAccess;

    @FXML
    private TextField nameTextField;
    @FXML
    private PasswordField passwordField;

    public LoginController() {
        this.dbAccess = Main.getDBaccess();
        this.userDAO = new UserDAO(dbAccess);
    }

    @FXML
    public void doLogin() {
        String userNameInput = nameTextField.getText();
        String passwordInut = passwordField.getText();
        User currentUser = userDAO.getUserByName(userNameInput);
        // Als de gebruiker gevonden is in de database het wachtwoord valideren en anders de
        // gebruiker informeren.
        if (currentUser != null) {
            if (currentUser.getPassword().equals(passwordInut)) {
                // Naar het volgende scherm als de wachtwoordvalidatie geslaagd is en de connectie
                // afbreken.
                Main.getSceneManager().showWelcomeScene();
                dbAccess.closeConnection();
            } else {
                showAlert("Het ingevoerde wachtwoord is onjuist!", AlertType.ERROR);
            }
        } else {
            showAlert("Gebruiker '" + userNameInput + "' niet gevonden!", AlertType.ERROR);
        }
    }

    @FXML
    public void doQuit() {
        dbAccess.closeConnection();
        System.out.println("Connectie gesloten, applicatie beëindigen...");
        System.exit(0);
    }

    public void showAlert(String message, AlertType type) {
        Alert alert = new Alert(type);
        alert.setContentText(message);
        alert.show();
    }

}
