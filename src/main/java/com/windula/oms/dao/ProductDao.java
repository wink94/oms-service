package com.windula.oms.dao;

import com.windula.oms.dto.ProductDTO;
import com.windula.oms.model.Product;

import java.util.List;

/**
 * The interface Product dao.
 */
public interface ProductDao {

    /**
     * Add products int.
     *
     * @param products the products
     * @return the int
     */
    int addProducts(List<ProductDTO> products);


    /**
     * Add product.
     *
     * @param product the product
     */
    void addProduct(Product product);

    /**
     * Gets all products.
     *
     * @return the all products
     */
    List<Product> getAllProducts();

    /**
     * Gets products by name.
     *
     * @param productName the product name
     * @return the products by name
     */
    List<Product> getProductsByName(String productName);

    /**
     * Update product int.
     *
     * @param product the product
     * @return the int
     */
    int updateProduct(Product product);

    /**
     * Delete product int.
     *
     * @param productId the product id
     * @return the int
     */
    int deleteProduct(int productId);
}
