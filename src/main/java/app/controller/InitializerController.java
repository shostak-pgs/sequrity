package app.controller;

import app.dao.UserDto;
import app.entity.Good;
import app.entity.Order;
import app.service.ServiceException;
import app.service.ServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static app.constants.Constants.*;
import static app.page_path.PagePath.*;
import static app.controller.GoodsAddController.ORDER_ID;

@Controller
@RequestMapping("/initialize/createUserServlet")
public class InitializerController {
    public static final String ALL_GOODS = "allGoodsList";
    public static final String AUTHORIZE_USER = "/createUserServlet";
    public static final String CREATE_USER = ("createUserServlet/createNewUser");

    private List<Good> allGoodsList;

    private ServiceProvider serviceProvider;

    @Autowired
    public InitializerController(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    @GetMapping("/createUser")
    public ModelAndView redirectToAddUser() {
        return new ModelAndView(ADD_USER.getPath());
    }

    @PostMapping(CREATE_USER)
    public ModelAndView createNewUser(@RequestParam(NAME) String userName, @RequestParam(PASSWORD) String password, HttpServletRequest request) throws SQLException, ServiceException {

        allGoodsList = serviceProvider.getGoodsService().getGoods();
        ModelAndView model;
        if (userName.equals(EMPTY)) {
            model = new ModelAndView(TERMS_ERROR.getPath());

        } else {
            serviceProvider.getUserService().create(new UserDto(userName, password));
            UserDto user = serviceProvider.getUserService().getUser(userName);
            Order order = (Order) serviceProvider.getOrderService().get(String.valueOf(user.getId()));

            addSessionAttributes(user, order, request);
            model = createView(user, order);
        }
        return model;
    }

    @PostMapping("/{id}")
    public ModelAndView authorizeUser(@RequestParam(NAME) String userName, HttpServletRequest request) throws SQLException, ServiceException {
        allGoodsList = serviceProvider.getGoodsService().getGoods();
        ModelAndView model;
            UserDto user = serviceProvider.getUserService().getUser(userName);
            Order order = (Order) serviceProvider.getOrderService().get(String.valueOf(user.getId()));

            addSessionAttributes(user, order, request);
            model = createView(user, order);
        return model;
    }

    private ModelAndView createView(UserDto user, Order order) throws ServiceException {

        ModelAndView model = new ModelAndView(ADD.getPath()).addObject(ALL_GOODS, allGoodsList);
        model.addAllObjects(Stream.of(new Object[][]{
                {NAME, user.getLogin()},
                {BASKET, serviceProvider.getOrderGoodsService().getOrderedGoods(order.getId())},
        }).collect(Collectors.toMap(data -> (String) data[0], data -> data[1])));
        return model;
    }

    private void addSessionAttributes(UserDto user, Order order, HttpServletRequest request) {

        HttpSession session = request.getSession();
        session.setAttribute(ALL_GOODS, allGoodsList);
        session.setAttribute(ID, user.getId());
        session.setAttribute(ORDER_ID, order.getId());
        session.setAttribute(NAME, user.getLogin());
    }
}