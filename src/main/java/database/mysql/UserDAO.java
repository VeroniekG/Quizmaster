package database.mysql;

import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Data Access Object voor gebruikers van de applicatie
 * @Author dleertouwer
 */
public class UserDAO extends AbstractDAO implements GenericDAO<User> {

    public UserDAO(DBAccess dBaccess) {
        super(dBaccess);
    }

    /**
     * TODO: userName validatie case-sensitive!
     * Haalt een gebruiker op uit de database o.b.v. de gebruikersnaam.
     * @param name de gebruikersnaam als String
     * @return een object van het type User
     * @exception SQLException wanneer het uitvoeren van de SQL-query een fout oplevert.
     */
    public User getUserByName(String name) {
        String sql = "SELECT * FROM User WHERE userName = ?";
        User user = null;
        try {
            setupPreparedStatement(sql);
            preparedStatement.setString(1, name);
            ResultSet resultSet = executeSelectStatement();
            if (resultSet.next()) {
                String userName = resultSet.getString("userName");
                String password = resultSet.getString("password");
                user = new User(userName, password);
                user.setIdUser(resultSet.getInt("idUser"));
            } else {
                System.out.println("Gebruiker '" + name + "' niet gevonden!");
            }
        } catch (SQLException sqlException){
            System.out.println("SQL error " + sqlException.getMessage());
        }
        return user;
    }

    /**
     * Haalt alle gebruikers in de database op
     * @return een ArrayList met objecten van het type User
     * @exception SQLException wanneer het uitvoeren van de SQL-query een fout oplevert.
     */
    @Override
    public ArrayList<User> getAll() {
        String sql = "Select * From Klant";
        ArrayList<User> userslist = new ArrayList<>();
        try {
            setupPreparedStatement(sql);
            ResultSet resultSet = executeSelectStatement();
            User user;
            while (resultSet.next()) {
                String userName = resultSet.getString("userName");
                String password = resultSet.getString("password");
                user = new User(userName, password);
                user.setIdUser(resultSet.getInt("idUser"));
                userslist.add(user);
            }
        } catch (SQLException sqlException){
            System.out.println("SQL error " + sqlException.getMessage());
        }
        return  userslist;
    }

    /**
     * Haalt een specifieke gebruiker op uit de database
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
                user = new User(userName, password);
                user.setIdUser(id);
            } else {
                System.out.println("Gebruiker met id " + id + " niet gevonden!");
            }

        }
        catch (SQLException sqlException){
            System.out.println("SQL error " + sqlException.getMessage());
        }
        return user;
    }

    /**
     * Slaat een specifieke gebruiker op in de database
     * @exception SQLException wanneer het uitvoeren van de SQL-query een fout oplevert.
     */
    @Override
    public void storeOne(User type) {
        String sql = "INSERT INTO User(userName, password) VALUES(?,?);";
        try {
            setupPreparedStatementWithKey(sql);
            preparedStatement.setString(1, type.getUserName());
            preparedStatement.setString(2, type.getPassword());
            int id = executeInsertStatementWithKey();
            type.setIdUser(id);
        } catch (SQLException sqlException) {
            System.out.println("SQL error " + sqlException.getMessage());
        }
    }

}
