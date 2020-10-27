package controller;

import database.mysql.CourseDAO;
import database.mysql.DBAccess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import model.Course;
import view.Main;

import java.util.ArrayList;

public class ManageCoursesController {

    public Button newCourseButton;
    public Button menuButton;
    @FXML
    ListView<Course> courseList;
    private DBAccess dbAccess;
    private CourseDAO courseDAO;

    public ManageCoursesController() {
        this.dbAccess = Main.getDBaccess();
        courseDAO = new CourseDAO(dbAccess);
    }

    public void setup() {
        ArrayList<Course> allCourses = courseDAO.getAll();
        for (Course course : allCourses) {
            courseList.getItems().add(course);
        }
        courseList.getSelectionModel().selectFirst();

    }

    public void doCreateCourse() {
    }

    public void doUpdateCourse() {
        this.dbAccess = Main.getDBaccess();
        this.courseDAO = new CourseDAO(dbAccess);
    }

    public void doDeleteCourse() {
    }

    //TJ menu knop terug naar menu
    public void doMenu(ActionEvent actionEvent) {
        Main.getSceneManager().showWelcomeScene();
    }

}

