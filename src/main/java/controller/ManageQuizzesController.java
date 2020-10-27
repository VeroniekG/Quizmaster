package controller;

import database.mysql.CourseDAO;
import database.mysql.DBAccess;
import database.mysql.QuizDAO;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import model.Quiz;
import view.Main;

import java.util.ArrayList;

public class ManageQuizzesController {
        private QuizDAO quizDAO;
        private DBAccess dbAccess;

        ListView<Quiz> quizlist;

    public ManageQuizzesController() {
        this.dbAccess = Main.getDBaccess();
        quizDAO = new QuizDAO(dbAccess);
    }

    public void setup() {
        this.quizDAO = new QuizDAO(dbAccess);
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