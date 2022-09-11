package com.windula.oms.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {

    @Size(min = 1, message = "one or more order items are required")
    List< OrderItemDTO> orderItems;

     PaymentDTO payment;

     DeliveryDTO delivery;

     OrderDTO order;


}
