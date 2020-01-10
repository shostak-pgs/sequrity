package app.exception.handler;

import app.exception.UserNameNotFoundException;
import app.exception.UserNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import static app.page_path.PagePath.*;

@ControllerAdvice
public class UserExceptionHandler  {
    private static String ERROR = "error";

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handleError404(Exception e)   {
        return new ModelAndView(ERROR_PATH_404.getPath()).addObject(ERROR, e.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ModelAndView handleUserNotFound(Exception e)   {
        return new ModelAndView(ERROR_PATH_User_NOT_FOUND.getPath()).addObject(ERROR, e.getMessage());
    }

    @ExceptionHandler(UserNameNotFoundException.class)
    public ModelAndView handleUserNameNotFound(Exception e)   {
        return new ModelAndView(ERROR_PATH_User_NOT_FOUND.getPath()).addObject(ERROR, e.getMessage());
    }
}
