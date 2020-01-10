package app.exception;

public class UserNameNotFoundException extends RuntimeException {
    public UserNameNotFoundException(Exception e){
        super(e);
    }
    public UserNameNotFoundException(String str){
        super(str);
    }
}
