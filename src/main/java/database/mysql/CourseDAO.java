package database.mysql;

import model.Course;
import model.Question;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO extends AbstractDAO implements GenericDAO<Course> {

    private static final Logger LOGGER = LogManager.getLogger(CourseDAO.class);

    public CourseDAO(DBAccess dbAccess) {
        super(dbAccess);
    }

    public void updateCourse(Course course) {
        String sql = "Update course Set courseName = ?, idCoordinator = ? where idCourse =?;";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setString(1, course.getCourseName());
            preparedStatement.setInt(2, course.getIdCourse());
            preparedStatement.setInt(3,course.getCoordinatorID());
            executeManipulateStatement();
        } catch (SQLException sqlException) {
            System.out.println("SQL error " + sqlException.getMessage());
        }
    }

    //@authorVG method delete selected course from DB
    public void deleteCourse(Course course) {
        String sql = "DELETE FROM course WHERE idCourse = ?;";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, course.getIdCourse());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println("SQL error" + sqlException.getMessage());
        }
    }

    /**
     * Gets all courses a user is signed up for
     *
     * @param idUser an int representing the user-id (idUser).
     *
     * @return a List containing Course objects
     * @author Daniel Leertouwer
     */
    public List<Course> getCoursesForUserWithId(int idUser) {
        List<Course> courses = new ArrayList<>();
        String sql = String.format("SELECT * FROM Course_User WHERE idUser=%d", idUser);
        try {
            setupPreparedStatement(sql);
            ResultSet resultSet = executeSelectStatement();
            while (resultSet.next()) {
                int idCourse = resultSet.getInt("idCourse");
                Course course = getOneById(idCourse);
                courses.add(course);
            }
        } catch (SQLException sqlException) {
            LOGGER.error("SQL-error " + sqlException.getMessage());
        }

        return courses;
    }

    /**
     * author VG
     * Retrieves all course from the database
     *
     * @return een ArrayList with objects with the type Course
     * @throws SQLException when executing the SQL-query results in an error.
     */
    @Override
    public ArrayList<Course> getAll() {
        String sql = "Select * From Course";
        ArrayList<Course> courselist = new ArrayList<>();
        try {
            setupPreparedStatement(sql);
            ResultSet resultSet = executeSelectStatement();
            Course course;
            while (resultSet.next()) {
                String coursename = resultSet.getString("courseName");
                int coordinatorID = resultSet.getInt("idCoordinator");
                course = new Course(coursename, coordinatorID);
                course.setIdCourse(resultSet.getInt("idCourse"));
                courselist.add(course);
            }
        } catch (SQLException sqlException) {
            System.out.println("SQL error " + sqlException.getMessage());
        }
        return courselist;
    }

    /**
     * Retrieves a specific course from the database
     *
     * @return an object with the type Course
     * @throws SQLException when executing the SQL-query results in an error.
     */
    @Override
    public Course getOneById(int id) {
        String sql = "SELECT * FROM course WHERE idCourse = ?";
        Course course = null;
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = executeSelectStatement();
            if (resultSet.next()) {
                String courseName = resultSet.getString("courseName");
                int coordinatorID = resultSet.getInt("idCoordinator");
                course = new Course(courseName,coordinatorID);
                course.setIdCourse(id);
            } else {
                System.out.println("Cursus met id " + id + " niet gevonden!");
            }
        } catch (SQLException sqlException) {
            System.out.println("SQL error " + sqlException.getMessage());
        }
        return course;
    }

    /**
     * Stores a specific course in the database.
     *
     * @throws SQLException when executing the SQL-query results in an error.
     */
    @Override
    public void storeOne(Course course) {
        String sql = "INSERT INTO course(courseName, idCoordinator) VALUES(?, ?);";
        try {
            setupPreparedStatementWithKey(sql);
            preparedStatement.setString(1, course.getCourseName());
            preparedStatement.setInt(2, course.getCoordinatorID());
            int id = executeInsertStatementWithKey();
            course.setIdCourse(id);
        } catch (SQLException sqlException) {
            System.out.println("SQL error " + sqlException.getMessage());
        }
    }

    public ArrayList<Course> getCoordinatorsByUserId (int userId){
        String sql = "SELECT * FROM course WHERE idCoordinator = ?;";
        ArrayList<Course> result = new ArrayList<>();
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = executeSelectStatement();
            Course course;
            while (resultSet.next()) {
                int idCourse = resultSet.getInt("idCourse");
                String courseName = resultSet.getString("courseName");
                int coordinatorID = resultSet.getInt("idCoordinator");
                course = new Course(idCourse, courseName, coordinatorID);
                result.add(course);
            }
        } catch (SQLException exception)
        { System.out.println("SQL error " + exception.getMessage());
        }
        return result;
    }

}
