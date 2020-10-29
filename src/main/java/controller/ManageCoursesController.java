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

    private DBAccess dbAccess;
    private CourseDAO courseDAO;
    private Course course;
    private Course selectedCourse = null;

    @FXML
    public Button newCourseButton;
    @FXML
    public Button menuButton;
    @FXML
    public Button updateButton;
    @FXML
    ListView<Course> courseList;
    @FXML
    public Button removeButton;

    public ManageCoursesController() {
        this.dbAccess = Main.getDBaccess();
        courseDAO = new CourseDAO(dbAccess);
    }

    public void setup() {
        ArrayList<Course> allCourses = courseDAO.getAll();
        for (Course course : allCourses) {
            courseList.getItems().add(course);
        } courseList.getSelectionModel().selectFirst();
    }
    public void setSelectedUser() {
        selectedCourse = courseList.getSelectionModel().getSelectedItem();
    }

    public void doCreateCourse(ActionEvent actionEvent) {
        Main.getSceneManager().showCreateUpdateCourseScene(course);
    }

    public void doUpdateCourse(ActionEvent actionEvent) {
        Course selectedCourse = courseList.getSelectionModel().getSelectedItem();
        Main.getSceneManager().showCreateUpdateCourseScene(selectedCourse);
    }

    //@authorVG - select item and remove from ListView + use deleteCourse() to remove from DB
    public void doDeleteCourse(ActionEvent actionEvent) {
      Course selectedCourse  = courseList.getSelectionModel().getSelectedItem();
        courseList.getItems().remove(selectedCourse);
        courseDAO.deleteCourse(selectedCourse);
    }

    //TJ menu knop terug naar menu
    public void doMenu(ActionEvent actionEvent) {
        Main.getSceneManager().showWelcomeScene();
    }

}

