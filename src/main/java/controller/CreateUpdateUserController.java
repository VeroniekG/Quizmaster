package controller;

import database.mysql.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.Main;

public class CreateUpdateUserController {

    private static final Logger LOGGER = LogManager.getLogger(CreateUpdateUserController.class);
    @FXML
    Label titleLabel;
    @FXML
    TextField userName;
    @FXML
    PasswordField password;
    private UserDAO userDAO;

    public CreateUpdateUserController() {
        userDAO = new UserDAO(Main.getDBaccess());
    }

    public void setup(User user) {
        userName.setText(user.getUserName());
    }

    //TJ menu knop terug naar menu
    public void doMenu(ActionEvent actionEvent) {
        Main.getSceneManager().showWelcomeScene();
    }

    public void doCreateUpdateUser() {
    }

}
