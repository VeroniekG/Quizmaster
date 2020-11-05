package controller;

import database.mysql.DBAccess;
import database.mysql.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import model.ApplicationAlert;
import model.Role;
import model.Session;
import model.User;
import view.Main;

import java.util.List;
import java.util.Optional;

/**
 * TODO: Refactor to make use of services
 *
 * @author Daniel Leertouwer
 */
public class ManageUsersController {

    private static final Session session = Session.getInstance();
    private static final DBAccess DB_ACCESS = DBAccess.getInstance();
    private static final UserDAO USER_DAO = new UserDAO(DB_ACCESS);

    @SuppressWarnings("FieldMayBeFinal")
    @FXML
    private ListView<User> userList;

    public ManageUsersController() {
        userList = new ListView<>();
    }

    public void setup() {
        populateList();
        if (session.getSelectedUser() == null) {
            selectFirstListItem();
        } else {
            userList.getSelectionModel().select(session.getSelectedUser());
        }
    }

    public void populateList() {
        List<User> allUsers = USER_DAO.getAll();
        allUsers.sort(new User.UserNameComparator());
        ObservableList<User> userObservableList = FXCollections.observableArrayList(allUsers);
        userList.setItems(userObservableList);
    }

    public void selectFirstListItem() {
        userList.getSelectionModel().selectFirst();
        updateSelectedUser();
    }

    public void updateSelectedUser() {
        session.setSelectedUser(userList.getSelectionModel().getSelectedItem());
    }

    public void doMenu() {
        Main.getSceneManager().showWelcomeScene();
    }

    public void doCreateUser() {
        User user = new User("", "", "", "", Role.STUDENT);
        Main.getSceneManager().showCreateUpdateUserScene(user);
    }

    public void doUpdateUser() {
        updateSelectedUser();
        if (isNotCurrentUser()) {
            Main.getSceneManager().showCreateUpdateUserScene(session.getSelectedUser());
        } else {
            showAlertCannotUpdate();
        }
    }

    public boolean isNotCurrentUser() {
        boolean isNotCurrentUser = true;
        User currentUser = session.getLoggedInUser();
        if (currentUser.equals(session.getSelectedUser())) {
            isNotCurrentUser = false;
        }
        return isNotCurrentUser;
    }

    private void showAlertCannotUpdate() {
        ApplicationAlert alert = new ApplicationAlert.Builder()
                .withAlertType(Alert.AlertType.WARNING)
                .withHeaderText("Waarschuwing")
                .withContentText("Je bent ingelogd als '" + session.getLoggedInUser().getUserName()
                        + "'. Gebruiker kan niet worden bewerkt of verwijderd!")
                .build();
        alert.show();
    }

    public void doDeleteUser() {
        updateSelectedUser();
        if (isNotCurrentUser()) {
            Optional<ButtonType> buttonType = showAlertDelete(session.getSelectedUser());
            ButtonType buttonPressed = buttonType.orElse(ButtonType.CANCEL);
            if (buttonPressed.equals(ButtonType.OK)) {
                USER_DAO.deleteOne(session.getSelectedUser());
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

    private Optional<ButtonType> showAlertDelete(User user) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Weet je het zeker?");
        alert.setTitle("Meldingvenster");
        alert.setHeaderText("Gebruiker '" + user.getUserName() + "' verwijderen?");
        return alert.showAndWait();
    }

    private void showAlertCancelled() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "");
        alert.setTitle("Meldingvenster");
        alert.setHeaderText("Geannuleerd");
        alert.setContentText("Gebruiker niet verwijderd");
        alert.show();
    }

}