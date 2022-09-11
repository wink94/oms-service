package com.windula.oms.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {

    @NotNull
    private String paymentStatus;

    private int orderId;
    @NotNull
    private int userId;
    @NotNull
    @Min(0)
    private BigDecimal paymentAmount;
}
