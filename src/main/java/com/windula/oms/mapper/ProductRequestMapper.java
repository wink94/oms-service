package com.windula.oms.mapper;

import com.windula.oms.dto.BaseResponse;
import com.windula.oms.dto.ProductDTO;
import com.windula.oms.dto.ProductResponse;
import com.windula.oms.dto.ProductUpdateDTO;
import com.windula.oms.model.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductRequestMapper {

    public Product convertToProductEntity(ProductUpdateDTO product) {
        return new Product(product.productId(), product.productName(), product.productType(), product.productMeasureUnit(),
                product.inStockQuantity(), product.productSoldPrice(), product.productBatchPrice(), product.activeStatus());
    }
}
