package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import model.UserMenu;
import service.WelcomeService;
import view.Main;

/**
 * Controller for welcomeScene view. Controls data flow and updates the view. The view is set up by
 * calling method {@link #setup()}, which depends on {@link WelcomeService} for creation of the
 * taskmenu and welcome message.
 *
 * @author Huub van Thienen, Daniel Leertouwer
 * @version 1.0.14
 * @see WelcomeService
 * @since 1.0
 */
public class WelcomeController {

    private final WelcomeService welcomeService;

    @FXML
    public Button logoutButton;
    @FXML
    MenuButton taskMenuButton;
    @FXML
    private Label welcomeLabel;

    public WelcomeController() {
        welcomeService = new WelcomeService();
    }

    public void setup() {
        setWelcomeLabel();
        setWelcomeMenu();
    }

    public void setWelcomeLabel() {
        welcomeLabel.setText(welcomeService.createWelcomeMessage());
    }

    public void setWelcomeMenu() {
        UserMenu menu = welcomeService.createWelcomeMenu(taskMenuButton);
        taskMenuButton.getItems().addAll(menu.getMenuItems());
    }

    public void doLogout() {
        welcomeService.invalidateSession();
        Main.getSceneManager().showLoginScene();
    }

}