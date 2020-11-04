package database.mysql;

import controller.CreateUpdateUserController;
import model.Role;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

/**
 * Interacts with the User model and maps application calls to the persistence layer. Extends
 * {@link AbstractDAO} and contains methods to retrieve all users, to retrieve one user by name, to
 * retrieve one user by id and to store one user.
 *
 * @author Daniel Leertouwer
 * @version 1.0.15
 * @see User
 * @since 1.0
 */
public class UserDAO extends AbstractDAO implements GenericDAO<User>, DAO<User> {

    private static final Logger LOGGER = LogManager.getLogger(UserDAO.class);

    public UserDAO(DBAccess dBaccess) {
        super(dBaccess);
    }

    /**
     * Retrieves a user from the database by username. A PreparedStatement is used to execute a
     * parameterized SQL-query with BINARY function. This function converts userName to a binary
     * string, which makes the query case-sensitive. {@link #setUserWithResultSet(ResultSet)} is
     * called with the resulting ResultSet to convert the contained values to a corresponding
     * User object.
     *
     * @param name the username as a String
     *
     * @return an object of type User
     * @throws SQLException if the Parameter index is out of range; if a database access error
     * occurs; if other SQL-errors occur
     * @see #setupPreparedStatement(String sql)
     * @see ResultSet
     * @since 1.0
     */
    @Override
    public User getOneByName(String name) {
        String sql = "SELECT * FROM User WHERE BINARY userName = ?";
        User user = null;
        try {
            setupPreparedStatement(sql);
            preparedStatement.setString(1, name);
            ResultSet resultSet = executeSelectStatement();
            if (resultSet.next()) {
                user = setUserWithResultSet(resultSet);
            }
        } catch (SQLException sqlException) {
            LOGGER.error("SQL-error " + sqlException.getMessage());
        }
        return user;
    }

    /**
     * Retrieves the values of the designated columns in the current row of the passed ResultSet
     * object and constructs a new User object with the results.
     *
     * @param resultSet an object of type ResultSet
     *
     * @return an object of type User
     * @throws SQLException if one or more columnLabels are not valid; if a database access
     * error occurs; if this method is called with a closed ResultSet
     * @see ResultSet
     * @since 1.0
     */
    private User setUserWithResultSet(ResultSet resultSet) throws SQLException {
        int idUser = resultSet.getInt("idUser");
        String userName = resultSet.getString("userName");
        String password = resultSet.getString("password");
        String role = resultSet.getString("role");
        String firstName = resultSet.getString("firstName");
        String lastName = resultSet.getString("lastName");
        return new User(idUser, firstName, lastName, userName, password, Role.valueOf(role));
    }

    public void updateOne(User type) {
        String sql = "UPDATE User SET userName = ?, password = ?, role = ?, firstName = ?, " +
                "lastName = ? where idUser = ?;";
        try {
            setupPreparedStatement(sql);
            setPreparedStatementParameters(type);
            executeManipulateStatement();
        } catch (SQLIntegrityConstraintViolationException duplicateUserName) {
            LOGGER.error("Gebruikersnaam bestaat al!");
            CreateUpdateUserController.setSqlErrorMessage("Gebruikersnaam bestaat al!");
        } catch (SQLException sqlException) {
            LOGGER.error("SQL-error: " + sqlException.getMessage());
        }
    }

    private void setPreparedStatementParameters(User user) throws SQLException {
        preparedStatement.setString(1, user.getUserName());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getRole().name());
        preparedStatement.setString(4, user.getFirstName());
        preparedStatement.setString(5, user.getLastName());
        // If the user isn't a new user (idUser == 0), we also need idUser to be able to execute an
        // UPDATE statement
        if (user.getIdUser() != 0) {
            preparedStatement.setInt(6, user.getIdUser());
        }
    }

    public void deleteOne(User type) {
        String sql = "DELETE FROM User WHERE idUser = ?;";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, type.getIdUser());
            executeManipulateStatement();
        } catch (SQLException sqlException) {
            LOGGER.error("SQL-error: " + sqlException.getMessage());
        }
    }

    /**
     * Retrieves all users from the database. A PreparedStatement is used to execute a
     * parameterized SQL-query. {@link #setUserWithResultSet(ResultSet)} is called with the
     * resulting ResultSet to convert the contained values to a corresponding User object
     *
     * @return an ArrayList with objects of type User
     * @throws SQLException if one or more columnLabels are not valid; if a database access
     * error occurs; if other SQL-errors occur
     * @since 1.0
     */
    @Override
    public ArrayList<User> getAll() {
        String sql = "SELECT * FROM User";
        ArrayList<User> userList = new ArrayList<>();
        try {
            setupPreparedStatement(sql);
            ResultSet resultSet = executeSelectStatement();
            User user;
            while (resultSet.next()) {
                user = setUserWithResultSet(resultSet);
                userList.add(user);
            }
        } catch (SQLException sqlException) {
            LOGGER.error("SQL-error " + sqlException.getMessage());
        }
        return userList;
    }

    /**
     * Retrieves a user from the database by idUser. A PreparedStatement is used to execute a
     * parameterized SQL-query. {@link #setUserWithResultSet(ResultSet)} is called with the
     * resulting ResultSet to convert the contained values to a corresponding User object.
     *
     * @return an object of type User
     * @throws IllegalArgumentException if the requested user id does not exist
     * @throws SQLException if the parameter index is out of range; if a database access error
     * occurs; if other SQL-error occur
     * @see #setupPreparedStatement(String sql)
     * @see ResultSet
     * @since 1.0
     */
    @Override
    public User getOneById(int id) {
        String sql = "SELECT * FROM User WHERE idUser = ?";
        User user = null;
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = executeSelectStatement();
            if (resultSet.next()) {
                user = setUserWithResultSet(resultSet);
            } else {
                throw new IllegalArgumentException("User with id '" + id + "' not found!");
            }
        } catch (SQLException sqlException) {
            LOGGER.error("SQL-error " + sqlException.getMessage());
        }
        return user;
    }

    /**
     * Stores a specific user in the database. Takes a User object as an argument, gets the field
     * values and makes use of a PreparedStatement to execute a parameterized SQL-query with these
     * values. The user id (idUser) is an Auto Increment value, which is generated when a new
     * user is stored. The {@link #executeInsertStatementWithKey()} executes an insert statement
     * and returns the generated key, which is then set as the user id of the provided User object.
     *
     * @throws SQLException if the parameter index is out of range; if a database access error
     * occurs; if other SQL-error occur
     * @see #setupPreparedStatementWithKey(String sql)
     * @see #executeInsertStatementWithKey()
     * @since 1.0
     */
    @Override
    public void storeOne(User type) {
        String sql = "INSERT INTO User(userName, password, role, firstName, lastName) VALUES(?,?," +
                "?,?,?);";
        try {
            setupPreparedStatementWithKey(sql);
            setPreparedStatementParameters(type);
            type.setIdUser(executeInsertStatementWithKey());
        } catch (SQLIntegrityConstraintViolationException duplicateUserName) {
            LOGGER.error("Gebruikersnaam bestaat al!");
            CreateUpdateUserController.setSqlErrorMessage("Gebruikersnaam bestaat al!");
        } catch (SQLException sqlException) {
            LOGGER.error("SQL-error " + sqlException.getMessage());
        }
    }

    //@VG - retrieve users from DB per specific rol
    public ArrayList<User> getUserByRole() {
        String sql = "SELECT * FROM user WHERE role = 'COORDINATOR';";
        ArrayList<User> rolelist = new ArrayList<>();
        try {
            setupPreparedStatement(sql);
            ResultSet resultSet = executeSelectStatement();
            User user;
            while (resultSet.next()) {
                int idUser = resultSet.getInt("idUser");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                user = new User(idUser, firstName, lastName);
                rolelist.add(user);
            }
        } catch (SQLException sqlException) {
            System.out.println("SQL error " + sqlException.getMessage());
        }
        return rolelist;
    }

}
