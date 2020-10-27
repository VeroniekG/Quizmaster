package model;

import java.util.Comparator;

/**
 * User Model Object. Various attributes of users and related behaviour.
 * An auto-incremented user-id (idUser) is generated when a user is stored in the database.
 *
 * @author thieh, leertod
 * @version 1.0.3
 * @see database.mysql.UserDAO
 * @see model.Role
 * @since 1.0
 */
public class User {

    private final String userName;
    private final String password;
    private final Role role;
    private int idUser;

    public User(int idUser, String userName, String password, Role role) {
        this(userName, password, role);
        this.idUser = idUser;
    }

    public User(String userName, String password, Role role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    //    @Override
    //    public String toString() {
    //        return userName;
    //    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", idUser=" + idUser +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public static class UserNameComparator implements Comparator<User> {

        // Used for sorting by username, ascending order
        @Override
        public int compare(User user1, User user2) {
            return user1.userName.compareTo(user2.userName);
        }

    }

}
