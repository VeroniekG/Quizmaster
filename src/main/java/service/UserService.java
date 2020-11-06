package service;

import database.mysql.DBAccess;
import database.mysql.UserDAO;
import javafx.scene.control.TextField;
import model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Contains the logic for getting User data from the database and checking it against input obtained
 * by the view controller.
 *
 * @author Daniel Leertouwer
 * @version 1.0.2
 * @see DBAccess
 * @see InputValidationService
 * @see User
 * @since 1.0
 */
public class UserService extends Service {

    private static final DBAccess DB_ACCESS = DBAccess.getInstance();

    private final UserDAO userDAO;
    User user;

    public UserService() {
        userDAO = new UserDAO(DB_ACCESS);
        user = null;
    }

    public Map<String, String> createTextFieldInputMap(List<TextField> fields) {
        Map<String, String> userInput = new HashMap<>();
        for (TextField field : fields) {
            userInput.put(field.getId(), field.getText());
        }
        return userInput;
    }

    public boolean checkIfUserIsValid(Map<String, String> userInputs) {
        boolean userNameExists;
        boolean passwordIsValid = false;
        String password = userInputs.get("passwordField");
        userNameExists = checkIfUserNameExists(userInputs);
        if (userNameExists) {
            passwordIsValid = checkIfPasswordIsValid(password);
        }
        return userNameExists && passwordIsValid;
    }

    public boolean checkIfUserNameExists(Map<String, String> userInputs) {
        boolean userNameExists = false;
        String username = userInputs.get("nameTextField");
        user = userDAO.getOneByName(username);
        if (user != null) {
            userNameExists = true;
        }
        return userNameExists;
    }

    public boolean checkIfPasswordIsValid(String passwordInput) {
        String validPassword = user.getPassword();
        return inputValidationService.validateInputString(validPassword, passwordInput);
    }

    public User getUser() {
        return user;
    }

}