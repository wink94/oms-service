package com.windula.oms.dao;

import com.windula.oms.dto.PaymentDTO;

/**
 * The interface Payment dao.
 */
public interface PaymentDao {

    /**
     * Add payment int.
     *
     * @param paymentDTO the payment dto
     * @return the int
     */
    int addPayment(PaymentDTO paymentDTO);
}
