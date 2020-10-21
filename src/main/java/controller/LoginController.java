package controller;

import database.mysql.DBAccess;
import database.mysql.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.User;

public class LoginController {

    private UserDAO userDAO;
    private DBAccess dbAccess;
    private User user;

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField passwordField;

    public LoginController(UserDAO userDAO, DBAccess dbAccess) {
        this.userDAO = userDAO;
        this.dbAccess = dbAccess;
    }

    @FXML
    public void doLogin(ActionEvent event) {

    }

    @FXML
    public void doQuit(ActionEvent event) {
        System.exit(0);
    }
}
