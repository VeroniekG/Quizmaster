package controller;

import database.mysql.QuizDAO;
import javafx.fxml.FXML;
import database.mysql.DBAccess;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Quiz;
import view.Main;

public class CreateUpdateQuizController {
    public Button menuButton;
    private QuizDAO quizDAO;
    private DBAccess dbAccess;
    private Quiz quiz;
    public Button composeButton;

    private Label titleLabel;
    private TextField quizIdTextfield;
    private TextField quizNameTextfield;



    public CreateUpdateQuizController() {
        quizDAO = new QuizDAO(Main.getDBaccess());
    }

    public void setup(Quiz quiz) {
        quizIdTextfield.setText(String.valueOf(quiz.getIdQuiz()));
        quizNameTextfield.setText(quizNameTextfield.getSelectedText());
    }

    //TJ menu knop terug naar menu
    public void doMenu(ActionEvent actionEvent) {
        Main.getSceneManager().showWelcomeScene();
    }

    public void doCreateUpdateQuiz() {}
}
