package controller;

import javafx.event.ActionEvent;
import view.Main;

public class ManageGroupsController {

    public void setup() {}

    //TJ menu knop terug naar menu
    public void doMenu(ActionEvent actionEvent) {
        Main.getSceneManager().showWelcomeScene();
    }

    public void doCreateGroup() {}

    public void doUpdateGroup() {}

    public void doDeleteGroup() {}
}
