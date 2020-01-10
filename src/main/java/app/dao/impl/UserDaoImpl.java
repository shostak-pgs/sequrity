package app.dao.impl;

import app.dao.UserDao;
import app.entity.User;
import app.exception.UserNameNotFoundException;
import app.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static app.constants.Constants.*;
import static app.role.Role.USER;

@Repository(USER_DAO)
@Lazy
public class UserDaoImpl implements UserDao {

    private static final String SELECT_ALL_USERS_SQL_STATEMENT = "SELECT * FROM Users";
    private static final String INSERT_USER_SQL_STATEMENT = "INSERT INTO Users (userName, password, role) VALUES (?,?,?)";

    private DataSource provider;

    @Autowired
    public void setProvider(DataSource provider) {
        this.provider = provider;
    }

    /**
     * Returns the user by the transferred name
     * @param userName user's name
     * @return the {@link User}
     * error or other errors.
     */
        @Override
        public Optional<User> getUserByName(final String userName) {
            return  getAllUsers().stream().filter(user -> user.getLogin().equals(userName)).findFirst();
        }

    /**
     * Return list contains all users in base
     * @return the list with all users
     */
    @Override
    public List<User> getAllUsers()  {
        List<User> userList = new ArrayList<>();
        try (PreparedStatement st = provider.getConnection().prepareStatement(SELECT_ALL_USERS_SQL_STATEMENT);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                userList.add(new User(rs.getLong(ID), rs.getString(USER_NAME),
                        rs.getString(PASSWORD), Role.toRole(rs.getString(ROLE))));
            }
        } catch (SQLException e){
            throw new UserNameNotFoundException("List of users not available");
        }
        return userList;
    }

    /**
     * Add user in base by the transferred name
     * @param user user
     * @throws SQLException an exception that provides information on a database access
     * error or other errors.
     */
    @Override
    public void addUser(User user) throws SQLException {
        try (PreparedStatement st = provider.getConnection().prepareStatement(INSERT_USER_SQL_STATEMENT)) {
            st.setString(1, user.getLogin());
            st.setString(2, user.getPassword());
            st.setString(3, USER.toString());
            st.executeUpdate();
        }
    }
}
