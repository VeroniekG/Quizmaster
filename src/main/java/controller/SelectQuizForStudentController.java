package controller;

import database.mysql.DBAccess;
import database.mysql.QuizDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Quiz;
import view.Main;

import java.util.ArrayList;

public class SelectQuizForStudentController {
    private QuizDAO quizDAO;
    private DBAccess dbAccess;

    @FXML
    ListView<Quiz> quizList;

    public SelectQuizForStudentController() {
        this.dbAccess = Main.getDBaccessMySql();
        quizDAO = new QuizDAO(dbAccess);
    }

    // @TJ * public void setup() {} *  origineel,  rest zelf toegevoegd
    public void setup() {
        ArrayList<Quiz> allQuizzes = quizDAO.getAll();
        for (Quiz quiz : allQuizzes) {
            quizList.getItems().add(quiz);
        }
        quizList.getSelectionModel().selectFirst();

    }

    //TJ menu knop terug naar menu
    public void doMenu(ActionEvent actionEvent) {
        Main.getSceneManager().showWelcomeScene();
    }

    // @TJ *public void doQuiz() {}* origineel, rest zelf toegevoegd
    public void doQuiz() {
        Quiz quiz = quizList.getSelectionModel().getSelectedItem();
        Main.getSceneManager().showFillOutQuiz(quiz);}
}
