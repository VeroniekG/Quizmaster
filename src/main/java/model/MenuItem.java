package model;

/**
 * MenuItem Model Object. Contains all menu-items, necessary to build application menus.
 * TODO: Add submenu-items
 *
 * @author leertod
 * @version 1.0.1
 * @see controller.WelcomeController
 * @since 1.0
 */
public enum MenuItem {

    // STUDENT
    STUDENTSIGNINOUT("In- of uitschrijven cursus"),
    SELECTQUIZFORSTUDENT("Quiz selecteren"),

    // COORDINATOR
    COORDINATORDASHBOARD("Coördinator dashboard"),
    MANAGEQUIZZES("Quizbeheer"),
    MANAGEQUESTIONS("Vraagbeheer"),

    // ADMINISTRATOR
    MANAGECOURSES("Cursusbeheer"),
    MANAGEGROUPS("Groepenbeheer"),

    // SYSTEM-ADMINISTRATOR (TECHNISCH BEHEERDER)
    MANAGEUSERS("Gebruikersbeheer"),

    // GENERAL
    WELCOMESCENE("Terug naar het beginscherm");

    private final String menuItemName;

    private MenuItem(String menuItemName) {
        this.menuItemName = menuItemName;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "menuItemName='" + menuItemName + '\'' +
                '}';
    }

    public String getMenuItemName() {
        return menuItemName;
    }

}
