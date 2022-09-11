package com.windula.oms.service;

import com.windula.oms.dao.DeliveryDao;
import com.windula.oms.dao.OrderDao;
import com.windula.oms.dao.OrderItemDao;
import com.windula.oms.dao.PaymentDao;
import com.windula.oms.dto.OrderItemDTO;
import com.windula.oms.dto.OrderRequestDTO;
import com.windula.oms.exception.ApiException;
import com.windula.oms.exception.ExceptionEnum;
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
    public int addOrder(OrderRequestDTO orderRequestDTO) {

        try {
            int deliveryId = deliveryDao.addDelivery(orderRequestDTO.getDelivery());

            orderRequestDTO.getOrder().setDeliveryId(deliveryId);
            orderRequestDTO.getOrder().setOrderTimestamp(Timestamp.valueOf(LocalDateTime.now())); // time zone time stamp is the correct approach
            int orderId = orderDao.addOrder(orderRequestDTO.getOrder());


            for (OrderItemDTO orderItemDTO : orderRequestDTO.getOrderItems()) {
                orderItemDTO.setOrderId(orderId);
            }
            orderItemDao.addOrderItems(orderRequestDTO.getOrderItems());

            orderRequestDTO.getPayment().setOrderId(orderId);

            return paymentDao.addPayment(orderRequestDTO.getPayment());

        } catch (Exception e) {
            LOGGER.error("Order insertion failure ", e);
            throw new ApiException(ExceptionEnum.ORDER_INSERTION_FAILURE);
        }
    }
}
