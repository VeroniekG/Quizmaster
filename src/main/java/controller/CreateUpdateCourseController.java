package controller;

import database.mysql.CourseDAO;
import javafx.fxml.FXML;
import database.mysql.DBAccess;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Course;
import view.Main;

public class CreateUpdateCourseController {
    private DBAccess dbAccess;
    private CourseDAO courseDAO;
    private Course course;
    @FXML
    private Label titleLabel;
    @FXML
    private Button menuButton;
    @FXML
    private Button makeButton;
    @FXML
    private TextField courseNameTextfield;
    @FXML
    private TextField courseIdTextfield;

    public CreateUpdateCourseController() {
        courseDAO = new CourseDAO(Main.getDBaccess());
    }

    public void setup(Course course) {
        courseIdTextfield.setText(String.valueOf(course.getIdCourse()));
        courseNameTextfield.setText(courseNameTextfield.getSelectedText());
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

