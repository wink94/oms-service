package com.windula.oms.controller;

import com.windula.oms.dto.BaseResponse;
import com.windula.oms.dto.ProductResponse;
import com.windula.oms.mapper.ProductResponseMapper;
import com.windula.oms.model.Product;
import com.windula.oms.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private ProductResponseMapper productResponseMapper;

    @Test
    public void productShouldReturnValidResponseFromGetAllProducts() throws Exception {
        List<Product> productList = List.of();
        ProductResponse productResponse = new ProductResponse();
        productResponse.setProducts(productList);
        when(productService.getAllProducts()).thenReturn(productList);
        when(productResponseMapper.getAllProductResponse(productList)).thenReturn(productResponse);
        this.mockMvc.perform(get("/v1/oms/product")
                        .header("x-syy-correlation-id", "123456789")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.requestStatuses", is(Arrays.asList())))
                .andExpect(jsonPath("$.products").isArray())
                .andExpect(jsonPath("$.recordCount", is(0)));
    }

    @Test
    public void productShouldReturnValidResponseFromGetProductsByName() throws Exception {
        List<Product> productList = List.of();
        ProductResponse productResponse = new ProductResponse();
        productResponse.setProducts(productList);
        when(productService.getProductsByName(anyString())).thenReturn(productList);
        when(productResponseMapper.getAllProductResponse(productList)).thenReturn(productResponse);
        this.mockMvc.perform(get("/v1/oms/product").param("productName", "test")
                        .header("x-syy-correlation-id", "123456789")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.requestStatuses", is(Arrays.asList())))
                .andExpect(jsonPath("$.products").isArray())
                .andExpect(jsonPath("$.recordCount", is(0)));
    }

    @Test
    public void productShouldReturnValidResponseFromAddProduct() throws Exception {

        BaseResponse productResponse = new BaseResponse();
        productResponse.addRequestStatuses(200, "Success", "Successfully added 1 products");
        when(productService.addProducts(anyList())).thenReturn(anyInt());
        when(productResponseMapper.addAllProductResponse(1)).thenReturn(productResponse);
        this.mockMvc.perform(post("/v1/oms/product")
                        .content("{\n" +
                                "    \"productList\": [\n" +
                                "         {\n" +
                                "            \"productName\": \"abc\",\n" +
                                "            \"productType\": \"0017354\",\n" +
                                "            \"productMeasureUnit\": \"Kg\",\n" +
                                "            \"inStockQuantity\": 15,\n" +
                                "            \"productSoldPrice\": 15,\n" +
                                "            \"productBatchPrice\": 15\n" +
                                "         }\n" +
                                "    ]\n" +
                                "}")
                        .header("x-syy-correlation-id", "123456789")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.requestStatuses").isArray());
    }
}
