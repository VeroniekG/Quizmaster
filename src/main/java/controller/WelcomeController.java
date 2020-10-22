package controller;

import database.mysql.DBAccess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import model.Role;
import model.User;
import view.Main;

public class WelcomeController {

    public Button logoutButton;
    @FXML
    private Label welcomeLabel;
    @FXML
    private MenuButton taskMenuButton;
    private User currentUser;
    private DBAccess dbAccess;

    public WelcomeController() {
        this.currentUser = Main.getCurrentUser();
        this.dbAccess = Main.getDBaccess();
    }

    public void setup() {
        StringBuilder welComeText = new StringBuilder("Welkom " + currentUser.getUserName() + "! ");
        welComeText.append("Je bent ingelogd als " + Role.valueOf(currentUser.getRole()));
        welcomeLabel.setText(welComeText.toString());
    }

    public void doLogout(ActionEvent actionEvent) {
        dbAccess.closeConnection();
        Main.getSceneManager().showLoginScene();



    }

}
