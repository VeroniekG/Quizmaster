package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.Quiz;
import model.QuizResult;
import view.Main;

public class StudentFeedbackController {

    @FXML
    private Label feedbackLabel;
    @FXML
    private ListView<QuizResult> feedbackList;
 // uit couchdb ophalen
    //@AuthorVG - quizresult need to show quiz attempt and result
    //result = correct answers / given answers
    public void setup(Quiz quiz) {
    }

    //TJ menu knop terug naar menu
    public void doMenu(ActionEvent actionEvent) {
        Main.getSceneManager().showWelcomeScene();
    }
}

