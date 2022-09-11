package com.windula.oms.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {


    @NotNull
    private BigDecimal quantity;
    @NotNull
    private int productId;
    @NotNull
    private int orderId;
    @NotNull
    private BigDecimal orderProductTotalPrice;
}
