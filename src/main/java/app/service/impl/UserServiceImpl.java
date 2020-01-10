package app.service.impl;

import app.dao.UserDao;
import app.dao.UserDto;
import app.dao.impl.UserDaoImpl;
import app.exception.UserNameNotFoundException;
import app.utils.converter.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import javax.validation.constraints.NotNull;
import java.sql.SQLException;

@Service
@Lazy
public class UserServiceImpl {
    private UserDao userDao;
    private UserConverter converter;

    @Autowired
    public void setUserDao(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setConverter(UserConverter converter) {
        this.converter = converter;
    }

    /**
     * Return the User by name. If he not exist, create new user and the return
     * @param name user's name
     * @return the User
     */
    @NotNull
    public UserDto getUser(final String name) {
        return converter.toDto((userDao.getUserByName(name)).orElseThrow(() -> new UserNameNotFoundException("User with email='" + name + "' not found")));
    }

    /**
     * Create a new user in Data Base
     * @param dto the user's dto
     * @return created user
     */
    public UserDto create(final UserDto dto) throws SQLException {
        userDao.addUser(converter.toEntity(dto));
        return dto;
    }
}