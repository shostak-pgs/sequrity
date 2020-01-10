package app.service.impl;

import app.dao.OrderDao;
import app.entity.Order;
import app.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.sql.SQLException;

@Service
@Lazy
public class OrderServiceImpl {
    private OrderDao orderDao;
    private Order order;

    /**
     * Return the Order by User's id. If it not exist, create a new order and the return
     * @param stringId user's id
     * @return the Order
     */
    public Object get(String stringId) throws ServiceException {
        Long id = Long.parseLong(stringId);
        if (!isExist(id)) {
            order = create(id);
        }
        return order;
    }

    /**
     * Check if order exist in base. Return boolean result of checking
     * @param id user's id for checking
     * @return the result
     */
    private boolean isExist(Long id) throws ServiceException {
        boolean isPresent = true;

        try {
            order = orderDao.getOrderByUserId(id);
            if (order == null) {
                return false;
            }
        } catch (SQLException e) {
            isPresent = false;
            throw new ServiceException("Checking if order exist");
        }
        return isPresent;
    }

    /**
     * Create a new Order by the transferred user's id
     * @param id the user's id
     * @return created user
     */
    private Order create(Long id) throws ServiceException {
        Order order;

        try {
            orderDao.addToOrder(id);
            order = orderDao.getOrderByUserId(id);
        } catch (SQLException e) {
            throw new ServiceException("Unsuccessful order creation. Check out data base connection", e);
        }
        return order;
    }

    /**
     * Update order price by order id
     * @param orderPrice the order price
     * @param orderId the order id
     */
    public boolean updateByUserId(double orderPrice, Long orderId) throws SQLException {
        return orderDao.updateOrderById(orderPrice, orderId);
    }

    @Autowired
    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }
}