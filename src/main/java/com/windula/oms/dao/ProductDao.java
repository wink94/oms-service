package com.windula.oms.dao;

import com.windula.oms.dto.ProductDTO;
import com.windula.oms.model.Product;

import java.util.List;

public interface ProductDao {

    int addProducts(List<ProductDTO> products);


    void addProduct(Product product);

    List<Product> getAllProducts();

    List<Product> getProductsByName(String productName);

    void updateProduct(Product product);
}
