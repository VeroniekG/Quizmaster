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

    @FXML
    public Button newCourseButton;
    @FXML
    public Button menuButton;
    @FXML
    public Button updateButton;
    @FXML
    public Button removeButton;
    @FXML
    ListView<Course> courseList;
    private DBAccess dbAccess;
    private CourseDAO courseDAO;
    private Course course;

    public ManageCoursesController() {
        this.dbAccess = Main.getDBaccessMySql();
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
        Main.getSceneManager().showCreateUpdateCourseScene(course);
    }

    public void doUpdateCourse() {
        Course course = courseList.getSelectionModel().getSelectedItem();
        Main.getSceneManager().showCreateUpdateCourseScene(course);
    }

    //@authorVG - select item and remove from ListView + use deleteCourse() to remove from DB
    public void doDeleteCourse() {
        Course selectedCourse = courseList.getSelectionModel().getSelectedItem();
        courseList.getItems().remove(selectedCourse);
        courseDAO.deleteCourse(selectedCourse);
    }

    //TJ menu knop terug naar menu
    public void doMenu() {
        Main.getSceneManager().showWelcomeScene();
    }

}

