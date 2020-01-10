package app.service.impl;

import app.dao.GoodDao;
import app.entity.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.util.List;

@Service
@Lazy
public class GoodsServiceImpl {
    private GoodDao goodDao;

    /**
     * @return list contains all Goods
     * @throws SQLException an exception that provides information on a database access
     * error or other errors.
     */
    public List<Good> getGoods() throws SQLException {
        return goodDao.getAllGoods();
    }

    @Autowired
    public void setGoodDao(GoodDao goodDao) {
        this.goodDao = goodDao;
    }
}
