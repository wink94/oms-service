package com.windula.oms.dao;

import com.windula.oms.dto.DeliveryDTO;
import com.windula.oms.dto.OrderDTO;

/**
 * The interface Order dao.
 */
public interface OrderDao {

    /**
     * Add order int.
     *
     * @param orderDTO the order dto
     * @return the int
     */
    int addOrder(OrderDTO orderDTO);
}
