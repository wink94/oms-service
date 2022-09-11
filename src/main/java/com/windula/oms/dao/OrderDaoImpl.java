package com.windula.oms.dao;

import com.windula.oms.dto.OrderDTO;
import com.windula.oms.exception.DatabaseException;
import com.windula.oms.exception.ExceptionEnum;
import com.windula.oms.model.Order;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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
            Session session = entityManager.unwrap(Session.class);

            Order order = new Order(BigDecimal.TEN, Timestamp.valueOf(LocalDateTime.now()), orderDTO.getOrderStatus(), orderDTO.getUserId(), orderDTO.getDeliveryId(), orderDTO.getInvoiceId());
            System.out.println(order);
            session.save(order);
            return order.getOrderId();

        } catch (Exception e) {
            LOGGER.error("error order item insertion failed", e);
            throw new DatabaseException(ExceptionEnum.DATABASE_QUERY_FAILURE, e);
        }
    }
}
