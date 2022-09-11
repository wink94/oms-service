package com.windula.oms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    @NotNull
    @Min(0)
    private BigDecimal orderTotalPrice;

    private Timestamp orderTimestamp;
    @NotNull
    private String orderStatus;
    @NotNull
    private int userId;
    @NotNull
    private int deliveryId;
    @NotNull
    private String invoiceId;
}
