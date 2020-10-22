package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import model.TaskList;
import model.User;
import view.Main;

import java.util.List;

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
        welComeText.append("Je bent ingelogd als " + currentUser.getRole().toString().toLowerCase());
        welcomeLabel.setText(welComeText.toString());
        List<String> userTaskList = TaskList.createTaskList(currentUser.getRole());
        for (String task : userTaskList) {
            taskMenuButton.getItems().add(new MenuItem(task));
        }
        System.out.println(userTaskList.toString());
    }

    public void doLogout() {
    }

}
