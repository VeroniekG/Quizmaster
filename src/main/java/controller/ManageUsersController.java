package controller;

import database.mysql.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import model.Role;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.Main;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ManageUsersController {

    private static final Logger LOGGER = LogManager.getLogger(ManageUsersController.class);
    public static User selectedUser;
    @FXML
    private ListView<User> userList;
    private UserDAO userDAO;

    public ManageUsersController() {
        userList = new ListView<>();
        userDAO = new UserDAO(Main.getDBaccessMySql());
    }

    public void setup() {
        populateList();
        if (selectedUser == null) {
            selectFirstListItem();
        } else {
            userList.getSelectionModel().select(selectedUser);
        }
        addEventHandlers();
    }

    public void doMenu() {
        Main.getSceneManager().showWelcomeScene();
    }

    public void doCreateUser() {
        selectedUser = new User("", "", "", "", Role.STUDENT);
        Main.getSceneManager().showCreateUpdateUserScene(selectedUser);
    }

    public void doUpdateUser() {
        updateSelectedUser();
        if (isNotCurrentUser()) {
            Main.getSceneManager().showCreateUpdateUserScene(selectedUser);
        } else {
            showAlertCannotUpdate();
        }
    }

    public void doDeleteUser() {
        updateSelectedUser();
        if (isNotCurrentUser()) {
            Optional<ButtonType> buttonType = showAlertDelete(selectedUser);
            ButtonType buttonPressed = buttonType.orElse(ButtonType.CANCEL);
            if (buttonPressed == ButtonType.OK) {
                userDAO.deleteOne(selectedUser);
                populateList();
                selectFirstListItem();
            } else {
                showAlertCancelled();
                updateSelectedUser();
            }
        } else {
            showAlertCannotUpdate();
        }

    }

    public void populateList() {
        List<User> allUsers = userDAO.getAll();
        Collections.sort(allUsers, new User.UserNameComparator());
        ObservableList<User> userObservableList = FXCollections.observableArrayList(allUsers);
        userList.setItems(userObservableList);
    }

    public void selectFirstListItem() {
        // Select the first item to avoid a NullPointerException
        userList.getSelectionModel().selectFirst();
        updateSelectedUser();
    }

    public void updateSelectedUser() {
        selectedUser = userList.getSelectionModel().getSelectedItem();
    }

    public void addEventHandlers() {
        EventHandler<InputEvent> selectionHandler = inputEvent -> {
            updateSelectedUser();
        };
        userList.addEventHandler(MouseEvent.MOUSE_CLICKED, selectionHandler);
        userList.addEventHandler(TouchEvent.TOUCH_PRESSED, selectionHandler);
        userList.addEventHandler(KeyEvent.KEY_PRESSED, selectionHandler);
    }

    private Optional<ButtonType> showAlertDelete(User user) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Weet je het zeker?");
        alert.setTitle("Meldingvenster");
        alert.setHeaderText("Gebruiker verwijderen?");
        return alert.showAndWait(); // alert.showAndWait() returns Optional<ButtonType>
    }

    private void showAlertCancelled() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "");
        alert.setTitle("Meldingvenster");
        alert.setHeaderText("Geannuleerd");
        alert.setContentText("Gebruiker niet verwijderd");
        alert.show();
    }

    private void showAlertCannotUpdate() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Meldingvenster");
        alert.setHeaderText("Waarschuwing");
        alert.setContentText("Je bent ingelogd als '" + selectedUser.getUserName() + "'. " +
                "Gebruiker kan niet worden bewerkt of verwijderd!");
        alert.show();
    }

    public boolean isNotCurrentUser() {
        boolean isNotCurrentUser = true;
        User currentUser = Main.getCurrentUser();
        if (currentUser.equals(selectedUser)) {
            isNotCurrentUser = false;
        }
        return isNotCurrentUser;
    }

    public static void setSelectedUser(User selectedUser) {
        ManageUsersController.selectedUser = selectedUser;
    }

    private enum ButtonOperation {
        OK, CANCEL, NONE;
    }

}
