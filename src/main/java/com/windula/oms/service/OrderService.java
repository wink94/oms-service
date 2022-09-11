package com.windula.oms.service;

import com.windula.oms.dto.OrderRequestDTO;

public interface OrderService {

    int addOrder(OrderRequestDTO orderRequestDTO);
}
