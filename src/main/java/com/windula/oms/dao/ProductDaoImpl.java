package com.windula.oms.dao;

import com.windula.oms.dto.ProductDTO;
import com.windula.oms.exception.DatabaseException;
import com.windula.oms.exception.ExceptionEnum;
import com.windula.oms.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductDaoImpl.class);

    @Autowired
    @Qualifier("omsEntityManager")
    private EntityManager entityManager;

    @Override
    @Transactional("omsTransactionManager")
    public int addProducts(List<ProductDTO> products) {
        int recordCount = 0;
        try {
            for (ProductDTO product : products) {
                Query query = entityManager.createNativeQuery("INSERT INTO oms.product" +
                        "(" +
                        "product_name," +
                        "product_type," +
                        "in_stock_quantity," +
                        "product_measure_unit," +
                        "product_sold_price," +
                        "product_batch_price)" +
                        "VALUES" +
                        "(" +
                        ":productName," +
                        ":productType," +
                        ":inStockQuantity," +
                        ":productMeasureUnit," +
                        ":productSoldPrice," +
                        ":productBatchPrice) ON DUPLICATE KEY UPDATE product_name=VALUES(product_name),product_type=VALUES(product_type),in_stock_quantity=VALUES(in_stock_quantity)" +
                        ",product_measure_unit=VALUES(product_measure_unit),product_sold_price=VALUES(product_sold_price),product_batch_price=VALUES(product_batch_price)");

                query.setParameter("productName", product.getProductName());
                query.setParameter("productType", product.getProductType());
                query.setParameter("productMeasureUnit", product.getProductMeasureUnit());
                query.setParameter("inStockQuantity", product.getInStockQuantity());
                query.setParameter("productSoldPrice", product.getProductSoldPrice());
                query.setParameter("productBatchPrice", product.getProductBatchPrice());
                recordCount+=query.executeUpdate();
            }
            return recordCount;
        } catch (Exception e) {
            LOGGER.error("error product insertion failed", e);
            throw new DatabaseException(ExceptionEnum.DATABASE_QUERY_FAILURE, e);
        }
    }

    @Override
    public void addProduct(Product product) {
        try {

            Query query = entityManager.createNativeQuery("INSERT INTO oms.product" +
                    "(" +
                    "product_name," +
                    "product_type," +
                    "in_stock_quantity," +
                    "product_measure_unit," +
                    "product_sold_price," +
                    "product_batch_price)" +
                    "VALUES" +
                    "(" +
                    ":productName," +
                    ":productType," +
                    ":productMeasureUnit," +
                    ":inStockQuantity," +
                    ":productSoldPrice," +
                    ":productBatchPrice) ON DUPLICATE KEY UPDATE product_name=VALUES(product_name),product_type=VALUES(product_type),in_stock_quantity=VALUES(in_stock_quantity)" +
                    ",product_measure_unit=VALUES(product_measure_unit),product_sold_price=VALUES(product_sold_price),product_batch_price=VALUES(product_batch_price)");

            query.setParameter("productId", product.getProductId());
            query.setParameter("productName", product.getProductName());
            query.setParameter("productType", product.getProductType());
            query.setParameter("productMeasureUnit", product.getProductMeasureUnit());
            query.setParameter("inStockQuantity", product.getInStockQuantity());
            query.setParameter("productSoldPrice", product.getProductSoldPrice());
            query.setParameter("productBatchPrice", product.getProductBatchPrice());
            query.executeUpdate();

        } catch (Exception e) {
            LOGGER.error("error product insertion failed", e);
            throw new DatabaseException(ExceptionEnum.DATABASE_QUERY_FAILURE, e);
        }
    }

    @Override
    public List<Product> getAllProducts() {
        try {
            final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            final CriteriaQuery<Product> query = cb.createQuery(Product.class);
            final Root<Product> productRoot = query.from(Product.class);

            query.select(productRoot);
            return entityManager.createQuery(query).getResultList();
        } catch (Exception e) {
            LOGGER.error("error product fetch failed", e);
            throw new DatabaseException(ExceptionEnum.DATABASE_QUERY_FAILURE, e);
        }
    }

    @Override
    public List<Product> getProductsByName(String productName) {
        try {
            final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            final CriteriaQuery<Product> query = cb.createQuery(Product.class);
            final Root<Product> productRoot = query.from(Product.class);

            Predicate predicate1 = cb.equal(productRoot.get("productName"), productName);

            query.select(productRoot).where(predicate1);
            return entityManager.createQuery(query).getResultList();
        } catch (Exception e) {
            LOGGER.error("error product fetch by product name failed", e);
            throw new DatabaseException(ExceptionEnum.DATABASE_QUERY_FAILURE, e);
        }
    }

    @Override
    @Transactional("omsTransactionManager")
    public int updateProduct(Product product) {

        try {
            return entityManager.merge(product).getProductId();
        } catch (Exception e) {
            LOGGER.error("error product update failed", e);
            throw new DatabaseException(ExceptionEnum.DATABASE_QUERY_FAILURE, e);
        }
    }

    @Override
    @Transactional("omsTransactionManager")
    public int deleteProduct(int productId) {
        try {

            final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            final CriteriaUpdate<Product> query = cb.createCriteriaUpdate(Product.class);
            final Root<Product> productRoot = query.from(Product.class);

            Predicate predicate1 = cb.equal(productRoot.get("productId"), productId);

            query.set(productRoot.get("activeStatus"),0).where(predicate1);
            return entityManager.createQuery(query).executeUpdate();

        } catch (Exception e) {
            LOGGER.error("error product insertion failed", e);
            throw new DatabaseException(ExceptionEnum.DATABASE_QUERY_FAILURE, e);
        }
    }


}
