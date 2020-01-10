package app.config.security;

import app.dao.impl.UserDaoImpl;
import app.entity.User;
import app.exception.UserNameNotFoundException;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import javax.inject.Inject;

@Service
@Lazy
public class CustomUserDetailsService implements UserDetailsService{

    private UserDaoImpl dao;

    @Inject
    public void setDao(UserDaoImpl dao){
        this.dao=dao;
    }

    /**
     * Load user from DB if it is exist. Else throw UserNameNotFoundException
     * @param name user's name
     * @return the {@link CustomUserPrincipal}
     * @throws UserNameNotFoundException throws when no one user found in data base
     */
    public CustomUserPrincipal loadUserByUsername(final String name) throws UserNameNotFoundException {
        final User user = dao.getUserByName(name)
                        .orElseThrow(() -> new UserNameNotFoundException("User with name = " + name + " not found"));
        System.out.println(user);
        return new CustomUserPrincipal(user);
    }
}
