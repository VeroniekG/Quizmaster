package controller;

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
import model.Session;
import view.Main;
import java.util.*;

public class FillOutQuizController {


    private Quiz quiz;
    private static final Session SESSION = Session.getInstance();

    @FXML
    private Label titleLabel;
    @FXML
    private TextArea questionArea;


    int index = 0;
    int vraagCorrect = 0;
    int vraagIncorrect = 0;
    int numberofQuizmade = 0;
    Question currentQuestion;

    List<String> allAnswers;
    ArrayList<Question> allQuestions;
    ArrayList<String> antwoordenGeshuffled = new ArrayList<>();

    QuestionDAO questionDAO = new QuestionDAO(Main.getDBaccessMySql());
    QuizDAO quizDAO = new QuizDAO(Main.getDBaccessMySql());

    public FillOutQuizController() {
        allAnswers = new ArrayList<>();
    }

    //HL - this method shows a question and all answers in textarea
    public void setup(Quiz quiz) {
        allQuestions = questionDAO.getAll();
        titleLabel.setText("Vraag " + (index + 1));
        toonAntwoorden(index);
    }

    // HL - Method that retrieves answers and displays them randomly
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

    //HL - this method checks if answer correct/incorrect and adds to array answers given


    private void checkAnswer (int givenAnswer){

        if (antwoordenGeshuffled.get(givenAnswer).equals(currentQuestion.getAnswerRight())){
            vraagCorrect++;
        } else {
            vraagIncorrect++;
        }
        allAnswers.add(antwoordenGeshuffled.get(givenAnswer));

    }

    //HL - methods to register answers

    public void doRegisterA() {
       checkAnswer(0);
       doNextQuestion();

    }

    public void doRegisterB() {
        checkAnswer(1);
        doNextQuestion();
    }

    public void doRegisterC() {
        checkAnswer(2);
        doNextQuestion();
    }

    public void doRegisterD() {
        checkAnswer(3);
        doNextQuestion();
    }


    public void numberWrongCorrect (){
        ArrayList<String> gemaakteQuizzes = new ArrayList();

        gemaakteQuizzes.add(String.valueOf(vraagCorrect));
        gemaakteQuizzes.add(String.valueOf(vraagIncorrect));
    }




    public void doNextQuestion() {
        if (index < allQuestions.size() - 1) {
            index ++;
            toonAntwoorden(index);
        } else {
            Alert bijEinde = new Alert(Alert.AlertType.INFORMATION);
            bijEinde.setContentText("Dit is de laatste vraag");
            bijEinde.show();
            numberofQuizmade++;
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

    public HashMap<String, Integer> getNumberofQuizMadeMap(){
        HashMap<String, Integer> map = new HashMap<>();
        map.put(SESSION.getLoggedInUser().getFirstName(), getNumberofQuizmade());
        return map;
    }



    public List<String> getAllAnswers() {
        return allAnswers;
    }

    public ArrayList<Question> getAllQuestions() {
        return allQuestions;
    }

    public int getNumberofQuizmade() {
        return numberofQuizmade;
    }

    public int getVraagCorrect() {
        return vraagCorrect;
    }

    public int getVraagIncorrect() {
        return vraagIncorrect;
    }

    //TJ menu knop terug naar menu
    public void doMenu (ActionEvent actionEvent){
        Main.getSceneManager().showWelcomeScene();
    }
}