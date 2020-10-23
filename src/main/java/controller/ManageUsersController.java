package controller;

import javafx.event.ActionEvent;
import view.Main;

public class ManageUsersController {

    public void setup() {}

    //TJ menu knop terug naar menu
    public void doMenu(ActionEvent actionEvent) {
        Main.getSceneManager().showWelcomeScene();
    }

    public void doCreateUser() {}

    public void doUpdateUser() {}

    public void doDeleteUser() {}
}
