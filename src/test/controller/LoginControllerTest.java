package controller;

import database.mysql.UserDAO;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LoginControllerTest {

    @Mock
    private UserDAO userDAO;
    @Mock
    private TextField nameTextField;
    @Mock
    private PasswordField passwordField;
    @InjectMocks
    private LoginController underTest;

    @Test
    void doLogin() {
    }

    @Test
    void doQuit() {
    }

    @Test
    void showAlert() {
    }

    @Nested
    class WhenDoingLogin {

        @BeforeEach
        void setup() {
        }

    }

    @Nested
    class WhenDoingQuit {

        @Mock
        private ActionEvent event;

        @BeforeEach
        void setup() {
        }

    }

    @Nested
    class WhenShowingAlert {

        private final String MESSAGE = "MESSAGE";
        private final AlertType TYPE = AlertType.NONE;

        @BeforeEach
        void setup() {
        }

    }

}