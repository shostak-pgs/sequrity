package app.service;

import app.service.impl.GoodsServiceImpl;
import app.service.impl.OrderGoodsServiceImpl;
import app.service.impl.OrderServiceImpl;
import app.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class ServiceProvider {

    public UserServiceImpl userServiceImpl;
    public OrderServiceImpl orderServiceImpl;
    public OrderGoodsServiceImpl orderGoodsService;
    public GoodsServiceImpl goodsService;

    private ServiceProvider instance;

    @Autowired
    public ServiceProvider(UserServiceImpl userServiceImpl, OrderServiceImpl orderServiceImpl, OrderGoodsServiceImpl orderGoodsService, GoodsServiceImpl goodsService) {
        this.userServiceImpl = userServiceImpl;
        this.orderServiceImpl = orderServiceImpl;
        this.orderGoodsService = orderGoodsService;
        this.goodsService = goodsService;
    }

    /**
     * Return the single provider object
     * @return the {@link ServiceProvider}
     */

    public ServiceProvider getInstance(){
        return instance;
    }

    @Autowired
    private void setInstance(ServiceProvider instance) {
        this.instance = instance;
    }

    /**
     * @return the UserService object
     */
    public UserServiceImpl getUserService(){
        return userServiceImpl;
    }

    /**
     * @return the OrderService object
     */
    public OrderServiceImpl getOrderService(){
        return orderServiceImpl;
    }

    /**
     * @return the OrderGoodsService object
     */
    public OrderGoodsServiceImpl getOrderGoodsService(){
        return orderGoodsService;
    }

    /**
     * @return the GoodsService object
     */
    public GoodsServiceImpl getGoodsService(){
        return goodsService;
    }
}
