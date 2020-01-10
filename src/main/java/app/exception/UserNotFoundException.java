package app.exception;

import java.sql.SQLException;

public class UserNotFoundException extends SQLException {
    public UserNotFoundException(Exception e){
        super(e);
    }
    public UserNotFoundException(String str){
        super(str);
    }
}
