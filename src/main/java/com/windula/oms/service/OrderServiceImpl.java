package com.windula.oms.service;

import com.windula.oms.dao.*;
import com.windula.oms.dto.OrderItemDTO;
import com.windula.oms.dto.OrderRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private PaymentDao paymentDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderItemDao orderItemDao;

    @Autowired
    private DeliveryDao deliveryDao;

    @Override
    @Transactional("omsTransactionManager")
    public void addOrder(OrderRequestDTO orderRequestDTO) {

        try {
            int deliveryId = deliveryDao.addDelivery(orderRequestDTO.getDelivery());
            System.out.println("Delivery "+deliveryId);
            if (deliveryId > 0) {
                orderRequestDTO.getOrder().setDeliveryId(deliveryId);
                orderRequestDTO.getOrder().setOrderTimestamp(Timestamp.valueOf(LocalDateTime.now())); // time zone time stamp is the correct approach
                int orderId = orderDao.addOrder(orderRequestDTO.getOrder());
                System.out.println("orderId "+orderId);
                for (OrderItemDTO orderItemDTO : orderRequestDTO.getOrderItems()) {
                    orderItemDTO.setOrderId(orderId);
                }

                orderItemDao.addOrderItems(orderRequestDTO.getOrderItems());

                orderRequestDTO.getPayment().setOrderId(orderId);
                System.out.println("payment "+orderRequestDTO.getPayment().getPaymentAmount());
                int paymentId = paymentDao.addPayment(orderRequestDTO.getPayment());

                System.out.println("paymentId "+paymentId);

            }
        } catch (Exception e) {
            LOGGER.error("error", e);
        }
    }
}
