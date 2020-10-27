package controller;

import database.mysql.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.Main;

import java.util.Collections;
import java.util.List;

public class ManageUsersController {

    private static final Logger logger = LogManager.getLogger(ManageUsersController.class);

    @FXML
    private ListView<User> userList;
    private UserDAO userDAO;
    private User selectedUser = null;

    public ManageUsersController() {
        userList = new ListView<>();
        userDAO = new UserDAO(Main.getDbAccess());
    }

    public void setup() {
        populateList();
        if (selectedUser == null) {
            selectFirstListItem();
        } else {
            setSelectedUser();
        }
        addEventHandlers();
    }

    public void doMenu(ActionEvent event) {
        Main.getSceneManager().showWelcomeScene();
    }

    public void doCreateUser() {
        //Main.getSceneManager().showCreateUpdateUserScene();
    }

    public void doUpdateUser() {
        Main.getSceneManager().showCreateUpdateUserScene(selectedUser);
    }

    public void doDeleteUser() {
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
        setSelectedUser();
    }

    public void setSelectedUser() {
        selectedUser = userList.getSelectionModel().getSelectedItem();
        logger.info(selectedUser);
    }

    public void addEventHandlers() {
        EventHandler<InputEvent> selectionHandler = inputEvent -> {
            setSelectedUser();
        };
        userList.addEventHandler(MouseEvent.MOUSE_CLICKED, selectionHandler);
        userList.addEventHandler(TouchEvent.TOUCH_PRESSED, selectionHandler);
        userList.addEventHandler(KeyEvent.KEY_PRESSED, selectionHandler);
    }

}
