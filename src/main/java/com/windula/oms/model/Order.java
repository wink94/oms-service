package com.windula.oms.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="order_id", unique=true, nullable=false, precision=10)
    private int orderId;
    @Column(name = "order_total_price")
    private BigDecimal orderTotalPrice;
    @Column(name="order_timestamp")
    private Timestamp orderTimestamp;
    @Column(name="order_status")
    private String orderStatus;
    @Column(name="user_id")
    private int userId;
    @Column(name="delivery_id")
    private int deliveryId;
    @Column(name = "invoice_id")
    private String invoiceId;

    public Order(BigDecimal orderTotalPrice, Timestamp orderTimestamp, String orderStatus, int userId, int deliveryId, String invoiceId) {
        this.orderTotalPrice = orderTotalPrice;
        this.orderTimestamp = orderTimestamp;
        this.orderStatus = orderStatus;
        this.userId = userId;
        this.deliveryId = deliveryId;
        this.invoiceId = invoiceId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderTotalPrice=" + orderTotalPrice +
                ", orderTimestamp=" + orderTimestamp +
                ", orderStatus='" + orderStatus + '\'' +
                ", userId=" + userId +
                ", deliveryId=" + deliveryId +
                ", invoiceId='" + invoiceId + '\'' +
                '}';
    }
}
