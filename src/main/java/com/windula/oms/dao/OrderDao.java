package com.windula.oms.dao;

import com.windula.oms.dto.DeliveryDTO;
import com.windula.oms.dto.OrderDTO;

public interface OrderDao {

    int addOrder(OrderDTO orderDTO);
}
