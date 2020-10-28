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

    public CreateUpdateCourseController(){
        this.dbAccess = Main.getDBaccess();
        this.courseDAO = new CourseDAO(dbAccess);
    }

    public void setup(Course course) {
        courseNameTextfield.setText(course.getCourseName());
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

    public void doStoreCourse(ActionEvent actionEvent){
        createCourse();
        if(course !=null){
                courseDAO.storeOne(course);
                Alert saved = new Alert(Alert.AlertType.INFORMATION);
                saved.setContentText("Cursus opgeslagen");
                saved.show();
            }else {
                //courseNameTextfield.setText(course.getCourseName());
                String name = courseNameTextfield.getText();
                course.setCourseName(name);
                courseDAO.updateCourse(course);
                Alert updated = new Alert(Alert.AlertType.INFORMATION);
                updated.setContentText("Cursus is gewijzigd");
                updated.show();
            }
        }
    }

