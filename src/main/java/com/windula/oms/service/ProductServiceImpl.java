package com.windula.oms.service;

import com.windula.oms.dao.ProductDao;
import com.windula.oms.dto.ProductDTO;
import com.windula.oms.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public int addProducts(List<ProductDTO> products) {
        return productDao.addProducts(products);
    }

    @Override
    public void addProduct(Product product) {
        productDao.addProduct(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    @Override
    public List<Product> getProductsByName(String productName) {
        return productDao.getProductsByName(productName);
    }

    @Override
    public void updateProduct(Product product) {
        productDao.updateProduct(product);
    }
}
