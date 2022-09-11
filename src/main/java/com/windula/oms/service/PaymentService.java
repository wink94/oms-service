package com.windula.oms.service;

import com.windula.oms.dto.PaymentDTO;
import com.windula.oms.dto.ProductDTO;
import com.windula.oms.model.Product;

import java.util.List;

public interface PaymentService {

    int addPayment(PaymentDTO paymentDTO);


}
