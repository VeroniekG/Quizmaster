package controller;

import database.couchdb.DBAccess;
import database.couchdb.QuizResultCouchDBDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Pair;
import model.Question;
import model.Quiz;
import model.QuizResult;
import view.Main;

import java.util.ArrayList;
import java.util.List;

public class StudentFeedbackController {
    private DBAccess couchDB;
    private QuizResultCouchDBDAO quizResultCouchDBDAO;
    public ArrayList<Question> feedbackQuestions;
    public ArrayList<String> feedbackAnswers;
    private FillOutQuizController quizcontroller;


    @FXML
    private Label feedbackLabel;
    @FXML
    ListView<String> feedbackList;

    //@AuthorVG - quizresult shows question, correct answer, answer given
    public void setup(FillOutQuizController quizcontroller) {
        feedbackLabel.setText("Quiz Resultaat");
        this.quizcontroller = quizcontroller;
        populateList();
    }

    public void populateList() {
        feedbackQuestions = new ArrayList<>(quizcontroller.getAllQuestions());
        feedbackAnswers = new ArrayList<>(quizcontroller.getAllAnswers());
        for (int i = 0; i < feedbackQuestions.size(); i++) {
            Question question = feedbackQuestions.get(i);
            String answer = feedbackAnswers.get(i);
            feedbackList.getItems().add("Vraag: " + question.getDescription() + " Goede antwoord: " + question.getAnswerRight());
            feedbackList.getItems().add("Jouw antwoord: \n" + answer);
        }
    }


    //     QuizResult quizResult = quizResultCouchDBDAO.getQuizResult("1");



    //TJ menu knop terug naar menu
    public void doMenu(ActionEvent actionEvent) {
        Main.getSceneManager().showWelcomeScene();
    }

}
