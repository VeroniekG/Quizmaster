package controller;

import javafx.event.ActionEvent;
import model.Group;
import view.Main;

public class CreateUpdateGroupController {

    public void setup(Group group) {}

    //TJ menu knop terug naar menu
    public void doMenu(ActionEvent actionEvent) {
        Main.getSceneManager().showWelcomeScene();
    }

    public void doCreateUpdateGroup() {}
}
