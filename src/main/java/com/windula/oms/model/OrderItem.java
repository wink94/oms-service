package com.windula.oms.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="order_item_id", unique=true, nullable=false, precision=10)
    private int orderItemId;
    @Column(name="quantity", nullable=false)
    private BigDecimal quantity;
    @Column(name="product_id")
    private int productId;
    @Column(name="order_id")
    private int orderId;
    @Column(name = "order_product_total_price", precision = 10, scale = 4)
    private BigDecimal orderProductTotalPrice;
    @Column(name = "item_status")
    private int status;

    public OrderItem(BigDecimal quantity, int productId, int orderId, BigDecimal orderProductTotalPrice, int status) {
        this.quantity = quantity;
        this.productId = productId;
        this.orderId = orderId;
        this.orderProductTotalPrice = orderProductTotalPrice;
        this.status = status;
    }
}
