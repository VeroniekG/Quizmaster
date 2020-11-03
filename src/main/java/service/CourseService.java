package service;

import database.mysql.CourseDAO;
import database.mysql.DBAccess;
import model.Course;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseService extends Service {

    private static final DBAccess DB_ACCESS = DBAccess.getInstance();

    private final CourseDAO courseDAO;

    public CourseService() {
        courseDAO = new CourseDAO(DB_ACCESS);
    }

    public Map<Integer, Course> getCoursesForCurrentUser() {
        int idUser = session.getLoggedInUser().getIdUser();
        return createHashMap(courseDAO.getCoursesForUserWithId(idUser));
    }

    private Map<Integer, Course> createHashMap(List<Course> courses) {
        Map<Integer, Course> courseMap = new HashMap<>();
        for (Course course : courses) {
            courseMap.put(course.getIdCourse(), course);
        }
        return courseMap;
    }

    public Map<Integer, Course> getAllAvailableCourses() {
        return createHashMap(courseDAO.getAll());
    }

}
