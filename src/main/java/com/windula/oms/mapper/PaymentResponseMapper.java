package com.windula.oms.mapper;

import com.windula.oms.dto.BaseResponse;
import com.windula.oms.dto.ProductResponse;
import com.windula.oms.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaymentResponseMapper {



    public BaseResponse addPaymentResponse(int recordCount){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.addRequestStatuses(200,"Success","Successfully completed payment.");
        return baseResponse;
    }

}
