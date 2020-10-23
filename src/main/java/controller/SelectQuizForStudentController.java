package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Quiz;
import view.Main;

public class SelectQuizForStudentController {

    @FXML
    ListView<Quiz> quizList;

    public void setup() {}

    //TJ menu knop terug naar menu
    public void doMenu(ActionEvent actionEvent) {
        Main.getSceneManager().showWelcomeScene();
    }

    public void doQuiz() {}
}
