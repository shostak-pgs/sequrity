package app.service.impl;

import app.dao.DAOProvider;
import app.entity.Good;
import app.entity.OrderGoods;
import app.service.ServiceException;
import app.utils.GoodsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.util.*;

@Service
@Lazy
public class OrderGoodsServiceImpl {
    public static final String PRINT_GOODS = "goodsList";
    public static final String PRICE = "price";

    private DAOProvider dao;

    /**
     * @param dao the {@link DAOProvider}
     */
    @Autowired
    public void setDao(DAOProvider dao) {
        this.dao = dao;
    }

    /**
     * Returns the list contains goods in order. Sampling by transferred id
     * @param orderId id of order for list building
     * @return the list
     */
    public List<Good> getGoods(Long orderId) throws ServiceException {
        List<Good> orderedGoods;
        try {
            List<OrderGoods> inCurrentOrder = dao.getOrderGoodsDao().getByOrderId(orderId);
            orderedGoods = getGoodsInCurrentOrder(inCurrentOrder);

        } catch (SQLException e) {
            throw new ServiceException("Exception in data base connection,", e);
        }
        return orderedGoods;
    }

    /**
     * Returns the map contains name of item, its price and number of it in order
     * @param orderId id of order for map building
     * @return the map
     */
    public Map<String, Integer> getOrderedGoods(Long orderId) throws ServiceException {
        Map<String, Integer> map = new HashMap<>();
        List<Good> orderedGoods = getGoods(orderId);
        for(Good good : orderedGoods) {
            String item = (good.getName() + " (" + good.getPrice() + " $)");
            int value = 1;
            if (map.containsKey(item)) {
                value = map.get(item) + 1;
                map.remove(item);
            }
            map.put(item, value);
        }
        return map;
    }

    /**
     * Added product by name to the OrderGoods db table
     * @param name name of product to be added
     * @param orderId id of order in which the product included
     */
    public void add(String name, Long orderId) throws ServiceException {
        try {
            Good good = dao.getGoodDao().getGood(GoodsUtil.getName(name));
            dao.getOrderGoodsDao().addToOrderGood(orderId, good.getId());
        } catch (SQLException e) {
            throw new ServiceException("Exception with basket. Try again later", e);
        }
    }

    /**
     * Returns the list contains goods in order. Sampling by transferred OrderGoods list
     * @param goodsInCurrentOrder the OrderGoods list for good list creation
     * @return the list
     */
  private List<Good> getGoodsInCurrentOrder(List<OrderGoods> goodsInCurrentOrder) throws SQLException {
      List<Good> orderedGoods = new ArrayList<>();
      for (OrderGoods current : goodsInCurrentOrder) {
          orderedGoods.add(dao.getGoodDao().getGood(current.getGoodId()));
      }
      return orderedGoods;
  }

    /**
     * Create map with parameters for print check
     * @param basket the map contains goods
     * @param orderId orderId of current user
     * @return the map with parameters
     */
  public Map<String, String> createPresentationParams(Map<String, Integer> basket, long orderId) throws ServiceException {
      System.out.println(basket.size());
      Map<String, String> params = new HashMap<>();
      Formatter formatter = new Formatter();
      StringBuilder result = new StringBuilder();
      int i = 1;
      for (Map.Entry<String, Integer> pair : basket.entrySet()) {
           formatter = (formatter.format("<p>%d) %s x %d</p>\n", i, pair.getKey(), pair.getValue()));
          i += 1;
      }
      result.append(formatter.toString());

      double orderPrice = GoodsUtil.countTotalPrice(getGoods(orderId));
      String price = (String.format("%.1f", orderPrice));

      params.put(PRINT_GOODS, result.toString());
      params.put(PRICE, price);

      return params;
  }
}
