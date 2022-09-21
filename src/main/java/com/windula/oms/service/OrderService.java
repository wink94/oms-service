package com.windula.oms.service;

import com.windula.oms.dto.OrderRequestDTO;

/**
 * The interface Order service.
 */
public interface OrderService {

    /**
     * Add order int.
     *
     * @param orderRequestDTO the order request dto
     * @return the int
     */
    int addOrder(OrderRequestDTO orderRequestDTO);
}
