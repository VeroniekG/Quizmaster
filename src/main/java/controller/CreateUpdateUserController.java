package controller;

import javafx.event.ActionEvent;
import model.User;
import view.Main;

public class CreateUpdateUserController {

    public void setup(User user) {}

    //TJ menu knop terug naar menu
    public void doMenu(ActionEvent actionEvent) {
        Main.getSceneManager().showWelcomeScene();
    }

    public void doCreateUpdateUser() {
    }
}
