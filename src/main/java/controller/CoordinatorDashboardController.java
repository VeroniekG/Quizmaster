package controller;

import database.mysql.CourseDAO;
import database.mysql.DBAccess;
import database.mysql.QuestionDAO;
import database.mysql.QuizDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import model.Course;
import model.Question;
import model.Quiz;
import view.Main;

import java.util.List;

public class CoordinatorDashboardController {
    private QuizDAO quizDAO;
    private CourseDAO courseDAO;
    private QuestionDAO questionDAO;
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
        questionDAO = new QuestionDAO(Main.getDBaccessMySql());
    }

    //@AuthorVG - retrieve courselist from DB
    // NOT WORKING YET!!  <------------------------------------------------------------------------- Almost working, quick & dirty
    public void setup() {
        populateList();
        Course selectedCourse = courseList.getSelectionModel().getSelectedItem();
        setQuizListByCourse(selectedCourse); // <--------------------------------------------------- Magic :-)
        courseList.getSelectionModel().getSelectedItem().getIdCourse();
        courseList.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Course>() {
                    @Override
                    public void changed(ObservableValue<? extends Course> observableValue,
                                        Course oldCourse, Course newCourse) {
                        System.out.println("Geselecteerde cursus: " + oldCourse + "-> " + newCourse);
                        setQuizListByCourse(newCourse);
                    }
                });
        Quiz selectedQuiz = quizList.getSelectionModel().getSelectedItem();
        setQuestionsListByQuiz(selectedQuiz);
        quizList.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Quiz>() {
                    @Override
                    public void changed(ObservableValue<? extends Quiz> observableValue,
                                        Quiz oldQuiz, Quiz newQuiz) {
                        System.out.println("Geselecteerde quiz: " + observableValue + ", " + oldQuiz + ", " + newQuiz);
                        setQuestionsListByQuiz(newQuiz);
                    }
                });
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
        courseList.getSelectionModel().selectFirst();
    }

    public void setQuizListByCourse(Course course) {
        List<Quiz> quizzes = quizDAO.getQuizzesForCourseWithId(course.getIdCourse());
        ObservableList<Quiz> quizzesObservableList = FXCollections.observableList(quizzes);
        quizList.setItems(quizzesObservableList);
    }
    public void setQuestionsListByQuiz(Quiz quiz) {
        List<Question> questions = questionDAO.getQuestionsForQuizWithId(quiz.getIdCourse());
        ObservableList<Question> questionObservableList = FXCollections.observableList(questions);
        questionList.setItems(questionObservableList);
    }
}


