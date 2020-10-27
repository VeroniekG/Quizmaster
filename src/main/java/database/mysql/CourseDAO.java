package database.mysql;

import model.Course;
import model.Role;
import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseDAO extends AbstractDAO implements GenericDAO<Course> {

    public CourseDAO(DBAccess dbAccess) {
        super(dbAccess);
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
                course = new Course(coursename);
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
                course = new Course(courseName);
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
    public void storeOne(Course type) {
        String sql = "INSERT INTO course(courseName) VALUES(?);";
        try {
            setupPreparedStatementWithKey(sql);
            preparedStatement.setString(1, type.getCourseName());
            int id = executeInsertStatementWithKey();
            type.setIdCourse(id);
        } catch (SQLException sqlException) {
            System.out.println("SQL error " + sqlException.getMessage());
        }
    }
}
