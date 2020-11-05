package service;

import model.User;
import model.UserMenu;

/**
 * Contains the logic for creating and setting taskmenu and welcome text.
 *
 * @author Daniel Leertouwer
 * @version 1.0.1
 * @see controller.WelcomeController
 * @see model.Session
 * @since 1.0
 */
public class WelcomeService extends Service {

    final UserMenu menu;
    private final User loggedInUser;

    public WelcomeService() {
        loggedInUser = session.getLoggedInUser();
        menu = new UserMenu();
    }

    public UserMenu createWelcomeMenu() {
        menu.setMenuItemsForUser(loggedInUser);
        return menu;
    }

    public String createWelcomeMessage() {
        String name = loggedInUser.getFirstName();
        String role = loggedInUser.getRole().toString().toLowerCase();
        return String.format("Welkom %s! Je bent ingelogd als %s.", name, role);
    }

    public void invalidateSession() {
        session.invalidate();
    }

}