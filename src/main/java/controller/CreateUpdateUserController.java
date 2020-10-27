package controller;

import database.mysql.DBAccess;
import database.mysql.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.Main;

public class CreateUpdateUserController {

    private static final Logger logger = LogManager.getLogger(ManageUsersController.class);
    private UserDAO userDAO;
    private DBAccess dbAccess;

    @FXML
    private Label titleLabel;

    @FXML
    private TextField userName;

    @FXML
    private PasswordField password;

    public CreateUpdateUserController() {
        dbAccess = Main.getDbAccess();
        userDAO = new UserDAO(dbAccess);
    }

    public void setup(User user) {
        userName.setText(user.getUserName());
    }

    public void doMenu() {
    }

    public void doCreateUpdateUser() {
    }

}
