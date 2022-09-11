package com.windula.oms.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "delivery")
public class Delivery {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="delivery_id", unique=true, nullable=false, precision=10)
    private int deliveryId;
    @Column(name="delivery_date", nullable=false, length=30)
    private LocalDate deliveryDate;
    @Column(name="user_address_id",nullable=false)
    private int userAddressId;
    @Column(name="delivery_status", nullable=false)
    private String deliveryStatus;

    public Delivery(LocalDate deliveryDate, int userAddressId, String deliveryStatus) {
        this.deliveryDate = deliveryDate;
        this.userAddressId = userAddressId;
        this.deliveryStatus = deliveryStatus;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "deliveryId=" + deliveryId +
                ", deliveryDate=" + deliveryDate +
                ", userAddressId=" + userAddressId +
                ", deliveryStatus='" + deliveryStatus + '\'' +
                '}';
    }
}
