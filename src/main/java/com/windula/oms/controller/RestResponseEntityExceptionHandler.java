package com.windula.oms.controller;

import com.windula.oms.common.Constants;
import com.windula.oms.exception.ApiException;
import com.windula.oms.exception.DatabaseException;
import com.windula.oms.exception.ExceptionEnum;
import com.windula.oms.dto.StatusResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.apache.commons.lang3.exception.ExceptionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

import static com.windula.oms.common.Constants.CORRELATION_ID;
import static com.windula.oms.exception.ExceptionEnum.BAD_REQUEST_EXCEPTION;
import static com.windula.oms.exception.ExceptionEnum.UNEXPECTED_EXCEPTION;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

    private static final String LOG_FORMAT_TWO_PLACEHOLDERS = " {} {}";

    private static StatusResponse getRequestErrorResponse(ExceptionEnum errorCodeEnum) {
        return new StatusResponse(errorCodeEnum.getErrorCode(), errorCodeEnum.getErrorState(), errorCodeEnum.getErrorMessage());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorMessage = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        return handleBadRequestErrors(errorMessage);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException constraintViolationException) {
        Set<ConstraintViolation<?>> violations = constraintViolationException.getConstraintViolations();
        String errorMessage = "";
        if (!violations.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            violations.forEach(violation -> builder.append(" ").append(violation.getMessage()));
            errorMessage = builder.toString();
        } else {
            errorMessage = "ConstraintViolationException occurred.";
        }
        return handleBadRequestErrors(errorMessage);
    }

    @ExceptionHandler({Exception.class, ApiException.class, DatabaseException.class})
    public ResponseEntity<Object> genericErrorHandler(Exception ex) {
        LOG.error(LOG_FORMAT_TWO_PLACEHOLDERS, ExceptionUtils.getMessage(ex), ExceptionUtils.getStackTrace(ex));
        return handleInternalServerErrors();
    }

    private ResponseEntity<Object> handleBadRequestErrors(String errorMessages) {
        HttpHeaders headers = new HttpHeaders();
        String correlationId = MDC.get(CORRELATION_ID);
        headers.set(Constants.HEADER_KEY_CORRELATION_ID, correlationId);
        StatusResponse response = getRequestErrorResponse(BAD_REQUEST_EXCEPTION);
        response.setInfo(errorMessages);
        return new ResponseEntity<>(response, headers,  HttpStatus.BAD_REQUEST);
    }
    private ResponseEntity<Object> handleInternalServerErrors() {
        HttpStatus responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        HttpHeaders headers = new HttpHeaders();
        String correlationId = MDC.get(CORRELATION_ID);
        headers.set(Constants.HEADER_KEY_CORRELATION_ID, correlationId);
        StatusResponse response = getRequestErrorResponse(UNEXPECTED_EXCEPTION);
        return new ResponseEntity<>(response, headers, responseStatus);
    }

}
