package com.windula.oms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    @NotNull
    @NotBlank
    private String productName;
    @NotNull
    @NotBlank
    private String productType;

    private String productMeasureUnit;
    @Min(0)
    @NotNull
    private Float inStockQuantity;
    @Min(0)
    @NotNull
    private BigDecimal productSoldPrice;
    @Min(0)
    @NotNull
    private BigDecimal productBatchPrice;
}
