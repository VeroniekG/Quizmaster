package controller;

import javafx.event.ActionEvent;
import view.Main;

public class ManageQuestionsController {

    public void setup() {}

    //TJ menu knop terug naar menu
    public void doMenu(ActionEvent actionEvent) {
        Main.getSceneManager().showWelcomeScene();
    }

    public void doCreateQuestion(){}

    public void doUpdateQuestion(){}

    public void doDeleteQuestion(){}
}
