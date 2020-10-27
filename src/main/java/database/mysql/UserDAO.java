package database.mysql;

import model.Role;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Interacts with the User model and maps application calls to the persistence layer. Extends
 * {@link AbstractDAO} and contains methods to retrieve all users, to retrieve one user by name, to
 * retrieve one user by id and to store one user.
 *
 * @author dleertouwer
 * @version 1.0.9
 * @see User
 * @since 1.0.0
 */
public class UserDAO extends AbstractDAO implements GenericDAO<User> {

    private static final Logger LOGGER = LogManager.getLogger(UserDAO.class);

    public UserDAO(DBAccess dBaccess) {
        super(dBaccess);
    }

    /**
     * Retrieves the values of the designated columns in the current row of the passed ResultSet
     * object and constructs a new User object with the results.
     *
     * @param resultSet an object of type ResultSet
     *
     * @return an object of type User
     * @throws SQLException if one or more columnLabels are not valid; if a database access
     * error occurs or this method is called with a closed ResultSet
     * @see ResultSet
     * @since 1.0.0
     */
    private User setUserWithResultset(ResultSet resultSet) throws SQLException {
        int idUser = resultSet.getInt("idUser");
        String userName = resultSet.getString("userName");
        String password = resultSet.getString("password");
        String role = resultSet.getString("role");
        String firstName = resultSet.getString("firstName");
        String lastName = resultSet.getString("lastName");
        return new User(idUser, firstName, lastName, userName, password, Role.valueOf(role));
    }

    /**
     * Retrieves a user from the database by username. A PreparedStatement is used to execute a
     * parameterized SQL-query with BINARY function. This function converts userName to a binary
     * string, which makes the query case-sensitive. {@link #setUserWithResultset(ResultSet)} is
     * called with the resulting ResultSet to convert the contained values to a corresponding
     * User object.
     *
     * @param name the username as a String
     *
     * @return an object of type User
     * @throws SQLException if the Parameter index is out of ronge or a database access error
     * occurs
     * @see AbstractDAO#setupPreparedStatement(String sql)
     * @see ResultSet
     * @since 1.0.0
     */
    public User getOneByName(String name) {
        String sql = "SELECT * FROM User WHERE BINARY userName = ?";
        User user = null;
        try {
            setupPreparedStatement(sql);
            preparedStatement.setString(1, name);
            ResultSet resultSet = executeSelectStatement();
            if (resultSet.next()) {
                user = setUserWithResultset(resultSet);
            } else {
                LOGGER.debug("Gebruiker '" + name + "' niet gevonden!");
            }
        } catch (SQLException sqlException) {
            LOGGER.error("SQL error " + sqlException.getMessage());
        }
        return user;
    }

    /**
     * Retrieves a user from the database by idUser. A PreparedStatement is used to execute a
     * parameterized SQL-query. {@link #setUserWithResultset(ResultSet)} is called with the
     * resulting ResultSet to convert the contained values to a corresponding User object.
     *
     * @return an object of type User
     * @throws IllegalArgumentException if the requested user id does not exist
     * @throws SQLException if the parameter index is out of range; if a database access error
     * occurs or if another SQL-error occurs
     * @see AbstractDAO#setupPreparedStatement(String sql)
     * @see ResultSet
     * @since 1.0.0
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
                user = setUserWithResultset(resultSet);
            } else {
                throw new IllegalArgumentException("User with id '" + id + "' not found!");
            }

        } catch (SQLException sqlException) {
            LOGGER.error("SQL error " + sqlException.getMessage());
        }
        return user;
    }

    /**
     * Stores a specific user in the database.
     *
     * @throws SQLException if the parameter index is out of range; if a database access error
     * occurs or if another SQL-error occurs
     * @see AbstractDAO#setupPreparedStatementWithKey(String sql)
     * @see AbstractDAO#executeInsertStatementWithKey()
     */
    @Override
    public void storeOne(User type) {
        String sql = "INSERT INTO User(userName, password, role, firstName, lastName) VALUES(?,?," +
                "?,?,?);";
        try {
            setupPreparedStatementWithKey(sql);
            preparedStatement.setString(1, type.getUserName());
            preparedStatement.setString(2, type.getPassword());
            preparedStatement.setString(3, type.getRole().name());
            preparedStatement.setString(4, type.getFirstName());
            preparedStatement.setString(5, type.getLastName());
            int id = executeInsertStatementWithKey();
            type.setIdUser(id);
        } catch (SQLException sqlException) {
            LOGGER.error("SQL error " + sqlException.getMessage());
        }
    }

    /**
     * Retrieves all users from the database.
     *
     * @return an ArrayList with objects of type User
     * @throws SQLException when executing the SQL-query results in an error.
     */
    @Override
    public ArrayList<User> getAll() {
        String sql = "Select * From User";
        ArrayList<User> userslist = new ArrayList<>();
        try {
            setupPreparedStatement(sql);
            ResultSet resultSet = executeSelectStatement();
            User user;
            while (resultSet.next()) {
                user = setUserWithResultset(resultSet);
                userslist.add(user);
            }
        } catch (SQLException sqlException) {
            LOGGER.error("SQL error " + sqlException.getMessage());
        }
        return userslist;
    }

}
