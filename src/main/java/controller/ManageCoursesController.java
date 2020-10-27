package controller;
import database.mysql.DBAccess;
import database.mysql.CourseDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import view.Main;
import model.Course;
import view.SceneManager;

import java.util.ArrayList;

public class ManageCoursesController {

    public Button newCourseButton;
    public Button menuButton;
    private DBAccess dbAccess;
    private CourseDAO courseDAO;

    @FXML
    ListView<Course> courseList;

    public ManageCoursesController(){
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
        public void doCreateCourse () {
        }

        public void doUpdateCourse () {
        this.dbAccess =Main.getDBaccess();
        this.courseDAO = new CourseDAO(dbAccess);
        }

        public void doDeleteCourse () {
        }

        //TJ menu knop terug naar menu
        public void doMenu (ActionEvent actionEvent){
            Main.getSceneManager().showWelcomeScene();
        }
    }

