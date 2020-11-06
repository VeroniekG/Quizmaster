package service;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Contains the logic needed for logging a user in/out. Depends on {@link UserService} to get
 * user input from the view controller.
 *
 * @author Daniel Leertouwer
 * @version 1.0.0
 * @see Service
 * @see UserService
 * @since 1.0
 */
public class LoginService extends Service {

    private final UserService userService;

    public LoginService() {
        super();
        userService = new UserService();
    }

    public boolean login(TextField nameTextField, PasswordField passwordField) {
        boolean loginSuccess = false;
        List<TextField> inputFields = new ArrayList<>();
        inputFields.add(nameTextField);
        inputFields.add(passwordField);
        Map<String, String> userInputMap = userService.createTextFieldInputMap(inputFields);
        if (session.getLoggedInUser() == null) {
            boolean userIsValidated;
            userIsValidated = userService.checkIfUserIsValid(userInputMap);
            if (userIsValidated) {
                session.setLoggedInUser(userService.getUser());
                loginSuccess = true;
            }
        }
        return loginSuccess;
    }

}