package com.windula.oms.service;

import com.windula.oms.dao.PaymentDao;
import com.windula.oms.dao.ProductDao;
import com.windula.oms.dto.PaymentDTO;
import com.windula.oms.dto.ProductDTO;
import com.windula.oms.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;



    @Override
    public int addPayment(PaymentDTO paymentDTO) {
        return paymentDao.addPayment(paymentDTO);
    }
}
