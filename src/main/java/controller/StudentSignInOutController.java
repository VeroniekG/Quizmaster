package controller;

import javafx.fxml.FXML;
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

    public void doMenu() {
        Main.getSceneManager().showWelcomeScene();
    }

    public void doSignIn() {
        studentSignInOutService.setSelection(listViewCoursesSignedOut);
        studentSignInOutService.storeSelectedCoursesForUser();
        studentSignInOutService.updateLists();
    }

    public void doSignOut() {
        studentSignInOutService.setSelection(listViewCoursesSignedUp);
        studentSignInOutService.deleteSelectedCoursesForUser();
        studentSignInOutService.updateLists();
    }

}

