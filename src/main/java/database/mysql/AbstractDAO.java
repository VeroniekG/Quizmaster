package database.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Centralizes shared database edits
 * @author remideboer, gerke de boer, Michael Oosterhout
 */
public abstract class AbstractDAO {

	protected DBAccess dbAccess;
	protected PreparedStatement preparedStatement;

	public AbstractDAO(DBAccess dbAccess) {
		this.dbAccess = dbAccess;
	}
	
	/**
	 * Creates a preparedStatement for the sql string. A DAO uses this to fill the parameters.
	 *
	 * @param sql,
	 *            de SQl query
	 */
	protected void setupPreparedStatement(String sql) throws SQLException {
		preparedStatement = dbAccess.getConnection().prepareStatement(sql);
	}

	/**

	 *
	 * Executes the preparedStatement without a ResultSet. Is used for insert, update and delete statements.
	 *
	 */
	protected void executeManipulateStatement() throws SQLException {
		preparedStatement.executeUpdate();
	}

	/**
	 *
	 * Executes the preparedStatement with a Resultset. Is used for select statements.
	 *
	 */
	protected ResultSet executeSelectStatement() throws SQLException {
		return preparedStatement.executeQuery();
	}

	/**
	 * Creates a preparedStatement for the sql string, which has to return a generated key.
	 * @param sql,
	 *            de SQL query
	 */
	protected void setupPreparedStatementWithKey(String sql) throws SQLException {
		preparedStatement = dbAccess.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	}

	/**
	 *
	 * Executes the prepared statement en returns the generated key.
	 * Is used for an insert in a AutoIncrement table.
	 */
	protected int executeInsertStatementWithKey() throws SQLException {
		preparedStatement.executeUpdate();
		ResultSet resultSet = preparedStatement.getGeneratedKeys();
		int gegenereerdeSleutel = 0;

			gegenereerdeSleutel = resultSet.getInt(1);

		return gegenereerdeSleutel;
	}
}
