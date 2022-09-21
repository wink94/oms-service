package com.windula.oms.controller;

import com.windula.oms.annotation.GenerateCorrelationId;
import com.windula.oms.dto.BaseResponse;
import com.windula.oms.dto.OrderRequestDTO;
import com.windula.oms.service.OrderService;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.windula.oms.common.Constants.CORRELATION_ID;
import static com.windula.oms.common.Constants.HEADER_KEY_CORRELATION_ID;

/**
 * The type Order controller.
 */
@RestController
@RequestMapping("/v1/oms")
public class OrderController {

    @Autowired
    private OrderService orderService;


    /**
     * Add order response entity.
     *
     * @param order         the order
     * @param correlationId the correlation id
     * @return the response entity
     */
    @GenerateCorrelationId
    @PostMapping(value = "/order", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> addOrder( @RequestBody OrderRequestDTO order, @RequestHeader(name = HEADER_KEY_CORRELATION_ID, required = false) String correlationId){
        orderService.addOrder(order);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HEADER_KEY_CORRELATION_ID, MDC.get(CORRELATION_ID));
        return new ResponseEntity<>(null, headers, HttpStatus.OK);
    }
}
