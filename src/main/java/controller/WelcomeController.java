package controller;

import database.mysql.DBAccess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import model.User;
import view.Main;

import static model.MenuItem.*;

public class WelcomeController {

    public Button logoutButton;
    @FXML
    private Label welcomeLabel;
    @FXML
    private MenuButton taskMenuButton;
    private User currentUser;
    private DBAccess dbAccess;

    public WelcomeController() {
        this.currentUser = Main.getCurrentUser();
        this.dbAccess = Main.getDBaccess();
    }

    public void setup() {
        setWelcomeText();
        showCurrentUserMenu();
    }

    public void doLogout(ActionEvent actionEvent) {
        dbAccess.closeConnection();
        Main.getSceneManager().showLoginScene();
    }

    public void setWelcomeText() {
        StringBuilder welcomeText = new StringBuilder("Welkom " + currentUser.getUserName());
        welcomeText.append("! Je bent ingelogd als " + currentUser.getRole().toString().toLowerCase());
        welcomeLabel.setText(welcomeText.toString());
    }

    public void showCurrentUserMenu() {
        switch (currentUser.getRole()) {
            case STUDENT:
                showStudentMenu();
                break;
            case COORDINATOR:
                showCoordinatorMenu();
                break;
            case ADMINISTRATOR:
                showAdministratorMenu();
                break;
            case TECHNISCH_BEHEERDER:
                showSystemAdministratorMenu();
                break;
            default:
                showStudentMenu();
                break;
        }
    }

    public void showStudentMenu() {
        MenuItem menuItem1 = new MenuItem(STUDENTSIGNINOUT.toString());
        menuItem1.setOnAction(event -> Main.getSceneManager().showStudentSignInOutScene());
        MenuItem menuItem2 = new MenuItem(SELECTQUIZFORSTUDENT.toString());
        menuItem2.setOnAction(event -> Main.getSceneManager().showSelectQuizForStudent());
        taskMenuButton.getItems().addAll(menuItem1, menuItem2);
    }

    public void showCoordinatorMenu() {
        MenuItem menuItem1 = new MenuItem(COORDINATORDASHBOARD.toString());
        menuItem1.setOnAction(event -> Main.getSceneManager().showCoordinatorDashboard());
        MenuItem menuItem2 = new MenuItem(MANAGEQUIZZES.toString());
        menuItem2.setOnAction(event -> Main.getSceneManager().showManageQuizScene());
        MenuItem menuItem3 = new MenuItem(MANAGEQUESTIONS.toString());
        menuItem3.setOnAction(event -> Main.getSceneManager().showManageQuestionsScene());
        taskMenuButton.getItems().addAll(menuItem1, menuItem2, menuItem3);
    }

    public void showAdministratorMenu() {
        MenuItem menuItem1 = new MenuItem(MANAGECOURSES.toString());
        menuItem1.setOnAction(event -> Main.getSceneManager().showManageCoursesScene());
        MenuItem menuItem2 = new MenuItem(MANAGEGROUPS.toString());
        menuItem2.setOnAction(event -> Main.getSceneManager().showManageGroupsScene());
        taskMenuButton.getItems().addAll(menuItem1, menuItem2);
    }

    public void showSystemAdministratorMenu() {
        MenuItem menuItem1 = new MenuItem(MANAGEUSERS.toString());
        menuItem1.setOnAction(event -> Main.getSceneManager().showManageUserScene());
        taskMenuButton.getItems().add(menuItem1);
    }

}
