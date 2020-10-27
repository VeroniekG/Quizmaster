package model;

import java.util.Comparator;

public class User {

    private int idUser;
    private String userName;
    private String password;
    private Role role;

    public User(int idUser, String userName, String password, Role role) {
        this(userName, password, role);
        this.idUser = idUser;
    }

    public User(String userName, String password, Role role) {
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

    public static class UserNameComparator implements Comparator<User> {

        @Override
        public int compare(User user1, User user2) {
            return user1.getUserName().compareTo(user2.getUserName());
        }

    }

}
