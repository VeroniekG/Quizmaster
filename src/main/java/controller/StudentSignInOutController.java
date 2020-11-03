package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import model.Course;
import service.StudentSignInOutService;
import view.Main;

public class StudentSignInOutController {

    private final StudentSignInOutService studentSignInOutService;

    @FXML
    private ListView<Course> signedOutCourseList;
    @FXML
    private ListView<Course> signedInCourseList;
    @FXML
    private Button buttonSignIn;
    @FXML
    private Button buttonSignOut;

    public StudentSignInOutController() {
        studentSignInOutService = new StudentSignInOutService();
        signedOutCourseList = new ListView<>();
        signedInCourseList = new ListView<>();
    }

    public void setup() {
        studentSignInOutService.createSignedOutCourseList(signedOutCourseList);
        studentSignInOutService.createSignedInCoursesList(signedInCourseList);

        // Enable multiselect to sign in/out for multiple courses at once.
        signedInCourseList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        signedOutCourseList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        //        // Populate the lists
        //        List<Course> coursesSignedInFor = studentSignInOutService
        //        .createSignedInCoursesList();
        //        signedInCourseList = studentSignInOutService.createListView(signedInCourseList,
        //                coursesSignedInFor);
        //        List<Course> coursesSignedOutFor = studentSignInOutService
        //        .createSignedOutCoursesList();
        //        signedOutCourseList = studentSignInOutService.createListView
        //        (signedOutCourseList, coursesSignedOutFor)

        //        List<Course> coursesSignedOut = studentSignInOutService
        //        .createSignedOutCoursesList();
        //        ObservableList<Course> coursesSignedInObservableList =
        //                FXCollections.observableArrayList(coursesSignedIn);
        //        signedInCourseList.setItems(coursesSignedInObservableList);
        //        ObservableList<Course> coursesSignedOutObservableList =
        //                FXCollections.observableArrayList(coursesSignedIn);
        //        signedOutCourseList.setItems(coursesSignedOutObservableList);
    }

    //TJ menu knop terug naar menu
    public void doMenu(ActionEvent actionEvent) {
        Main.getSceneManager().showWelcomeScene();
    }

    public void doSignIn() {
    }

    public void doSignOut() {
    }

}
