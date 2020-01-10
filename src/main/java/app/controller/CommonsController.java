package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import static app.constants.Constants.LOGIN;
import static app.constants.Constants.LOGOUT;
import static app.page_path.PagePath.HOME_PAGE;
import static app.page_path.PagePath.LOGOUT_PAGE;

@Controller
public class CommonsController {

  @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
  public String home() {
    return "pages/login";
  }

  @GetMapping(LOGOUT)
  public String logout() {
    return LOGOUT_PAGE.getPath();
  }
}
