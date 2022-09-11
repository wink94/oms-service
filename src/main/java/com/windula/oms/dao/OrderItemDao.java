package com.windula.oms.dao;

import com.windula.oms.dto.OrderItemDTO;

import java.util.List;

public interface OrderItemDao {

    int addOrderItems(List<OrderItemDTO> orderItemList);
}
