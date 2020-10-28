package model;

import java.util.Comparator;

public class User {

    private int idUser;
    private String userName;
    private String password;
    private Role role;
    private String firstName;
    private String lastName;

    public User(int idUser, String firstName, String lastName, String userName, String password,
                Role role) {
        this(firstName, lastName, userName, password, role);
    }

    public User(String firstName, String lastName, String userName, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public User() {
    }

    @Override
    public String toString() {
        return userName;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public static class UserNameComparator implements Comparator<User> {

        @Override
        public int compare(User user1, User user2) {
            return user1.getUserName().compareTo(user2.getUserName());
        }

    }

}
