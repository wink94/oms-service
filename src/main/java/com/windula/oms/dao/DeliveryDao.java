package com.windula.oms.dao;

import com.windula.oms.dto.DeliveryDTO;

/**
 * The interface Delivery dao.
 */
public interface DeliveryDao {

    /**
     * Add delivery int.
     *
     * @param deliveryDTO the delivery dto
     * @return the int
     */
    int addDelivery(DeliveryDTO deliveryDTO);
}
