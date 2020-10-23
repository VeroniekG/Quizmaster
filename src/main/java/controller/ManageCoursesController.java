package controller;

import javafx.event.ActionEvent;
import view.Main;

public class ManageCoursesController {

    public void setup() {}

    public void doMenu(){}

    public void doCreateCourse(){}

    public void doUpdateCourse(){}

    public void doDeleteCourse(){}

    //TJ menu knop terug naar menu
    public void doMenu(ActionEvent actionEvent) {
        Main.getSceneManager().showWelcomeScene();
    }

}
