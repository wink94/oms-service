package com.windula.oms.service;

import com.windula.oms.dao.ProductDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductServiceTest {

    @Mock
    private ProductDao productDao;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeAll
    public void setUp() {
        productService = new ProductServiceImpl();
    }

    @Test
    public void getAllProductsWhenNoErrors() {
        try {

            productService.getAllProducts();
        } catch (Exception e) {
            Assertions.fail();
        }
    }


}
