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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class FillOutQuizController {

    @FXML
    private Label titleLabel;
    @FXML
    private TextArea questionArea;
    @FXML
    private Button doNextQuestion;

    ArrayList<Question> allQuestions;
    int index = 0;

    QuestionDAO questionDAO = new QuestionDAO(Main.getDBaccessMySql());
    QuizDAO quizDAO = new QuizDAO(Main.getDBaccessMySql());

    //HL - show a question and all answers in textarea
    public void setup(Quiz quiz) {
        allQuestions = questionDAO.getAll();
        titleLabel.setText("Vraag " + (index + 1));
        aanroepenAntwoorden(index);
    }

    public void aanroepenAntwoorden(int index) {
        allQuestions.get(index);
        titleLabel.setText("Vraag " + (index + 1));

        Question question = allQuestions.get(index);

        ArrayList<String> antwoordenGeshuffled = new ArrayList<>();
        antwoordenGeshuffled.add(question.getAnswerRight());
        antwoordenGeshuffled.add(question.getAnswerWrong1());
        antwoordenGeshuffled.add(question.getAnswerWrong2());
        antwoordenGeshuffled.add(question.getAnswerWrong3());

        Collections.shuffle(antwoordenGeshuffled);

        questionArea.setText(
                question.getDescription()
                        + "\n A: " + antwoordenGeshuffled.get(0)
                        + "\n B: " + antwoordenGeshuffled.get(1)
                        + "\n C: " + antwoordenGeshuffled.get(2)
                        + "\n D: " + antwoordenGeshuffled.get(3));
    }


    public void doRegisterA() {
    }

    public void doRegisterB() {
    }

    public void doRegisterC() {
    }

    public void doRegisterD() {
    }

    public void doNextQuestion(ActionEvent actionEvent) {
        if (index < allQuestions.size() - 1) {
            index += 1;
            aanroepenAntwoorden(index);
        } else {
            Alert bijEinde = new Alert(Alert.AlertType.INFORMATION);
            bijEinde.setContentText("Dit is de laatste vraag");
            bijEinde.show();
        }
    }

    public void doPreviousQuestion() {
        if (index > 0) {
            index -= 1;
            aanroepenAntwoorden(index);
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