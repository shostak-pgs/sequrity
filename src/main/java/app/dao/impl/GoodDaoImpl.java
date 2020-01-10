package app.dao.impl;

import app.dao.GoodDao;
import app.entity.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static app.constants.Constants.*;

@Repository
@Lazy
public class GoodDaoImpl implements GoodDao {
    private static final String SELECT_GOOD_SQL_STATEMENT = "SELECT * FROM Goods WHERE name LIKE ?";
    private static final String SELECT_GOOD_BY_ID_SQL_STATEMENT = "SELECT * FROM Goods WHERE id LIKE ?";
    private static final String SELECT_ALL_GOODS_SQL_STATEMENT = "SELECT * FROM Goods";

    private DataSource provider;

    @Autowired
    public void setProvider(DataSource provider) {
        this.provider = provider;
    }

    /**
     * Returns the good by the transferred name
     * @param name good's name
     * @return the {@link Good}
     * @throws SQLException an exception that provides information on a database access
     * error or other errors.
     */
    @Override
    public Good getGood(String name) throws SQLException {
        Good good = null;
        try (PreparedStatement st = provider.getConnection().prepareStatement(SELECT_GOOD_SQL_STATEMENT)) {
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                good = new Good(rs.getLong(ID), rs.getString(NAME), rs.getDouble(PRICE));
            }
            rs.close();
        }
        return good;
    }

    /**
     * Returns the good by the transferred id
     * @param goodId good's id
     * @return the {@link Good}
     * @throws SQLException an exception that provides information on a database access
     * error or other errors.
     */
    @Override
    public Good getGood(Long goodId) throws SQLException {
        Good good = null;
        try (PreparedStatement st = provider.getConnection().prepareStatement(SELECT_GOOD_BY_ID_SQL_STATEMENT)) {
            st.setLong(1, goodId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                good = new Good(rs.getLong(ID), rs.getString(NAME), rs.getDouble(PRICE));
            }
            rs.close();
        }
        return good;
    }

    /**
     * Return list contains all Goods in base
     * @return the list
     * @throws SQLException an exception that provides information on a database access
     * error or other errors.
     */
    @Override
    public List<Good> getAllGoods() throws SQLException {
        List<Good> goodsList = new ArrayList<>();
        try (PreparedStatement st = provider.getConnection().prepareStatement(SELECT_ALL_GOODS_SQL_STATEMENT);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                goodsList.add(new Good(rs.getLong(ID), rs.getString(NAME), rs.getDouble(PRICE)));
            }
        }
        return goodsList;
    }
}
