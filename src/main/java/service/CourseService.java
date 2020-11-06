package service;

import database.mysql.CourseDAO;
import database.mysql.DBAccess;
import model.Course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Interacts with Class CourseDAO to prepare data for use with other services.
 *
 * @author Daniel Leertouwer
 * @version 1.0.2
 * @see CourseDAO
 * @since 1.0
 */
public class CourseService {

    private static final DBAccess DB_ACCESS = DBAccess.getInstance();
    private static final CourseDAO COURSE_DAO = new CourseDAO(DB_ACCESS);

    public CourseService() {

    }

    /**
     * Creates a list of IDs, of courses the currently logged-in user has signed up for.
     *
     * @return a list of IDs
     */
    public List<Integer> getSignedUpCourseIdList(int idUser) {
        List<Course> courseList = COURSE_DAO.getCoursesForUserWithId(idUser);
        List<Integer> idCourseList = new ArrayList<>();
        courseList.forEach(course -> {
            idCourseList.add(course.getIdCourse());
        });
        return idCourseList;
    }

    public List<Course> getSignedUpCourseList(int idUser) {
        return COURSE_DAO.getCoursesForUserWithId(idUser);
    }

    public void deleteCourses(List<Course> courseList, int idUser) {
        for (Course course : courseList) {
            int idCourse = course.getIdCourse();
            COURSE_DAO.deleteCoursesSignedUpForUserWithId(idCourse, idUser);
        }
    }

    public void storeCourses(List<Course> courseList, int idUser) {
        for (Course course : courseList) {
            int idCourse = course.getIdCourse();
            COURSE_DAO.storeCoursesSignedUpForUserWithId(idCourse, idUser);
        }
    }

    /**
     * Creates a HashMap of all available courses, with the IDs as keys and Objects of type Course
     * as values.
     *
     * @return a HashMap of all available courses, Map<Integer, Course>
     * @since 1.0
     */
    public Map<Integer, Course> getAllAvailableCoursesMap() {
        return createHashMap(COURSE_DAO.getAll());
    }

    private Map<Integer, Course> createHashMap(List<Course> courses) {
        Map<Integer, Course> courseMap = new HashMap<>();
        for (Course course : courses) {
            courseMap.put(course.getIdCourse(), course);
        }
        return courseMap;
    }

}