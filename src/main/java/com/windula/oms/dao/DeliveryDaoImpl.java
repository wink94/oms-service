package com.windula.oms.dao;

import com.windula.oms.dto.DeliveryDTO;
import com.windula.oms.exception.DatabaseException;
import com.windula.oms.exception.ExceptionEnum;
import com.windula.oms.model.Delivery;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

/**
 * The type Delivery dao.
 */
@Repository
public class DeliveryDaoImpl implements DeliveryDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeliveryDaoImpl.class);
    @Autowired
    @Qualifier("omsEntityManager")
    private EntityManager entityManager;


    @Override
    @Transactional("omsTransactionManager")
    public int addDelivery(DeliveryDTO deliveryDTO) {
        try {

            Delivery delivery = new Delivery(deliveryDTO.getDeliveryDate(), deliveryDTO.getUserAddressId(), deliveryDTO.getDeliveryStatus());

            entityManager.persist(delivery);
            return delivery.getDeliveryId();

        } catch (Exception e) {
            LOGGER.error("error delivery insertion failed", e);
            throw new DatabaseException(ExceptionEnum.DATABASE_QUERY_FAILURE, e);
        }
    }
}
