package com.windula.oms.dao;

import com.windula.oms.dto.OrderItemDTO;
import com.windula.oms.dto.ProductDTO;
import com.windula.oms.exception.DatabaseException;
import com.windula.oms.exception.ExceptionEnum;
import com.windula.oms.model.OrderItem;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
@Repository
public class OrderItemDaoImpl implements OrderItemDao{

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderItemDaoImpl.class);
    @Autowired
    @Qualifier("omsEntityManager")
    private EntityManager entityManager;

    @Override
    @Transactional("omsTransactionManager")
    public int addOrderItems(List<OrderItemDTO> orderItemList){
        int recordCount = 0;
        try {
            Session session = entityManager.unwrap(Session.class);
            for (OrderItemDTO orderItemDTO : orderItemList) {
                OrderItem orderItem = new OrderItem( orderItemDTO.getQuantity(),orderItemDTO.getProductId(),orderItemDTO.getOrderId(),orderItemDTO.getOrderProductTotalPrice(),1);
                session.save(orderItem);
                recordCount+=1;
            }
            return recordCount;
        } catch (Exception e) {
            LOGGER.error("error order item insertion failed", e);
            throw new DatabaseException(ExceptionEnum.DATABASE_QUERY_FAILURE, e);
        }
    }
}
