package com.windula.oms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDTO {

    @NotNull
    private LocalDate deliveryDate;
    @NotNull
    private int userAddressId;
    @NotNull
    private String deliveryStatus;
}
