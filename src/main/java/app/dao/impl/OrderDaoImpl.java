package app.dao.impl;

import app.dao.OrderDao;
import app.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static app.constants.Constants.*;

@Repository
@Lazy
public class OrderDaoImpl implements OrderDao {
    private static final String SELECT_ORDER_BY_USER_SQL_STATEMENT = "SELECT * FROM Orders WHERE userId = ?";
    private static final String INSERT_ORDER_SQL_STATEMENT = "INSERT INTO Orders (userId) VALUES (?)";
    private static final String UPDATE_ORDER_SQL_STATEMENT = "UPDATE Orders SET totalPrice = ? WHERE userId = ?";

    private DataSource provider;

    @Autowired
    public void setProvider(DataSource provider) {
        this.provider = provider;
    }

    /**
     * Returns the order by the transferred user's id
     * @param id user's id
     * @return the {@link Order}
     * @throws SQLException An exception that provides information on a database access
     * error or other errors.
     */
    @Override
    public Order getOrderByUserId(Long id) throws SQLException {
        Order order = null;
        try (PreparedStatement st = provider.getConnection().prepareStatement(SELECT_ORDER_BY_USER_SQL_STATEMENT)) {
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                order = new Order(rs.getLong(ID), rs.getLong(USER_ID), rs.getDouble(TOTAL_PRICE));
            }
            rs.close();
        }
        return order;
    }

    /**
     * Add to Order table by the transferred user's id
     * @param userId id user's id
     * @throws SQLException An exception that provides information on a database access
     * error or other errors.
     */
    @Override
    public void addToOrder(Long userId) throws SQLException {
        try (PreparedStatement st = provider.getConnection().prepareStatement(INSERT_ORDER_SQL_STATEMENT)) {
            st.setLong(1, userId);
            st.executeUpdate();
        }
    }

    /**
     * Update user's Order with transferred price
     * @param totalPrice price to be assigned to the order
     * @param userId id of user to update
     */
    @Override
    public boolean updateOrderById(double totalPrice, long userId) throws SQLException {
        boolean isUpdated;
        try (PreparedStatement st = provider.getConnection().prepareStatement(UPDATE_ORDER_SQL_STATEMENT)) {
            st.setDouble(1, totalPrice);
            st.setLong(2, userId);
            st.executeUpdate();
            isUpdated = true;
        }
            return isUpdated;
    }
}
