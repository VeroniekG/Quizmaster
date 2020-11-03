package model;

/**
 * Stores data of the application user session. As the application has only one active user, this
 * class implements a Singleton pattern, it cannot be instantiated directly. Instead method
 * {@link #getInstance()} should be used to create a new instance, or get the instance if it
 * already exists within the application.
 *
 * @author Daniel Leertouwer
 * @version 1.0.0
 * @see User
 * @since 1.0
 */
public class Session {

    static Session sessionInstance = null;

    User loggedInUser;
    User selectedUser;

    private Session() {
        loggedInUser = null;
        selectedUser = null;
    }

    public void invalidate() {
        loggedInUser = null;
        selectedUser = null;
    }

    public static Session getInstance() {
        if (sessionInstance == null) {
            sessionInstance = new Session();
        }
        return sessionInstance;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

}