package model;

public enum MenuItem {

    // STUDENT
    STUDENTSIGNINOUT("In- of uitschrijven cursus"), SELECTQUIZFORSTUDENT("Quiz selecteren"),

    //COORDINATOR
    COORDINATORDASHBOARD("Coördinator dashboard"), MANAGEQUIZZES("Quizbeheer"), MANAGEQUESTIONS(
            "Vraagbeheer"),

    // ADMINISTRATOR
    MANAGECOURSES("Cursusbeheer"), MANAGEGROUPS("Groepenbeheer"),

    // TECHNISCH BEHEERDER
    MANAGEUSERS("Gebruikersbeheer");

    private final String menuItemName;

    private MenuItem(String menuItemName) {
        this.menuItemName = menuItemName;
    }

    @Override
    public String toString() {
        return menuItemName;
    }
}
