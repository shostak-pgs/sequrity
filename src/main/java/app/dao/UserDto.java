package app.dao;

import app.role.Role;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class UserDto {
    private final Long id;
    private final String login;
    private final String password;
    private final Role role;

    public UserDto(final long id, @NotBlank final String login, @NotBlank final String password, final Role role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public UserDto(@NotBlank final String login, @NotBlank final String password) {
        this.id = 0L;
        this.login = login;
        this.password = password;
        this.role = Role.USER;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDto)) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(id, userDto.id) &&
                login.equals(userDto.login) &&
                password.equals(userDto.password) &&
                role == userDto.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, role);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password= \\" +
                ", role=" + role +
                '}';
    }
}
