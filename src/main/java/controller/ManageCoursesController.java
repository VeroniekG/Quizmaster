package controller;

import database.mysql.CourseDAO;
import database.mysql.DBAccess;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import model.Course;
import view.Main;

import java.util.ArrayList;

public class ManageCoursesController {

    private DBAccess dbAccess;
    private CourseDAO courseDAO;
    private Course course;

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

    public void doCreateCourse(ActionEvent actionEvent) {
        Main.getSceneManager().showCreateUpdateCourseScene(course);
    }

    public void doUpdateCourse(ActionEvent event) {
        Course course = courseList.getSelectionModel().getSelectedItem();
        Main.getSceneManager().showCreateUpdateCourseScene(course);
    }

    public void doDeleteCourse(ActionEvent event) {
        courseList.getItems().remove(courseList.getSelectionModel().getSelectedItem());
        courseDAO.deleteRow(course);
    }

    //TJ menu knop terug naar menu
    public void doMenu(ActionEvent actionEvent) {
        Main.getSceneManager().showWelcomeScene();
    }

}

