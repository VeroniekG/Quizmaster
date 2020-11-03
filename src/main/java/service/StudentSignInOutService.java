package service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import model.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StudentSignInOutService {

    private final CourseService courseService;

    public StudentSignInOutService() {
        courseService = new CourseService();
    }

    public ListView<Course> createSignedOutCourseList(ListView<Course> listView) {
        Map<Integer, Course> allCourses = courseService.getAllAvailableCourses();
        Map<Integer, Course> coursesSignedInForMap = courseService.getCoursesForCurrentUser();
        Map<Integer, Course> coursesSignedOutForMap = courseService.getAllAvailableCourses();

        Set<Integer> keys = coursesSignedInForMap.keySet();
        for (Integer key : keys) {
            if (coursesSignedOutForMap.containsKey(key)) {
                coursesSignedOutForMap.remove(key);
            }
        }
        List<Course> coursesSignedOutForList =
                new ArrayList<Course>(coursesSignedOutForMap.values());
        return createListView(listView, coursesSignedOutForList);
    }

    public ListView<Course> createListView(ListView<Course> listView, List<Course> courses) {
        ObservableList<Course> coursesObservableList = FXCollections.observableArrayList(courses);
        listView.setItems(coursesObservableList);
        return listView;
    }

    public ListView<Course> createSignedInCoursesList(ListView<Course> listView) {
        List<Course> coursesSignedInFor =
                new ArrayList<>(courseService.getCoursesForCurrentUser().values());
        return createListView(listView, coursesSignedInFor);
    }

    public void signOutStudentForCourses(List<Course> courseList) {

    }

    public void signInStudentForCourses(List<Course> courseList) {

    }

}
