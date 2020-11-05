package model;

/**
 * Role Model Object. Contains the roles a User can have within the application. Based on the role,
 * certain functionality will become available.
 *
 * @author leertod
 * @version 1.0.1
 * @see model.User
 * @see database.mysql.UserDAO
 * @see controller.WelcomeController
 * @since 1.0
 */
public enum Role {

    STUDENT("Student"),
    COORDINATOR("Coördinator"),
    ADMINISTRATOR("Administrator"),
    TECHNISCH_BEHEERDER("Technisch beheerder");

    private final String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return roleName;
    }

    public String getRoleName() {
        return roleName.toLowerCase();
    }

}
