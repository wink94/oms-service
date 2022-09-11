package com.windula.oms.service;

import com.windula.oms.dto.ProductDTO;
import com.windula.oms.model.Product;

import java.util.List;

public interface ProductService {

    int addProducts(List<ProductDTO> products);


    void addProduct(Product product);

    List<Product> getAllProducts();

    List<Product> getProductsByName(String productName);

    int updateProduct(Product product);

    int deleteProduct(int productId);

}
