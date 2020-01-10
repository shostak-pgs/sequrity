package app.dao;

import app.entity.User;
import app.exception.UserNotFoundException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserDao {

    /**
     * Returns the user by the transferred name
     * @param userName user's name
     * @return the {@link User}
     * error or other errors.
     */
    Optional<User> getUserByName(String userName);

    /**
     * Return list contains all users in base
     * @return the list with all users
     * @throws SQLException an exception that provides information on a database access
     * error or other errors.
     */
    List<User> getAllUsers() throws SQLException;

    /**
     * Add user in base
     * @param user the {@link User}
     * @throws SQLException an exception that provides information on a database access
     * error or other errors.
     */
    void addUser(User user) throws SQLException;

}
