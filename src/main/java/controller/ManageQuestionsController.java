package controller;

import database.mysql.DBAccess;
import database.mysql.QuestionDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Question;
import view.Main;

import java.util.ArrayList;
import java.util.List;


public class ManageQuestionsController {

    @FXML
    ListView<Question> questionsList;

    @FXML
    TextField warningText;

    private QuestionDAO questionDAO;
    private DBAccess dbAccess;


    public ManageQuestionsController(){
        this.dbAccess = Main.getDBaccess();
        questionDAO = new QuestionDAO(dbAccess);
    }

    public void setup() {
        List<Question> allQuestions = questionDAO.getAll();
        ObservableList<Question> questionObservableList = FXCollections.observableArrayList(allQuestions);
        questionsList.setItems(questionObservableList);
        questionsList.getSelectionModel().selectFirst();
    }

    //TJ menu knop terug naar menu
    public void doMenu(ActionEvent actionEvent) {
        Main.getSceneManager().showWelcomeScene();
    }

    public void doCreateQuestion(){

    }

    public void doUpdateQuestion(){
    }

    public void doDeleteQuestion(){}
}
