package com.windula.oms.dao;

import com.windula.oms.dto.PaymentDTO;

public interface PaymentDao {

    int addPayment(PaymentDTO paymentDTO);
}
