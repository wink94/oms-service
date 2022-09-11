package com.windula.oms.controller;


import com.windula.oms.annotation.GenerateCorrelationId;
import com.windula.oms.dto.BaseResponse;
import com.windula.oms.dto.ProductRequest;
import com.windula.oms.mapper.ProductResponseMapper;
import com.windula.oms.dto.ProductResponse;
import com.windula.oms.model.Product;
import com.windula.oms.service.ProductService;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.windula.oms.common.Constants.CORRELATION_ID;
import static com.windula.oms.common.Constants.HEADER_KEY_CORRELATION_ID;

@RestController
@RequestMapping("/v1/oms")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductResponseMapper productResponseMapper;

    @GenerateCorrelationId
    @GetMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResponse> getAllProducts(){
        ProductResponse productResponse = productResponseMapper.getAllProductResponse(productService.getAllProducts());
        HttpStatus responseStatus = HttpStatus.OK;
        HttpHeaders headers = new HttpHeaders();
        headers.add(HEADER_KEY_CORRELATION_ID, MDC.get(CORRELATION_ID));
        return new ResponseEntity<>(productResponse, headers, responseStatus);
    }

    @GenerateCorrelationId
    @GetMapping(value = "/product/{productName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResponse> getProductsByName(@PathVariable String productName){
        ProductResponse productResponse = productResponseMapper.getAllProductResponse(productService.getProductsByName(productName));
        HttpStatus responseStatus = HttpStatus.OK;
        HttpHeaders headers = new HttpHeaders();
        headers.add(HEADER_KEY_CORRELATION_ID, MDC.get(CORRELATION_ID));
        return new ResponseEntity<>(productResponse, headers, responseStatus);
    }

    @GenerateCorrelationId
    @PostMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> addProducts(@Valid @RequestBody ProductRequest products, @RequestHeader(name = HEADER_KEY_CORRELATION_ID, required = false) String correlationId){
        BaseResponse productResponse = productResponseMapper.addAllProductResponse(productService.addProducts(products.getProductList()));
        HttpStatus responseStatus = HttpStatus.OK;
        HttpHeaders headers = new HttpHeaders();
        headers.add(HEADER_KEY_CORRELATION_ID, MDC.get(CORRELATION_ID));
        return new ResponseEntity<>(productResponse, headers, responseStatus);
    }

    @GenerateCorrelationId
    @PutMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> updateProducts(@Valid @RequestBody Product product, @RequestHeader(name = HEADER_KEY_CORRELATION_ID, required = false) String correlationId){
        BaseResponse productResponse = productResponseMapper.updateProductResponse(productService.updateProduct(product));
        HttpStatus responseStatus = HttpStatus.OK;
        HttpHeaders headers = new HttpHeaders();
        headers.add(HEADER_KEY_CORRELATION_ID, MDC.get(CORRELATION_ID));
        return new ResponseEntity<>(productResponse, headers, responseStatus);
    }

    @GenerateCorrelationId
    @DeleteMapping(value = "/product/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> deleteProducts( @PathVariable int productId, @RequestHeader(name = HEADER_KEY_CORRELATION_ID, required = false) String correlationId){
        BaseResponse productResponse = productResponseMapper.deleteProductResponse(productService.deleteProduct(productId)>0?productId:0);
        HttpStatus responseStatus = HttpStatus.OK;
        HttpHeaders headers = new HttpHeaders();
        headers.add(HEADER_KEY_CORRELATION_ID, MDC.get(CORRELATION_ID));
        return new ResponseEntity<>(productResponse, headers, responseStatus);
    }

}
