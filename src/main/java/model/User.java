package model;

import java.util.Comparator;
import java.util.Objects;

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
        this.idUser = idUser;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return idUser == user.idUser &&
                userName.equals(user.userName) &&
                password.equals(user.password) &&
                role == user.role &&
                firstName.equals(user.firstName) &&
                lastName.equals(user.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, userName, password, role, firstName, lastName);
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

    public static class UpdateUser {

        private int idUser;
        private String userName;
        private String password;
        private Role role;
        private String firstName;
        private String lastName;

        public UpdateUser withIdUser(int idUser) {
            this.idUser = idUser;
            return this;
        }

        public UpdateUser withUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public UpdateUser withPassword(String password) {
            this.password = password;
            return this;
        }

        public UpdateUser withRole(Role role) {
            this.role = role;
            return this;
        }

        public UpdateUser withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UpdateUser withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public User update() {
            User user = new User();
            user.idUser = this.idUser;
            user.firstName = this.firstName;
            user.lastName = this.lastName;
            user.userName = this.userName;
            user.password = this.password;
            user.role = this.role;
            return user;
        }

    }

}
