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
@Entity(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="payment_id", unique=true, nullable=false, precision=10)
    private int paymentId;
    @Column(name="payment_date", nullable=false, length=30)
    private Timestamp paymentDate;
    @Column(name="payment_status")
    private String paymentStatus;
    @Column(name="order_id")
    private int orderId;
    @Column(name="user_id")
    private int userId;
    @Column(name = "payment_amount", precision = 10, scale = 4)
    private BigDecimal paymentAmount;

    public Payment(Timestamp paymentDate, String paymentStatus, int orderId, int userId, BigDecimal paymentAmount) {
        this.paymentDate = paymentDate;
        this.paymentStatus = paymentStatus;
        this.orderId = orderId;
        this.userId = userId;
        this.paymentAmount = paymentAmount;
    }
}
