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

import java.util.Collections;
import java.util.List;

public class CreateUpdateCourseController {

    private DBAccess dbAccess;
    private CourseDAO courseDAO;
    private Course course;
    private UserDAO userDAO;
    private User coordinator;

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
        comboBoxCoordinator = new ComboBox<>();
    }

    public void setup(Course course) {
        if (course != null){
        titleLabel.setText("Wijzig cursus");
        courseIdTextfield.setText(String.valueOf(course.getIdCourse()));
        courseNameTextfield.setText((String.valueOf(course.getCourseName())));
            List<User> allCoordinators = userDAO.getUsersByRole(Role.COORDINATOR);
            ObservableList<User> coordinatorObservableList =
                    FXCollections.observableArrayList(allCoordinators);
            comboBoxCoordinator.setItems(coordinatorObservableList);
        } else {
            titleLabel.setText("Nieuwe cursus");
            comboBoxCoordinator.getSelectionModel().selectFirst();
        }
    }

    public void createCourse() {
        boolean correct = true;
        String coursename = courseNameTextfield.getText();
        if (coursename.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "");
            alert.setTitle("Meldingvenster");
            alert.setHeaderText("Waarschuwing");
           alert.setContentText("Graag een cursusnaam invoeren");
            alert.show();
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
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "");
                alert.setTitle("Meldingvenster");
                alert.setContentText("Cursus opgeslagen");
                ;
                alert.show();
            } else {
                int id = Integer.parseInt(courseIdTextfield.getText());
                course.setIdCourse(id);
                courseDAO.updateCourse(course);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Meldingvenster");
                alert.setContentText("Cursus is gewijzigd");
                alert.show();
            }
        }
    }
    //TJ menu knop terug naar menu
    public void doMenu(ActionEvent actionEvent) {
        Main.getSceneManager().showManageCoursesScene();
    }

}

