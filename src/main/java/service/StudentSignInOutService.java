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

    private static final String SIGNED_UP = "signedUp";
    private static final String SIGNED_OUT = "signedOut";
    private static final Session SESSION = Session.getInstance();
    private final CourseService courseService;

    private ObservableList<Course> selection;

    private ObservableList<Course> courseSignedUp;
    private SortedList<Course> courseSignedUpSorted;
    private ListView<Course> courseSignedUpListView;
    private ObservableList<Course> courseSignedOut;
    private SortedList<Course> courseSignedOutSorted;
    private ListView<Course> courseSignedOutListview;

    public StudentSignInOutService() {
        courseService = new CourseService();
    }

    public void setupView(ListView<Course> listViewSignUp, ListView<Course> listViewSignOut) {
        setCourseSignedUp(listViewSignUp.getItems());
        setCourseSignedOut(listViewSignOut.getItems());
        setObservableListItems(courseSignedUp, createCourseSignedUpList());
        setObservableListItems(courseSignedOut, createCourseSignedOutList());
        courseSignedUpSorted = new SortedList<Course>(courseSignedUp);
        courseSignedUpSorted.setComparator(courseComparator());
        courseSignedOutSorted = new SortedList<Course>(courseSignedOut);
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
        List<Course> courseSignedUpList = courseService.getSignedUpCourseList(idUser);
        return courseSignedUpList;
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
        return new Comparator<Course>() {
            @Override
            public int compare(Course o1, Course o2) {
                return o1.getCourseName().compareTo(o2.getCourseName());
            }
        };
    }

    public void updateListView(ListView<Course> listView, SortedList<Course> sortedList) {
        listView.setItems(sortedList);
    }

    public void deleteSelection() {
        int idUser = SESSION.getLoggedInUser().getIdUser();
        courseService.deleteCourses(selection, idUser);
    }

    public void update() {
        selection = null;
        courseSignedUp.clear();
        courseSignedOut.clear();
        setObservableListItems(courseSignedUp, createCourseSignedUpList());
        setObservableListItems(courseSignedOut, createCourseSignedOutList());
    }

    public void addSelection() {
        int idUser = SESSION.getLoggedInUser().getIdUser();
        courseService.addCourses(selection, idUser);
    }

    public ObservableList<Course> getCourseSignedUp() {
        return courseSignedUp;
    }

    public void setCourseSignedUp(ObservableList<Course> courseSignedUp) {
        this.courseSignedUp = courseSignedUp;
    }

    public ObservableList<Course> getCourseSignedOut() {
        return courseSignedOut;
    }

    public void setCourseSignedOut(ObservableList<Course> courseSignedOut) {
        this.courseSignedOut = courseSignedOut;
    }

    public void setSelection(ListView<Course> listView) {
        selection = listView.getSelectionModel().getSelectedItems();
    }

}