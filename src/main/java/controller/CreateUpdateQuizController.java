package controller;

import database.mysql.DBAccess;
import database.mysql.QuizDAO;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Quiz;
import view.Main;

public class CreateUpdateQuizController {
    public Button menuButton;
    private QuizDAO quizdao;
    private DBAccess dbAccess;
    private Quiz quiz;
    public Button composeButton;

    private Label titleLabel;

    public CreateUpdateQuizController() {
    }

    public void setup(Quiz quiz) {
        titlelable.set
    }

    //TJ menu knop terug naar menu
    public void doMenu(ActionEvent actionEvent) {
        Main.getSceneManager().showWelcomeScene();
    }

    public void doCreateUpdateQuiz() {}
}