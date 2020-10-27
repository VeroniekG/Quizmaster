package controller;

import database.mysql.DBAccess;
import database.mysql.QuizDAO;
import javafx.event.ActionEvent;
import model.Quiz;
import view.Main;

public class CreateUpdateQuizController {
    private QuizDAO quizdao;
    private DBAccess dbAccess;
    private Quiz quiz;

    public void setup(Quiz quiz) {}

    //TJ menu knop terug naar menu
    public void doMenu(ActionEvent actionEvent) {
        Main.getSceneManager().showWelcomeScene();
    }

    public void doCreateUpdateQuiz() {}
}