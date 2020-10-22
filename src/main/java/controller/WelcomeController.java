package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import model.Role;
import model.User;
import view.Main;

public class WelcomeController {

    @FXML
    private Label welcomeLabel;
    @FXML
    private MenuButton taskMenuButton;
    private User currentUser;

    public WelcomeController() {
        this.currentUser = Main.getCurrentUser();
    }

    public void setup() {
        StringBuilder welComeText = new StringBuilder("Welkom " + currentUser.getUserName() + "! ");
        welComeText.append("Je bent ingelogd als " + Role.valueOf(currentUser.getRole()));
        welcomeLabel.setText(welComeText.toString());
    }

    public void doLogout() {
    }

}
