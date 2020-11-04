package controller;

import database.mysql.CourseDAO;
import database.mysql.DBAccess;
import database.mysql.QuizDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Quiz;
import model.Session;
import view.Main;

import java.util.ArrayList;

public class SelectQuizForStudentController {
    private static final Session SESSION = Session.getInstance();
    private QuizDAO quizDAO;
    private DBAccess dbAccess;
    private CourseDAO courseDAO;

    @FXML
    ListView<Quiz> quizList;

    public SelectQuizForStudentController() {
        this.dbAccess = Main.getDBaccessMySql();
        quizDAO = new QuizDAO(dbAccess);
        courseDAO = new CourseDAO(dbAccess);
    }

    /*public List<Quiz> getQuizzesForStudent(User user){
        List<Course> courseList = courseDAO.getCoursesForUserWithId(user.getIdUser());
    // list met quizzen maken voor alle curssussen uit courselist
        List<Quiz> quizList = new ArrayList<>();
        for (Course course : courseList) {
            int idCourse = course.getIdCourse();
          //lijst ophalen met quizzen voor cursus via quizDao met idCourse

        }

    }*/


    // @TJ * public void setup() {} *  origineel,  rest zelf toegevoegd
    public void setup() {
        ArrayList<Quiz> allQuizzes = quizDAO.getAll();
        for (Quiz quiz : allQuizzes) {
            quizList.getItems().add(quiz);
        }
        quizList.getSelectionModel().selectFirst();

    }

    //TJ menu knop terug naar menu
    public void doMenu(ActionEvent actionEvent) {
        Main.getSceneManager().showWelcomeScene();
    }

    // @TJ *public void doQuiz() {}* origineel, rest zelf toegevoegd
    public void doQuiz() {
        Quiz quiz = quizList.getSelectionModel().getSelectedItem();
        Main.getSceneManager().showFillOutQuiz(quiz);}
}
