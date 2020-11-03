package controller;

import database.mysql.QuestionDAO;
import database.mysql.QuizDAO;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import model.Question;
import model.Quiz;
import view.Main;
import java.util.*;

public class FillOutQuizController {

    @FXML
    private Label titleLabel;
    @FXML
    private TextArea questionArea;
    @FXML
    private Button doNextQuestion;

    int index = 0;
    int vraagCorrect = 0;
    int vraagIncorrect = 0;
    Question currentQuestion;

    ArrayList<Question> allQuestions;
    ArrayList<String> antwoordenGeshuffled = new ArrayList<>();

    QuestionDAO questionDAO = new QuestionDAO(Main.getDBaccessMySql());
    QuizDAO quizDAO = new QuizDAO(Main.getDBaccessMySql());

    //HL - show a question and all answers in textarea
    public void setup(Quiz quiz) {
        allQuestions = questionDAO.getAll();
        titleLabel.setText("Vraag " + (index + 1));
        toonAntwoorden(index);
    }


    // Method that retrieves answers and displays them randomly
    public void toonAntwoorden(int index) {

        titleLabel.setText("Vraag " + (index + 1));

        currentQuestion = allQuestions.get(index);
        antwoordenGeshuffled.clear();
        antwoordenGeshuffled.add(currentQuestion.getAnswerRight());
        antwoordenGeshuffled.add(currentQuestion.getAnswerWrong1());
        antwoordenGeshuffled.add(currentQuestion.getAnswerWrong2());
        antwoordenGeshuffled.add(currentQuestion.getAnswerWrong3());

        Collections.shuffle(antwoordenGeshuffled);


        questionArea.setText(
                currentQuestion.getDescription()
                        + "\n A: " + antwoordenGeshuffled.get(0)
                        + "\n B: " + antwoordenGeshuffled.get(1)
                        + "\n C: " + antwoordenGeshuffled.get(2)
                        + "\n D: " + antwoordenGeshuffled.get(3));

    }


    private void checkAnswer (int givenAnswer){

        if (antwoordenGeshuffled.get(givenAnswer).equals(currentQuestion.getAnswerRight())){
            vraagCorrect++;
//            Alert correct = new Alert(Alert.AlertType.INFORMATION);
//            correct.setContentText("Correct");
//            correct.show();
        } else {
            vraagIncorrect++;
//            Alert incorrect = new Alert(Alert.AlertType.INFORMATION);
//            incorrect.setContentText("Incorrect");
//            incorrect.show();
        }

    }

    public void doRegisterA() {
       checkAnswer(0);
    }

    public void doRegisterB() {
        checkAnswer(1);
    }

    public void doRegisterC() {
        checkAnswer(2);
    }

    public void doRegisterD() {
        checkAnswer(3);
    }
//
//    public void printResults (){
//        for (int i = 0; i < quizDAO.getAll().size(); i++) {
//            System.out.println(currentQuestion);
//            System.out.println(vraagCorrect);
//        }
//
//    }


    public void doNextQuestion(ActionEvent actionEvent) {
        if (index < allQuestions.size() - 1) {
            index ++;
            toonAntwoorden(index);
        } else {
            Alert bijEinde = new Alert(Alert.AlertType.INFORMATION);
            bijEinde.setContentText("Dit is de laatste vraag");
            bijEinde.show();
        }
    }


    public void doPreviousQuestion() {
        if (index > 0) {
            index -= 1;
            toonAntwoorden(index);
        } else {
            Alert bijBegin = new Alert(Alert.AlertType.INFORMATION);
            bijBegin.setContentText("Dit is de eerste vraag");
            bijBegin.show();
        }
    }

        //TJ menu knop terug naar menu
        public void doMenu (ActionEvent actionEvent){
            Main.getSceneManager().showWelcomeScene();
        }
}