package app.entity;

import java.util.Objects;

/**
 * Class represents a bin to describe a order-good connection
 */
public class OrderGoods {
    private long id;
    private long orderId;
    private long goodId;

    public OrderGoods(long id, long orderId, long goodId) {
        this.id = id;
        this.orderId = orderId;
        this.goodId = goodId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getGoodId() {
        return goodId;
    }

    public void setGoodId(long goodId) {
        this.goodId = goodId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderGoods orderGoods = (OrderGoods) o;
        return id == orderGoods.id &&
                orderId == orderGoods.orderId &&
                goodId == orderGoods.goodId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderId, goodId);
    }

    @Override
    public String toString() {
        return "OrderGood{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", goodId=" + goodId +
                '}';
    }
}
