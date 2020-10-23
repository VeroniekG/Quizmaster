package controller;

import javafx.event.ActionEvent;
import model.Course;
import view.Main;

public class CreateUpdateCourseController {

    public void setup(Course course) {}

    //TJ menu knop terug naar menu
    public void doMenu(ActionEvent actionEvent) {
        Main.getSceneManager().showWelcomeScene();
    }

    public void doCreateUpdateCourse() {}
}
