package database.mysql;

import model.Role;
import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Data Access Object voor gebruikers van de applicatie
 *
 * @Author dleertouwer
 */
public class UserDAO extends AbstractDAO implements GenericDAO<User> {

    public UserDAO(DBAccess dBaccess) {
        super(dBaccess);
    }

    /**
     * Retrieves a user from the database based on the username.
     *
     * @param name the username as String
     *
     * @return an object with the type User
     * @exception SQLException when executing the SQL-query results in an error.
     */
    public User getUserByName(String name) {
        // Case-sensitive SQL-query
        String sql = "SELECT * FROM User WHERE BINARY userName = ?";
        User user = null;
        try {
            setupPreparedStatement(sql);
            preparedStatement.setString(1, name);
            ResultSet resultSet = executeSelectStatement();
            if (resultSet.next()) {
                String userName = resultSet.getString("userName");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                user = new User(userName, password, Role.valueOf(role));
                user.setIdUser(resultSet.getInt("idUser"));
            } else {
                System.out.println("Gebruiker '" + name + "' niet gevonden!");
            }
        } catch (SQLException sqlException) {
            System.out.println("SQL error " + sqlException.getMessage());
        }
        return user;
    }

    /**
     * Retrieves a specific user from the database
     *
     * @return an object with the type User
     * @exception SQLException when executing the SQL-query results in an error.
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
                String userName = resultSet.getString("userName");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                user = new User(userName, password, Role.valueOf(role));
                user.setIdUser(id);
            } else {
                System.out.println("Gebruiker met id " + id + " niet gevonden!");
            }

        } catch (SQLException sqlException) {
            System.out.println("SQL error " + sqlException.getMessage());
        }
        return user;
    }

    /**
     * Stores a specific user in the database.
     *
     * @exception SQLException when executing the SQL-query results in an error.
     */
    @Override
    public void storeOne(User type) {
        String sql = "INSERT INTO User(userName, password, role) VALUES(?,?,?);";
        try {
            setupPreparedStatementWithKey(sql);
            preparedStatement.setString(1, type.getUserName());
            preparedStatement.setString(2, type.getPassword());
            preparedStatement.setString(3, type.getRole().name());
            int id = executeInsertStatementWithKey();
            type.setIdUser(id);
        } catch (SQLException sqlException) {
            System.out.println("SQL error " + sqlException.getMessage());
        }
    }

    /**
     * Retrieves all users from the database
     *
     * @return een ArrayList with objects with the type User
     * @exception SQLException when executing the SQL-query results in an error.
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
                String userName = resultSet.getString("userName");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                user = new User(userName, password, Role.valueOf(role));
                user.setIdUser(resultSet.getInt("idUser"));
                userslist.add(user);
            }
        } catch (SQLException sqlException) {
            System.out.println("SQL error " + sqlException.getMessage());
        }
        return userslist;
    }

}
