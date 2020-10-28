package controller;

import javafx.event.ActionEvent;
import model.Question;
import view.Main;

public class CreateUpdateQuestionController {

    public void setup(Question question) {}

    //TJ menu knop terug naar menu
    public void doMenu(ActionEvent actionEvent) {
        Main.getSceneManager().showWelcomeScene();
    }

    public void doCreateUpdateQuestion(Question question) {

    }
}