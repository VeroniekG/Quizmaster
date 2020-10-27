package controller;

import javafx.event.ActionEvent;
import model.Quiz;
import view.Main;

public class CreateUpdateQuizController {

    public void setup(Quiz quiz) {}

    //TJ menu knop terug naar menu
    public void doMenu(ActionEvent actionEvent) {
        Main.getSceneManager().showWelcomeScene();
    }

    public void doCreateUpdateQuiz() {}
}