package com.windula.oms.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
@AllArgsConstructor
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

    @Column(name = "active_status")
    private int activeStatus;

    public Product(String productName, String productType, String productMeasureUnit, Float inStockQuantity, BigDecimal productSoldPrice, BigDecimal productBatchPrice) {
        this.productName = productName;
        this.productType = productType;
        this.productMeasureUnit = productMeasureUnit;
        this.inStockQuantity = inStockQuantity;
        this.productSoldPrice = productSoldPrice;
        this.productBatchPrice = productBatchPrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productType='" + productType + '\'' +
                ", productMeasureUnit='" + productMeasureUnit + '\'' +
                ", inStockQuantity=" + inStockQuantity +
                ", productSoldPrice=" + productSoldPrice +
                ", productBatchPrice=" + productBatchPrice +
                '}';
    }
}
