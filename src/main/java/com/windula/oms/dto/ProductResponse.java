package com.windula.oms.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.windula.oms.model.Product;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@JsonPropertyOrder({"products,recordCount"})
public class ProductResponse extends BaseResponse {

    List<Product> products=new ArrayList<>();

    int recordCount=0;

    public ProductResponse() {
        super();
    }

    public void setProducts(List<Product> products) {
        this.products = products;
        this.recordCount = products.size();
    }

    public void addRequestStatuses(int code, String state, String message) {
        this.requestStatuses.add(new StatusResponse(code, state, message));
    }
}
