package database.mysql;

/**
 * Extends interface {@link GenericDAO} to add extra methods, without refactoring of existing code.
 *
 * @param <T> a type of Object
 *
 * @author Daniel Leertouwer
 * @version 1.0.0
 * @since 1.0
 */
public interface DAO<T> extends GenericDAO<T> {

    public T getOneByName(String name);

}