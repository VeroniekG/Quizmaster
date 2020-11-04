package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import model.Course;
import service.StudentSignInOutService;
import view.Main;

/**
 * Updates view studentSignInOut.fxml
 *
 * @author Daniel Leertouwer
 * @version 1.0.3
 */
public class StudentSignInOutController {

    private final StudentSignInOutService studentSignInOutService;

    @FXML
    private ListView<Course> listViewCoursesSignedOut;
    @FXML
    private ListView<Course> listViewCoursesSignedUp;
    @FXML
    private Button buttonSignIn;
    @FXML
    private Button buttonSignOut;

    public StudentSignInOutController() {
        studentSignInOutService = new StudentSignInOutService();
        listViewCoursesSignedOut = new ListView<>();
        listViewCoursesSignedUp = new ListView<>();
    }

    public void setup() {
        studentSignInOutService.setupView(listViewCoursesSignedUp, listViewCoursesSignedOut);
        listViewCoursesSignedUp.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listViewCoursesSignedOut.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }

    public void doMenu(ActionEvent actionEvent) {
        Main.getSceneManager().showWelcomeScene();
    }

    public void doSignIn() {
        studentSignInOutService.setSelection(listViewCoursesSignedOut);
        studentSignInOutService.addSelection();
        studentSignInOutService.update();
    }

    public void doSignOut() {
        studentSignInOutService.setSelection(listViewCoursesSignedUp);
        studentSignInOutService.deleteSelection();
        studentSignInOutService.update();
    }

    public ListView<Course> getListViewCoursesSignedOut() {
        return listViewCoursesSignedOut;
    }

    public ListView<Course> getListViewCoursesSignedUp() {
        return listViewCoursesSignedUp;
    }

}

