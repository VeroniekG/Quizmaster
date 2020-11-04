package controller;

import database.mysql.CourseDAO;
import database.mysql.DBAccess;
import database.mysql.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.MenuItem;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;
import view.Main;

import java.util.ArrayList;
import java.util.List;

public class CreateUpdateCourseController {

    private DBAccess dbAccess;
    private CourseDAO courseDAO;
    private Course course;
    private UserDAO userDAO;
    private Role role;
    private User coordinator;
    private User user;

    @FXML
    Label titleLabel;
    @FXML
    Button menuButton;
    @FXML
    Button saveButton;
    @FXML
    TextField courseNameTextfield;
    @FXML
    TextField courseIdTextfield;
    @FXML
    MenuButton coordinatorList;


    public CreateUpdateCourseController() {
        courseDAO = new CourseDAO(Main.getDBaccessMySql());
        userDAO = new UserDAO(Main.getDBaccessMySql());
    }

    public void setup(Course course) {
        titleLabel.setText("Wijzig cursus");
        courseIdTextfield.setText(String.valueOf(course.getIdCourse()));
        courseNameTextfield.setText((String.valueOf(course.getCourseName())));
        populateList();
    }

    //@VG-dropdown list coordinators
    public void populateList() {
        List<User> allCoordinators = userDAO.getUserByRole();
        for (User user : allCoordinators) {
            MenuItem item = new MenuItem(user.getFirstName() +" "+ user.getLastName());
            item.setOnAction(event -> {
                coordinator = user;
                coordinatorList.setText(coordinator.getLastName() + coordinator.getFirstName());
            });
            coordinatorList.getItems().add(item);
        }
    }

    //TJ menu knop terug naar menu
    public void doMenu(ActionEvent actionEvent) {
        Main.getSceneManager().showManageCoursesScene();
    }

    public void createCourse() {
        StringBuilder warningtext = new StringBuilder();
        boolean correct = true;
        String coursename = courseNameTextfield.getText();
        if (coursename.isEmpty()) {
            warningtext.append("Graag een cursusnaam invoeren\n");
            Alert foutmelding = new Alert(Alert.AlertType.ERROR);
            foutmelding.setContentText(warningtext.toString());
            foutmelding.show();
            course = null;
        } else {
            course = new Course(coursename);
        }
    }

    //@VG check in DB if idCourse already exist. If so --> update course  If not --> new course
    public void doStoreCourse(ActionEvent actionEvent) {
        createCourse();
        if (course != null) {
            if (courseIdTextfield.getText().isEmpty()) {
                courseDAO.storeOne(course);
                courseIdTextfield.setText(String.valueOf(course.getIdCourse()));
                Alert saved = new Alert(Alert.AlertType.INFORMATION);
                saved.setContentText("Cursus opgeslagen");
                saved.show();
            } else {
                int id = Integer.parseInt(courseIdTextfield.getText());
                course.setIdCourse(id);
                courseDAO.updateCourse(course);
                Alert updated = new Alert(Alert.AlertType.INFORMATION);
                updated.setContentText("Cursus is gewijzigd");
                updated.show();
            }
        }
    }

}

