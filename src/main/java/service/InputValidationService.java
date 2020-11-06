package service;

/**
 * Contains logic for validating user input.
 *
 * @author Daniel Leertouwer
 * @version 1.0.1
 * @since 1.0
 */
public class InputValidationService {

    public InputValidationService() {

    }

    public boolean validateInputString(String validString, String inputString) {
        return validString.equals(inputString);
    }

}

