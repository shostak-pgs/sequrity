package app.utils.converter;

import app.dao.UserDto;
import app.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import static app.constants.Constants.EMPTY;

@Component
public class UserConverter {

    private final PasswordEncoder encoder;

    @Autowired
    public UserConverter(final PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public User toEntity(final UserDto dto) {
        return new User(dto.getId(), dto.getLogin(), encoder.encode(dto.getPassword()), dto.getRole());
    }

    public UserDto toDto(final User entity) {
        return new UserDto(entity.getId(), entity.getLogin(), EMPTY, entity.getRole());
    }

    public User enrich(final User entity, final UserDto dto) {
        entity.setLogin(dto.getLogin());
        entity.setPassword(encoder.encode(dto.getPassword()));
        entity.setRole(dto.getRole());
        return entity;
    }
}
