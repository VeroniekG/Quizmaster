package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TaskList {

    // WELCOME MENU TASKS - STUDENT
    // studentSignInOut.fxml
    public static final String STUDENTSIGNINOUT = "In- of uitschrijven cursus";
    // selectQuizForStudent.fxml
    public static final String SELECTQUIZFORSTUDENT = "Quiz selecteren";
    // studentFeedback.fxml
    public static final String STUDENTFEEDBACK = "Resultaten bekijken";

    // WELCOME MENU TASKS - COORDINATOR
    // coordinatorDashboard.fxml
    public static final String COORDINATORDASHBOARD = "Coördinator dashboard";
    // manageQuizzes.fxml
    public static final String MANAGEQUIZZES = "Quizbeheer";
    // manageQuestions.fxml
    public static final String MANAGEQUESTIONS = "Vraagbeheer";

    // WELCOME MENU TASKS - ADMINISTRATOR
    // manageCourses.fxml
    public static final String MANAGECOURSES = "Cursusbeheer";
    // manageGroups.fxml
    public static final String MANAGEGROUPS = "Groepenbeheer";

    // WELCOME MENU TASKS - SYSTEM ADMINISTRATOR (TECHNISCH BEHEERDER)
    // manageUsers.fxml
    public static final String MANAGEUSERS = "Gebruikersbeheer";

    public TaskList() {

    }

    public static List<String> createTaskList(Role role) {
        List<String> tasks = new ArrayList<>();
        switch (role) {
            case STUDENT:
                tasks = new ArrayList<>(Arrays.asList(STUDENTFEEDBACK, SELECTQUIZFORSTUDENT,
                        STUDENTSIGNINOUT));
                break;
            case COORDINATOR:
                tasks = new ArrayList<>(Arrays.asList(COORDINATORDASHBOARD, MANAGEQUIZZES,
                        MANAGEQUESTIONS));
                break;
            case ADMINISTRATOR:
                tasks = new ArrayList<>(Arrays.asList(MANAGECOURSES, MANAGEGROUPS));
                break;
            case TECHNISCH_BEHEERDER:
                tasks = new ArrayList<>(Collections.singletonList(MANAGEUSERS));
                break;
        }
        return tasks;
    }

}
