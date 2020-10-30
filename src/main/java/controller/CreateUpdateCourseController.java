package controller;

import database.mysql.CourseDAO;
import database.mysql.DBAccess;
import database.mysql.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Course;
import model.Question;
import model.Role;
import model.User;
import view.Main;

import java.util.ArrayList;
import java.util.List;

public class CreateUpdateCourseController {

    private DBAccess dbAccess;
    private CourseDAO courseDAO;
    private Course course;
    private UserDAO userDAO;
    private Role role;
    final ObservableList coordinatorlist = FXCollections.observableArrayList();

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
    ComboBox<User> comboBoxCoordinator;

    public CreateUpdateCourseController() {
        courseDAO = new CourseDAO(Main.getDBaccessMySql());
        userDAO = new UserDAO(Main.getDBaccessMySql());
    }

    public void setup(Course course) {
        populateList();
        titleLabel.setText("Wijzig cursus");
        courseIdTextfield.setText(String.valueOf(course.getIdCourse()));
        courseNameTextfield.setText((String.valueOf(course.getCourseName())));

    }
    //@VG-retrieve users that are coordinator from DB
    public void populateList(){
        List<User> allCoordinators = userDAO.getUserByRole();
        ObservableList<User> coordinatorObservableList =
                FXCollections.observableArrayList(allCoordinators);
        comboBoxCoordinator.setItems(coordinatorObservableList);
        comboBoxCoordinator.getSelectionModel().selectFirst();
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

