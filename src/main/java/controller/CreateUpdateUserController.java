package controller;

import database.mysql.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.Main;

public class CreateUpdateUserController {

    private static final Logger LOGGER = LogManager.getLogger(CreateUpdateUserController.class);
    @FXML
    Label titleLabel;
    @FXML
    TextField firstName;
    @FXML
    TextField lastName;
    @FXML
    TextField userName;
    @FXML
    PasswordField password;
    @FXML
    Button buttonMenu;
    @FXML
    Button buttonCreateUpdate;
    private UserDAO userDAO;
    private User user;

    public CreateUpdateUserController() {
        userDAO = new UserDAO(Main.getDBaccess());
    }

    public void setup(User user) {
        this.user = user;
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        userName.setText(user.getUserName());
        password.setText(user.getPassword());
        // Initially unfocus the textfield
        firstName.getParent().requestFocus();
    }

    public void doMenu() {
        Main.getSceneManager().showManageUserScene();
    }

    public void doCreateUpdateUser() {
        userDAO.storeOne(this.user);
    }

}
