package app.controller;

import app.service.ServiceException;
import app.service.impl.OrderGoodsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static app.constants.Constants.*;
import static app.controller.InitializerController.ALL_GOODS;
import static app.page_path.PagePath.*;
import static app.service.impl.OrderGoodsServiceImpl.PRICE;
import static app.service.impl.OrderGoodsServiceImpl.PRINT_GOODS;

@Controller
@RequestMapping("/add")
public class GoodsAddController{
    private static final String ITEM = "good";
    private static final String EMPTY_ELEMENT = "--Choose item--";
    public static final String ORDER_ID = "Order id";
    public static final String BASKET = "order";

    private OrderGoodsServiceImpl service;

    @Autowired
    public GoodsAddController(OrderGoodsServiceImpl service) {
        this.service = service;
    }

    /**
     * Handles {@link HttpServlet} POST Method.
     * If the item has not been selected returns to the selection page. If selected - adds it to
     * the basket and returns to the selection page
     * @param request  the {@link HttpServletRequest} contains selected item as a parameter
     * @throws IOException      thrown when occur exception in getting Writer object
     */
    @RequestMapping(value = "/goodsAddServlet", method = RequestMethod.POST)
    protected ModelAndView addGood(final HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();

        String chosenItem = request.getParameter(ITEM);
        putToBasket(chosenItem, session);

        return createView(session, ADD.getPath());
    }

    private ModelAndView createView(HttpSession session, String path) {

        ModelAndView model = new ModelAndView(path);
        model.addAllObjects(Stream.of(new Object[][]{
                {NAME, session.getAttribute(NAME)},
                {ALL_GOODS, session.getAttribute(ALL_GOODS)},
                {BASKET, session.getAttribute(BASKET)},
        }).collect(Collectors.toMap(data -> (String) data[0], data -> data[1])));
        return model;
    }

    /**
     * Handles {@link HttpServlet} GET Method. Redirect user if no goods were chosen after authentication
     * and order submitting
     */
    @RequestMapping(value = {"/goodsAddServlet", "/complete"}, method = RequestMethod.GET)
    public ModelAndView doGet() {
        return new ModelAndView(ADD.getPath());
    }

    /**
     * Add not empty goods to basket
     * @param chosenItem item for adding
     */
    public void putToBasket(final String chosenItem, HttpSession session) throws ServiceException {
        long orderId = (long)session.getAttribute(ORDER_ID);
        if (!chosenItem.equals(EMPTY_ELEMENT)) {
            service.add(chosenItem, orderId);
        }
        session.setAttribute(BASKET, service.getOrderedGoods(orderId));
    }

    @RequestMapping(value = "/complete", method = RequestMethod.POST)
    protected ModelAndView completeOrder(final HttpServletRequest request) throws ServiceException {
        String chosenItem = request.getParameter(ITEM);
        HttpSession session = request.getSession();
        putToBasket(chosenItem, session);
        return printCheckView(session);
    }

    /**
     * Chooses path for redirection by transferred basket's content
     * @param session {@link HttpSession} current session
     * @return path to redirect
     */
    private ModelAndView printCheckView(HttpSession session) throws ServiceException {
        ModelAndView model;
        Map<String, Integer> basket = (Map<String, Integer>)session.getAttribute(BASKET);
            if (basket.size() == 0) {
                model = new ModelAndView(EMPTY_BASKET_ERROR.getPath());

            } else {
                model = new ModelAndView(PRINT_CHECK.getPath());
                Map<String, String> params = service.createPresentationParams(basket, (long)session.getAttribute(ORDER_ID));
                model.addAllObjects(Stream.of(new Object[][]{
                        {NAME, session.getAttribute(NAME)},
                        {PRINT_GOODS, params.get(PRINT_GOODS)},
                        {PRICE, params.get(PRICE)},
                }).collect(Collectors.toMap(data -> (String) data[0], data -> data[1])));
                return model;
        }
        return model;
    }
}
