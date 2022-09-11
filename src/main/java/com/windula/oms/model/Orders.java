package com.windula.oms.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="order_id", unique=true, nullable=false)
    private int orderId;
    @Column(name = "order_total_price", nullable=false)
    private BigDecimal orderTotalPrice;
    @Column(name="order_timestamp", nullable=false)
    private Timestamp orderTimestamp;
    @Column(name="order_status", nullable=false)
    private String orderStatus;
    @Column(name="user_id", nullable=false)
    private int userId;
    @Column(name="delivery_id", nullable=false)
    private int deliveryId;
    @Column(name = "invoice_id", nullable=false)
    private String invoiceId;

    public Orders(BigDecimal orderTotalPrice, Timestamp orderTimestamp, String orderStatus, int userId, int deliveryId, String invoiceId) {
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
