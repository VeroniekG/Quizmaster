package controller;

import database.mysql.CourseDAO;
import database.mysql.DBAccess;
import database.mysql.QuizDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import model.Course;
import model.Question;
import model.Quiz;
import view.Main;

import java.util.List;

public class CoordinatorDashboardController {
    private QuizDAO quizDAO;
    private CourseDAO courseDAO;
    private DBAccess dbAccess;
    private Quiz quiz;

    @FXML
    private Button updateButtonQuiz;
    @FXML
    private Button newButtonQuestion;
    @FXML
    private Button updateButtonQuestion;
    @FXML
    private Button menuButton;
    @FXML
    private ListView<Course> courseList;
    @FXML
    private ListView<Quiz> quizList;
    @FXML
    private ListView<Question> questionList;

    public CoordinatorDashboardController() {
        courseList = new ListView<>();
        quizList = new ListView<>();
        questionList = new ListView<>();
        courseDAO = new CourseDAO(Main.getDBaccessMySql());
        quizDAO = new QuizDAO(Main.getDBaccessMySql());
        //QuestionDA
    }

    //@AuthorVG - retrieve courselist from DB
    public void setup() {
        populateList();
        courseList.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Course>() {
                    @Override
                    public void changed(ObservableValue<? extends Course> observableValue,
                                        Course oldCourse, Course newCourse) {
                        System.out.println("Geselecteerde cursus: " + observableValue + ", " + oldCourse + ", " + newCourse);
                    }
                });
        getSelectedCourseID();
        quizList.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Quiz>() {
                    @Override
                    public void changed(ObservableValue<? extends Quiz> observableValue,
                                        Quiz oldQuiz, Quiz newQuiz) {
                        System.out.println("Geselecteerde quiz: " + observableValue + ", " + oldQuiz + ", " + newQuiz);
                    }
                });
        questionList.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Question>() {
                    @Override
                    public void changed(ObservableValue<? extends Question> observableValue,
                                        Question oldQuestion, Question newQuestion) {
                    }
                }
        );

    }//VG - select course, retrieve courseID from DB, and retrieve corresponding quizID
    public void getSelectedCourseID() {
        Course selectedCourse = courseList.getSelectionModel().getSelectedItem();
        courseDAO.getOneById(selectedCourse.getIdCourse());
        if (selectedCourse.getIdCourse() == quiz.getIdCourse()){
            List<Quiz> selectedQuiz = quizDAO.getAll();
            ObservableList<Quiz> quizObservableList = FXCollections.observableArrayList(selectedQuiz);
            quizList.setItems(quizObservableList);
        }
    }

    public void doNewQuiz(ActionEvent actionEvent) {
        Quiz selectedQuiz = quizList.getSelectionModel().getSelectedItem();
        Main.getSceneManager().showCreateUpdateQuizScene(selectedQuiz);
    }

    public void doEditQuiz() {
        Quiz selectedQuiz = quizList.getSelectionModel().getSelectedItem();
        Main.getSceneManager().showCreateUpdateQuizScene(selectedQuiz);
    }

    public void doNewQuestion(ActionEvent actionEvent) {
        Main.getSceneManager().showCreateUpdateQuestionScene();
    }

    public void doEditQuestion(ActionEvent actionEvent) {
        Main.getSceneManager().showCreateUpdateQuestionScene();
    }

    //TJ menu knop terug naar menu
    public void doMenu(ActionEvent actionEvent) {
        Main.getSceneManager().showWelcomeScene();
    }

    public void populateList() {
        List<Course> allCourse = courseDAO.getAll();
        ObservableList<Course> courseObservableList = FXCollections.observableArrayList(allCourse);
        courseList.setItems(courseObservableList);
        courseList.getSelectionModel().selectedItemProperty();
    }
}


