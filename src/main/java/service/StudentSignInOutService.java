package service;

import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.ListView;
import model.Course;
import model.Session;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Contains the logic needed for updating view studentSignInOut.fxml
 *
 * @author Daniel Leertouwer
 * @version 1.0.1
 * @see CourseService
 */
public class StudentSignInOutService {

    private static final Session SESSION = Session.getInstance();

    private final CourseService courseService;

    private ObservableList<Course> selection;
    private ObservableList<Course> courseSignedUp;
    private SortedList<Course> courseSignedUpSorted;
    private ObservableList<Course> courseSignedOut;
    private SortedList<Course> courseSignedOutSorted;

    public StudentSignInOutService() {
        courseService = new CourseService();
    }

    public void setupView(ListView<Course> listViewSignUp, ListView<Course> listViewSignOut) {
        setCourseSignedUp(listViewSignUp.getItems());
        setCourseSignedOut(listViewSignOut.getItems());
        setObservableListItems(courseSignedUp, createCourseSignedUpList());
        setObservableListItems(courseSignedOut, createCourseSignedOutList());
        courseSignedUpSorted = new SortedList<>(courseSignedUp);
        courseSignedUpSorted.setComparator(courseComparator());
        courseSignedOutSorted = new SortedList<>(courseSignedOut);
        courseSignedOutSorted.setComparator(courseComparator());
        updateListView(listViewSignUp, courseSignedUpSorted);
        updateListView(listViewSignOut, courseSignedOutSorted);
    }

    public void setObservableListItems(ObservableList<Course> observableList,
                                       List<Course> courseList) {
        observableList.setAll(courseList);
    }

    public List<Course> createCourseSignedUpList() {
        int idUser = SESSION.getLoggedInUser().getIdUser();
        return courseService.getSignedUpCourseList(idUser);
    }

    public List<Course> createCourseSignedOutList() {
        int idUser = SESSION.getLoggedInUser().getIdUser();
        // Create a list containing the IDs of courses the logged in user is signed up for
        List<Integer> signedUpIdCourseList = courseService.getSignedUpCourseIdList(idUser);
        // Create a HashMap of all available courses with idCourse as key and Course object as value
        Map<Integer, Course> courseMap = courseService.getAllAvailableCoursesMap();
        // Removing courses the user is signed up for results in a HashMap containing the courses
        // available for sign-up
        signedUpIdCourseList.forEach(courseMap::remove);
        return new ArrayList<>(courseMap.values());
    }

    public Comparator<Course> courseComparator() {
        return Comparator.comparing(Course::getCourseName);
    }

    public void updateListView(ListView<Course> listView, SortedList<Course> sortedList) {
        listView.setItems(sortedList);
    }

    public void setSelection(ListView<Course> listView) {
        selection = listView.getSelectionModel().getSelectedItems();
    }

    public void storeSelectedCoursesForUser() {
        int idUser = SESSION.getLoggedInUser().getIdUser();
        courseService.storeCourses(selection, idUser);
    }

    public void deleteSelectedCoursesForUser() {
        int idUser = SESSION.getLoggedInUser().getIdUser();
        courseService.deleteCourses(selection, idUser);
    }

    public void setCourseSignedUp(ObservableList<Course> courseSignedUp) {
        this.courseSignedUp = courseSignedUp;
    }

    public void setCourseSignedOut(ObservableList<Course> courseSignedOut) {
        this.courseSignedOut = courseSignedOut;
    }

    public void updateLists() {
        selection = null;
        courseSignedUp.clear();
        courseSignedOut.clear();
        setObservableListItems(courseSignedUp, createCourseSignedUpList());
        setObservableListItems(courseSignedOut, createCourseSignedOutList());
    }

}