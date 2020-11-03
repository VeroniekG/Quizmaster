package controller;

import database.couchdb.DBAccess;
import database.couchdb.QuizResultCouchDBDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Question;
import model.Quiz;
import model.QuizResult;
import view.Main;

import java.util.ArrayList;

public class StudentFeedbackController {
    private DBAccess couchDB;
    private QuizResultCouchDBDAO quizResultCouchDBDAO;
    private ArrayList<Question> feedbackQuestions;
    private ArrayList<String> feedbackAnswers;


    @FXML
    private Label feedbackLabel;
    @FXML
    TextField feedbackList;

 // uit couchdb ophalen
    //@AuthorVG - quizresult need to show quiz attempt and result
    //result = correct answers / given answers

    public void setup() {
        feedbackQuestions = new FillOutQuizController().getAllQuestions();
        feedbackAnswers = (ArrayList<String>) new FillOutQuizController().getAllAnswers();
        for (int i = 0; i < feedbackQuestions.size(); i++) {
            feedbackQuestions.get(i);
            System.out.println(feedbackQuestions);
            System.out.println(feedbackAnswers);

        }





    }
    //method get results from couchDB and fill feedbacklist:
  //  public void populateList(){
   //     QuizResult quizResult = quizResultCouchDBDAO.getQuizResult("1");

      //  feedbackList


    //TJ menu knop terug naar menu
    public void doMenu(ActionEvent actionEvent) {
        Main.getSceneManager().showWelcomeScene();
    }
}

