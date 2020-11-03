package model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import view.Main;

import static model.Role.*;

/**
 * UserMenuItem Model Object. Enum of which each value contains a MenuItem, a Role the MenuItem is
 * applicable to and a corresponding eventhandler.
 * TODO: Add submenu-items
 *
 * @author Daniel Leertouwer
 * @version 2.0.0
 * @see UserMenu
 * @see MenuItem
 * @see view.SceneManager
 * @since 1.0
 */
public enum UserMenuItem {

    STUDENTSIGNINOUT(new MenuItem("In- of uitschrijven cursus"), STUDENT, event -> {
        Main.getSceneManager().showStudentSignInOutScene();
    }),
    SELECTQUIZFORSTUDENT(new MenuItem("Quiz selecteren"), STUDENT,
            event -> {
                Main.getSceneManager().showSelectQuizForStudent();
            }),
    COORDINATORDASHBOARD(new MenuItem("Coördinator dashboard"), COORDINATOR,
            event -> {
                Main.getSceneManager().showCoordinatorDashboard();
            }),
    MANAGEQUIZZES(new MenuItem("Quizbeheer"), COORDINATOR,
            event -> {
                Main.getSceneManager().showManageQuizScene();
            }),
    MANAGEQUESTIONS(new MenuItem("Vraagbeheer"), COORDINATOR,
            event -> {
                Main.getSceneManager().showManageQuestionsScene();
            }),
    MANAGECOURSES(new MenuItem("Cursusbeheer"), ADMINISTRATOR,
            event -> {
                Main.getSceneManager().showManageCoursesScene();
            }),
    MANAGEGROUPS(new MenuItem("Groepenbeheer"), ADMINISTRATOR,
            event -> {
                Main.getSceneManager().showManageGroupsScene();
            }),
    MANAGEUSERS(new MenuItem("Gebruikersbeheer"), TECHNISCH_BEHEERDER,
            event -> {
                Main.getSceneManager().showManageUserScene();
            });

    private final MenuItem menuItem;
    private final Role menuItemForRole;
    private final EventHandler<ActionEvent> onActionHandler;

    UserMenuItem(MenuItem item, Role role, EventHandler<ActionEvent> handler) {
        menuItem = item;
        menuItemForRole = role;
        onActionHandler = handler;
    }

    @Override
    public String toString() {
        return menuItem.getText();
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public Role getMenuItemForRole() {
        return menuItemForRole;
    }

    public EventHandler<ActionEvent> getOnActionHandler() {
        return onActionHandler;
    }

}
