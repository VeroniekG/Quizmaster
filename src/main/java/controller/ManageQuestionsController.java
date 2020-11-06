package controller;

import database.mysql.DBAccess;
import database.mysql.QuestionDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Question;
import view.Main;

import java.util.List;

public class ManageQuestionsController {

    @FXML
    public Button newQuestionButton;
    @FXML
    public Button deleteButton;
    @FXML
    public Button menuButton;
    @FXML
    public Button updateButton;
    @FXML
    public Button makeButton;
    @FXML
    ListView<Question> questionsList;

    private QuestionDAO questionDAO;
    private DBAccess dbAccess;
    private Question question;

    public ManageQuestionsController() {
        this.dbAccess = Main.getDBaccessMySql();
        questionDAO = new QuestionDAO(dbAccess);
    }

    public void setup() {
        List<Question> allQuestions = questionDAO.getAll();
        ObservableList<Question> questionObservableList =
                FXCollections.observableArrayList(allQuestions);
        questionsList.setItems(questionObservableList);
        questionsList.getSelectionModel().selectFirst();
    }

    public void doCreateQuestion() {
        Main.getSceneManager().showCreateUpdateQuestionScene(question);
    }

    public void doUpdateQuestion(ActionEvent actionEvent) {
        Question question = questionsList.getSelectionModel().getSelectedItem();
        Main.getSceneManager().showCreateUpdateQuestionScene(question);
    }

    public void doDeleteQuestion() {
        Question selectedQuestion = questionsList.getSelectionModel().getSelectedItem();
        questionsList.getItems().remove(selectedQuestion);
        questionDAO.deleteQuestion(selectedQuestion);
    }

    //TJ menu knop terug naar menu
    public void doMenu(ActionEvent actionEvent) {
        Main.getSceneManager().showWelcomeScene();
    }

}
