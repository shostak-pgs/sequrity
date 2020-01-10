package app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component("daoProvider")
public class DAOProvider {

    public static UserDao userDao;
    public static GoodDao goodDao;
    public static OrderDao orderDao;
    public static OrderGoodsDao orderGoods;

    /**
     * The constructor establishes a connection and creates the provider object
     */
    private DAOProvider(){}

    @Autowired
    public void setOrderDao(OrderDao orderDao) {
        DAOProvider.orderDao = orderDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        DAOProvider.userDao = userDao;
    }

    @Autowired
    public void setGoodDao(GoodDao goodDao) {
        DAOProvider.goodDao = goodDao;
    }

    @Autowired
    public void setOrderGoodsDao(OrderGoodsDao orderGoodsDao) {
        DAOProvider.orderGoods = orderGoodsDao;
    }

    /**
     * @return the GoodDao object
     */
    @Bean(name = "userDaoImpl")
    public UserDao getUserDao(){
       return userDao;
    }

    /**
     * @return the GoodDao object
     */
    public GoodDao getGoodDao(){
        return goodDao;
    }

    /**
     * @return the OrderDao object
     */
    public OrderDao getOrderDao(){
        return orderDao;
    }

    /**
     * @return the OrderGoodsDao object
     */
    public OrderGoodsDao getOrderGoodsDao(){
        return orderGoods;
    }

    /**
     * Creates data base and fills it with values. Throws runtime exception if db init is falls
     * @return boolean value
     */
    private static final String CREATE_DB_URL = "jdbc:h2:mem:testdb;INIT=RUNSCRIPT FROM 'classpath:create_tables.sql'\\;RUNSCRIPT FROM 'classpath:populate.sql';DB_CLOSE_DELAY=-1";
    @Bean(name="data")
    @Scope(value = "singleton")
    public boolean init() {
        try {
            Connection connection = DriverManager.getConnection(CREATE_DB_URL);
            connection.close();
            System.out.println("Base was inited");
        } catch (SQLException e) {
            throw new RuntimeException("Unable init base", e);
        }
        return true;
    }

}
