package controller;

import javafx.event.ActionEvent;
import view.Main;

public class ManageQuizzesController {

    public void setup() {}

    //TJ menu knop terug naar menu
    public void doMenu(ActionEvent actionEvent) {
        Main.getSceneManager().showWelcomeScene();
    }

    public void doCreateQuiz(){}

    public void doUpdateQuiz(){}

    public void doDeleteQuiz(){}
}