package com.windula.oms.dao;

import com.windula.oms.dto.OrderDTO;
import com.windula.oms.exception.DatabaseException;
import com.windula.oms.exception.ExceptionEnum;
import com.windula.oms.model.Orders;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * The type Order dao.
 */
@Repository
public class OrderDaoImpl implements OrderDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderDaoImpl.class);


    @Autowired
    @Qualifier("omsEntityManager")
    private EntityManager entityManager;


    @Override
    @Transactional("omsTransactionManager")
    public int addOrder(OrderDTO orderDTO) {

        try {
            Orders orders = new Orders(orderDTO.getOrderTotalPrice(),
                    Timestamp.valueOf(LocalDateTime.now()), orderDTO.getOrderStatus(),orderDTO.getUserId(),orderDTO.getDeliveryId(),orderDTO.getInvoiceId());
            entityManager.persist(orders);
            return orders.getOrderId();

        } catch (Exception e) {
            LOGGER.error("error order item insertion failed", e);
            throw new DatabaseException(ExceptionEnum.DATABASE_QUERY_FAILURE, e);
        }
    }
}
