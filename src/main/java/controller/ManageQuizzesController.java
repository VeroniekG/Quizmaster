package controller;

import database.mysql.DBAccess;
import database.mysql.QuizDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import model.Quiz;
import view.Main;

import java.util.ArrayList;

public class ManageQuizzesController {
        public Button newQuizButton;
        public Button menuButton;
        private QuizDAO quizDAO;
        private DBAccess dbAccess;

        @FXML
        ListView<Quiz> quizlist;

    public ManageQuizzesController() {
        this.dbAccess = Main.getDBaccess();
        quizDAO = new QuizDAO(dbAccess);
    }

    public void setup() {
        ArrayList<Quiz> allQuizzes = quizDAO.getAll();
        for (Quiz quiz : allQuizzes) {
            quizlist.getItems().add(quiz);
        }
        quizlist.getSelectionModel().selectFirst();
    }

    //TJ menu knop terug naar menu
    public void doMenu(ActionEvent actionEvent) {
        Main.getSceneManager().showWelcomeScene();
    }

    public void doCreateQuiz(){}

    public void doUpdateQuiz(){
        this.dbAccess = Main.getDBaccess();
        this.quizDAO = new QuizDAO(dbAccess);
    }

    public void doDeleteQuiz(){}
}