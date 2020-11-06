package controller;

import database.mysql.DBAccess;
import database.mysql.QuizDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import model.Course;
import model.Quiz;
import view.Main;

import java.util.ArrayList;

public class ManageQuizzesController {
        public Button newQuizButton;
        public Button menuButton;
        public Button changeButton;
        public Button deleteButton;
        private QuizDAO quizDAO;
        private DBAccess dbAccess;
        private Quiz quiz;

        @FXML
        ListView<Quiz> quizlist;

    public ManageQuizzesController() {
        this.dbAccess = Main.getDBaccessMySql();
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

    public void doCreateQuiz(ActionEvent actionEvent){
        Main.getSceneManager().showCreateUpdateQuizScene(quiz);

    }
    //@TJ updaten van quizzen
    public void doUpdateQuiz(){
        Quiz quiz = quizlist.getSelectionModel().getSelectedItem();
        Main.getSceneManager().showCreateUpdateQuizScene(quiz);
    }
    //
    public void doDeleteQuiz(ActionEvent actionEvent){
        Quiz selectedQuiz  = quizlist.getSelectionModel().getSelectedItem();
        quizlist.getItems().remove(selectedQuiz);
        quizDAO.deleteQuiz(selectedQuiz);
    }
}