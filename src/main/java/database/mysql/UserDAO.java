package database.mysql;

import model.Role;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * DAO for User Model Object. Used to access persisted user data.
 *
 * @author dleertouwer
 * @version 1.0.2
 * @see model.User
 * @see database.mysql.DBAccess
 * @since 1.0
 */
public class UserDAO extends AbstractDAO implements GenericDAO<User> {

    //private static final Log log = LogFactory.getLog(UserDAO.class);
    private static final Logger log = LogManager.getLogger(UserDAO.class);

    public UserDAO(DBAccess dBaccess) {
        super(dBaccess);
    }

    /**
     * Haalt een gebruiker op uit de database o.b.v. de gebruikersnaam.
     *
     * @param name de gebruikersnaam als String
     *
     * @return een object van het type User
     * @exception SQLException wanneer het uitvoeren van de SQL-query een fout oplevert.
     */
    public User getUserByName(String name) {
        // Case-sensitive SQL-query
        String sql = "SELECT * FROM User WHERE BINARY userName = ?";
        ResultSet resultSet = null;
        User user = null;
        try {
            setupPreparedStatement(sql);
            preparedStatement.setString(1, name);
            resultSet = executeSelectStatement();
            if (resultSet.next()) {
                String userName = resultSet.getString("userName");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                user = new User(userName, password, Role.valueOf(role));
                user.setIdUser(resultSet.getInt("idUser"));
            } else {
                log.info("Gebruiker '" + name + "' niet gevonden!");
            }
        } catch (SQLException sqlException) {
            System.out.println("SQL error " + sqlException.getMessage());
        } finally {
            dbAccess.closeConnection();
        }
        return user;
    }

    /**
     * Haalt een specifieke gebruiker op uit de database
     *
     * @return een object van het type User
     * @exception SQLException wanneer het uitvoeren van de SQL-query een fout oplevert.
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
        } finally {
            dbAccess.closeConnection();
        }
        return user;
    }

    /**
     * Slaat een specifieke gebruiker op in de database
     *
     * @exception SQLException wanneer het uitvoeren van de SQL-query een fout oplevert.
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
        } finally {
            dbAccess.closeConnection();
        }
    }

    /**
     * Haalt alle gebruikers in de database op
     *
     * @return een ArrayList met objecten van het type User
     * @exception SQLException wanneer het uitvoeren van de SQL-query een fout oplevert.
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
        } finally {
            dbAccess.closeConnection();
        }
        return userslist;
    }

}
