package controller;

import database.mysql.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.User;
import view.Main;

public class CreateUpdateUserController {

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
