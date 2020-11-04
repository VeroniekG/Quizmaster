package controller;

import database.couchdb.QuizResultCouchDBDAO;
import database.mysql.QuestionDAO;
import database.mysql.QuizDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import model.Question;
import model.Quiz;
import model.QuizResult;
import view.Main;
import java.util.*;

public class FillOutQuizController {


    private Quiz quiz;

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

   List<String> allAnswers;

    ArrayList<Question> allQuestions;
    ArrayList<String> antwoordenGeshuffled = new ArrayList<>();

    QuestionDAO questionDAO = new QuestionDAO(Main.getDBaccessMySql());
    QuizDAO quizDAO = new QuizDAO(Main.getDBaccessMySql());

    public FillOutQuizController() {
        allAnswers = new ArrayList<>();
    }

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

        allAnswers.add(antwoordenGeshuffled.get(givenAnswer));

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
//
//        allQuestions;
//        allAnswers;
//    }

    public void numberWrongCorrect (){
        ArrayList<String> gemaakteQuizzes = new ArrayList();

        gemaakteQuizzes.add(String.valueOf(vraagCorrect));
        gemaakteQuizzes.add(String.valueOf(vraagIncorrect));
    }


//    TODO je hoeft maar een correct/incorrect optelsom te maken


    public void doNextQuestion(ActionEvent actionEvent) {
        if (index < allQuestions.size() - 1) {
            index ++;
            toonAntwoorden(index);
        } else {
//            Alert bijEinde = new Alert(Alert.AlertType.INFORMATION);
//            bijEinde.setContentText("Dit is de laatste vraag");
//            bijEinde.show();
            Main.getSceneManager().showStudentFeedback(this);
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

    public List<String> getAllAnswers() {
        return allAnswers;
    }

    public ArrayList<Question> getAllQuestions() {
        return allQuestions;
    }
}