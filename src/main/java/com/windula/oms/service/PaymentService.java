package com.windula.oms.service;

import com.windula.oms.dto.PaymentDTO;
import com.windula.oms.dto.ProductDTO;
import com.windula.oms.model.Product;

import java.util.List;

/**
 * The interface Payment service.
 */
public interface PaymentService {

    /**
     * Add payment int.
     *
     * @param paymentDTO the payment dto
     * @return the int
     */
    int addPayment(PaymentDTO paymentDTO);


}
