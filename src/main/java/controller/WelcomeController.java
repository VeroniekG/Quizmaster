package controller;

import database.mysql.DBAccess;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import model.User;
import view.Main;

import static model.MenuItem.*;

/**
 * Controller for welcomeScene view (view.fxml.welcomeScene.fxml). Controls data flow and updates
 * the view.
 *
 * @author thieh, leertod, geertsv
 * @version 1.0.10
 * @see view.Main
 * @see view.SceneManager
 * @see model.Role
 * @see model.User
 * @since 1.0
 */
public class WelcomeController {

    private static final DBAccess dbAccess = Main.getDBaccess();
    public Button logoutButton;
    @FXML
    private Label welcomeLabel;
    @FXML
    private MenuButton taskMenuButton;
    private User currentUser;

    public WelcomeController() {
        currentUser = Main.getCurrentUser();
    }

    public void setup() {
        setWelcomeText();
        showCurrentUserMenu();
    }

    public void doLogout() {
        Main.getSceneManager().showLoginScene();
    }

    public void setWelcomeText() {
        StringBuilder welcomeText = new StringBuilder("Welkom " + currentUser.getUserName());
        welcomeText.append("! Je bent ingelogd als " + currentUser.getRole().getRoleName());
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
        MenuItem menuItem1 = new MenuItem(STUDENTSIGNINOUT.getMenuItemName());
        menuItem1.setOnAction(event -> Main.getSceneManager().showStudentSignInOutScene());
        MenuItem menuItem2 = new MenuItem(SELECTQUIZFORSTUDENT.getMenuItemName());
        menuItem2.setOnAction(event -> Main.getSceneManager().showSelectQuizForStudent());
        taskMenuButton.getItems().addAll(menuItem1, menuItem2);
    }

    public void showCoordinatorMenu() {
        MenuItem menuItem1 = new MenuItem(COORDINATORDASHBOARD.getMenuItemName());
        menuItem1.setOnAction(event -> Main.getSceneManager().showCoordinatorDashboard());
        MenuItem menuItem2 = new MenuItem(MANAGEQUIZZES.getMenuItemName());
        menuItem2.setOnAction(event -> Main.getSceneManager().showManageQuizScene());
        MenuItem menuItem3 = new MenuItem(MANAGEQUESTIONS.getMenuItemName());
        menuItem3.setOnAction(event -> Main.getSceneManager().showManageQuestionsScene());
        taskMenuButton.getItems().addAll(menuItem1, menuItem2, menuItem3);
    }

    public void showAdministratorMenu() {
        MenuItem menuItem1 = new MenuItem(MANAGECOURSES.getMenuItemName());
        menuItem1.setOnAction(event -> Main.getSceneManager().showManageCoursesScene());
        MenuItem menuItem2 = new MenuItem(MANAGEGROUPS.getMenuItemName());
        menuItem2.setOnAction(event -> Main.getSceneManager().showManageGroupsScene());
        taskMenuButton.getItems().addAll(menuItem1, menuItem2);
    }

    public void showSystemAdministratorMenu() {
        MenuItem menuItem1 = new MenuItem(MANAGEUSERS.getMenuItemName());
        menuItem1.setOnAction(event -> Main.getSceneManager().showManageUserScene());
        taskMenuButton.getItems().add(menuItem1);
    }

}
