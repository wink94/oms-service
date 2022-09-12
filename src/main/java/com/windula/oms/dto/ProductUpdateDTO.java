package com.windula.oms.dto;

import java.math.BigDecimal;

public record ProductUpdateDTO(int productId,
                               String productName, String productType, String productMeasureUnit,
                               Float inStockQuantity, BigDecimal productSoldPrice, BigDecimal productBatchPrice,
                               int activeStatus) {
}
