package com.windula.oms.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "product")
public class Product {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="product_id", unique=true, nullable=false, precision=10)
    private int productId;
    @Column(name="product_name", nullable=false, length=30)
    private String productName;
    @Column(name="product_type")
    private String productType;
    @Column(name="product_measure_unit")
    private String productMeasureUnit;
    @Column(name="in_stock_quantity")
    private Float inStockQuantity;
    @Column(name = "product_sold_price", precision = 10, scale = 4)
    private BigDecimal productSoldPrice;
    @Column(name = "product_batch_price", precision = 10, scale = 4)
    private BigDecimal productBatchPrice;
}
