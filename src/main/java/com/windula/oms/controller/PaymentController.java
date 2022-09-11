package com.windula.oms.controller;

import com.windula.oms.annotation.GenerateCorrelationId;
import com.windula.oms.dto.BaseResponse;
import com.windula.oms.dto.PaymentDTO;
import com.windula.oms.dto.ProductRequest;
import com.windula.oms.mapper.PaymentResponseMapper;
import com.windula.oms.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.windula.oms.common.Constants.HEADER_KEY_CORRELATION_ID;

@RestController
@RequestMapping("/v1/oms")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PaymentResponseMapper paymentResponseMapper;

    @GenerateCorrelationId
    @PostMapping(value = "/payment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> addPayment(@Valid @RequestBody PaymentDTO paymentDTO, @RequestHeader(name = HEADER_KEY_CORRELATION_ID, required = false) String correlationId){
        BaseResponse paymentResponse = paymentResponseMapper.addPaymentResponse(paymentService.addPayment(paymentDTO));
        HttpStatus responseStatus = HttpStatus.OK;
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(paymentResponse, headers, responseStatus);
    }
}
