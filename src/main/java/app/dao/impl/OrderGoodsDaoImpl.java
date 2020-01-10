package app.dao.impl;

import app.dao.OrderGoodsDao;
import app.entity.OrderGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@Lazy
public class OrderGoodsDaoImpl implements OrderGoodsDao {
    private static final String SELECT_ORDER_GOOD_BY_ORDER_ID_SQL_STATEMENT = "SELECT * FROM OrderGoods WHERE orderId LIKE ?";
    private static final String INSERT_ORDER_GOOD_SQL_STATEMENT = "INSERT INTO OrderGoods (orderId, goodId) VALUES (?,?)";

    private DataSource provider;

    @Autowired
    public void setProvider(DataSource provider) {
        this.provider = provider;
    }

    /**
     * Return list contains all {@link OrderGoods} of the transferred orderId
     * @return the list with all {@link OrderGoods} of this order
     * @throws SQLException an exception that provides information on a database access
     * error or other errors.
     */
    @Override
    public List<OrderGoods> getByOrderId(Long orderId) throws SQLException {
        List<OrderGoods> list = new ArrayList<>();
        try (PreparedStatement st = provider.getConnection().prepareStatement(SELECT_ORDER_GOOD_BY_ORDER_ID_SQL_STATEMENT)){
            st.setLong(1, orderId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new OrderGoods(rs.getLong("id"), rs.getLong("orderId"), rs.getLong("goodId")));////
            }
            rs.close();
        }
        return list;
    }

    /**
     * Add to OrderGoods table units with transferred goodId and orderId
     * @param orderId the order id
     * @param goodId the good id
     * @throws SQLException an exception that provides information on a database access
     *      * error or other errors.
     */
    @Override
    public void addToOrderGood(Long orderId, Long goodId) throws SQLException {
        try (PreparedStatement st = provider.getConnection().prepareStatement(INSERT_ORDER_GOOD_SQL_STATEMENT)) {
            st.setLong(1, orderId);
            st.setLong(2, goodId);
            st.executeUpdate();
        }
    }
}
