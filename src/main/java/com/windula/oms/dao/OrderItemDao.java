package com.windula.oms.dao;

import com.windula.oms.dto.OrderItemDTO;

import java.util.List;

/**
 * The interface Order item dao.
 */
public interface OrderItemDao {

    /**
     * Add order items int.
     *
     * @param orderItemList the order item list
     * @return the int
     */
    int addOrderItems(List<OrderItemDTO> orderItemList);
}
