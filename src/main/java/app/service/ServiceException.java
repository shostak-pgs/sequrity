package app.service;

import java.io.IOException;

public class ServiceException extends IOException {
    public ServiceException(String str, Exception e){
        super(str, e);
    }
    public ServiceException(String str){
        super(str);
    }
}
