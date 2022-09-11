package com.windula.oms.mapper;

import com.windula.oms.dto.BaseResponse;
import com.windula.oms.model.Product;
import com.windula.oms.dto.ProductResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductResponseMapper {

    public ProductResponse getAllProductResponse(List<Product> products){
        ProductResponse productResponse = new ProductResponse();
        productResponse.setProducts(products);
        return productResponse;
    }

    public BaseResponse addAllProductResponse(int recordCount){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.addRequestStatuses(200,"Success","Successfully added "+recordCount+" products");
        return baseResponse;
    }

}
