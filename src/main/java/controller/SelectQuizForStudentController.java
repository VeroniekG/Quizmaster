package controller;

import database.mysql.CourseDAO;
import database.mysql.DBAccess;
import database.mysql.QuizDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Course;
import model.Quiz;
import model.Session;
import model.User;
import view.Main;

import java.util.ArrayList;
import java.util.List;

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

    public List<Quiz> getQuizzesForStudent(){
        // Id van ingelogde gebruiker ophalen
        int idUser = SESSION.getLoggedInUser().getIdUser();
        // Alle cursussen ophalen waar de gebruiker voor ingeschreven is
        List<Course> courses = courseDAO.getCoursesForUserWithId(idUser);
        // Een ArrayList om het resultaat in te bewaren
        List<Quiz> allQuizzesForStudent = new ArrayList<>();
        // Voor iedere cursus de bijbehorende quizzen ophalen
        for (Course course : courses) {
            int idCourse = course.getIdCourse();
            List<Quiz> quizzesForCourse = quizDAO.getQuizzesForCourseWithId(idCourse);
            // De opgehaalde quizzen toevoegen aan de ArrayList met het eindresultaat
            allQuizzesForStudent.addAll(quizzesForCourse);
        }
        return allQuizzesForStudent;
    }



    public void setup() {
//        ArrayList<Quiz> allQuizzes = quizDAO.getAll();
//        for (Quiz quiz : allQuizzes) {
//            quizList.getItems().add(quiz);
//        }
        ObservableList<Quiz> quizzesObservableList = FXCollections.observableList(getQuizzesForStudent());
        SortedList<Quiz> quizzesSortedList = new SortedList<>(quizzesObservableList);
        quizList.setItems(quizzesSortedList);
        quizList.getSelectionModel().selectFirst();

    }

    //TJ menu knop terug naar menu
    public void doMenu(ActionEvent actionEvent) {
        Main.getSceneManager().showWelcomeScene();
    }


    public void doQuiz() {
        Quiz quiz = quizList.getSelectionModel().getSelectedItem();
        Main.getSceneManager().showFillOutQuiz(quiz);}
}
