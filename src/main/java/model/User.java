package model;

import java.util.Comparator;
import java.util.Objects;

public class User {

    private int idUser;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private Role role;

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

    @SuppressWarnings({"MethodComplexity", "ObjectComparison"})
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

    public String getPassword() {
        return password;
    }

    @SuppressWarnings("ReturnPrivateMutableField")
    public Role getRole() {
        return role;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public static class UserNameComparator implements Comparator<User> {

        @Override
        public int compare(User user1, User user2) {
            return user1.getUserName().compareTo(user2.getUserName());
        }

    }

    public static class Builder {

        private int idUser;
        private String firstName;
        private String lastName;
        private String userName;
        private String password;
        private Role role;


        public Builder withIdUser(int idUser) {
            this.idUser = idUser;
            return this;
        }

        public Builder withUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withRole(Role role) {
            this.role = role;
            return this;
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public User build() {
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
