package com.windula.oms.dao;

import com.windula.oms.dto.PaymentDTO;
import com.windula.oms.exception.DatabaseException;
import com.windula.oms.exception.ExceptionEnum;
import com.windula.oms.model.Payment;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * The type Payment dao.
 */
@Repository
public class PaymentDaoImpl implements PaymentDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentDaoImpl.class);

    @Autowired
    @Qualifier("omsEntityManager")
    private EntityManager entityManager;

    @Override
    @Transactional("omsTransactionManager")
    public int addPayment(PaymentDTO paymentDTO) {
        try {
            Payment payment = new Payment(Timestamp.valueOf(LocalDateTime.now()),paymentDTO.getPaymentStatus(),paymentDTO.getOrderId(),paymentDTO.getUserId(),BigDecimal.ONE);
            entityManager.persist(payment);
            return payment.getPaymentId();

        } catch (Exception e) {
            LOGGER.error("error payment insertion failed", e);
            throw new DatabaseException(ExceptionEnum.DATABASE_QUERY_FAILURE, e);
        }
    }
}
