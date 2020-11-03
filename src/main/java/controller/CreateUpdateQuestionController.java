package controller;

import database.mysql.DBAccess;
import database.mysql.QuestionDAO;
import database.mysql.QuizDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.MenuItem;
import model.Question;
import model.Quiz;
import view.Main;

//import javax.management.openmbean.CompositeData;
import java.util.List;

public class CreateUpdateQuestionController {



    @FXML
    TextField warningText;
    private QuestionDAO questionDAO;
    private DBAccess dbAccess;
    private Question question;
    private QuizDAO quizDAO;

    @FXML
    private Label titleLabel;
    @FXML
    private TextField vraagnummerTextfield;
    @FXML
    private TextField vraagTextfield;
    @FXML
    private TextField antwoordCorrectTextfield;
    @FXML
    private TextField antwoordOnjuist1Textfield;
    @FXML
    private TextField antwoordOnjuist2Textfield;
    @FXML
    private TextField antwoordOnjuist3Textfield;
    @FXML
    public ComboBox quizlist = new ComboBox();

    public CreateUpdateQuestionController() {
        questionDAO = new QuestionDAO(Main.getDBaccessMySql());
    }


    // HL - To edit selected customer, all fields are filled with values from the database

    public void setup(Question question) {
        titleLabel.setText("Wijzig vraag");
        vraagnummerTextfield.setText(String.valueOf(question.getIdQuestion()));
        vraagTextfield.setText(question.getDescription());
        antwoordCorrectTextfield.setText(question.getAnswerRight());
        antwoordOnjuist1Textfield.setText(question.getAnswerWrong1());
        antwoordOnjuist2Textfield.setText(question.getAnswerWrong2());
        antwoordOnjuist3Textfield.setText(question.getAnswerWrong3());
        quizlist.setPromptText("Wijzig de bijbehorende quiz:");
        fillComboBoxQuizzes();
    }

    public void fillComboBoxQuizzes (){
        List<Quiz> allQuizzes = quizDAO.getAll();
        ObservableList<Quiz> quizObservableList =
                FXCollections.observableArrayList(allQuizzes);
        quizlist.setItems(quizObservableList);
    }

    //TJ menu knop terug naar menu
    public void doMenu(ActionEvent actionEvent) {
        Main.getSceneManager().showWelcomeScene();
    }

    // HL - to create a new question

    private void createQuestion() {
        StringBuilder warningText = new StringBuilder();
        boolean correcteInvoer = true;
        String vraag = vraagTextfield.getText();
        String correctAntwoord = antwoordCorrectTextfield.getText();
        String antwoordOnjuist1 = antwoordOnjuist1Textfield.getText();
        String antwoordOnjuist2 = antwoordOnjuist2Textfield.getText();
        String antwoordOnjuist3 = antwoordOnjuist3Textfield.getText();


        if (vraag.isEmpty() || correctAntwoord.isEmpty() || antwoordOnjuist1.isEmpty() || antwoordOnjuist2.isEmpty() || antwoordOnjuist3.isEmpty()) {
            warningText.append("Alle velden moeten worden ingevuld!\n");
            Alert foutmelding = new Alert(Alert.AlertType.ERROR);
            foutmelding.setContentText(warningText.toString());
            foutmelding.show();
            correcteInvoer = false;
            question = null;
        } else {
            question = new Question(vraag, correctAntwoord, antwoordOnjuist1, antwoordOnjuist2,
                    antwoordOnjuist3);
        }
    }

    // HL - Check if current question already exists and create it if it doesn't.

    public void doStoreQuestion(ActionEvent actionEvent) {
        createQuestion();
        if (question != null) {
            if (vraagnummerTextfield.getText().isEmpty()) {
                questionDAO.storeOne(question);
                vraagnummerTextfield.setText(String.valueOf(question.getIdQuestion()));
                Alert saved = new Alert(Alert.AlertType.INFORMATION);
                saved.setContentText("Vraag is opgeslagen");
                saved.show();
            } else {
                int id = Integer.parseInt(vraagnummerTextfield.getText());
                question.setIdQuestion(id);
                questionDAO.updateQuestion(question);
                Alert saved = new Alert(Alert.AlertType.INFORMATION);
                saved.setContentText("Vraag gewijzigd");
                saved.show();
            }
        }

        //    public void doCreateUpdateQuestion(Question question) {
        //    }

    }

    public void doBack(ActionEvent actionEvent) {
        Main.getSceneManager().showManageQuestionsScene();
    }
}