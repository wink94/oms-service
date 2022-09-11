package com.windula.oms.exception;


public class ApiException extends RuntimeException {
    private final ExceptionEnum errorCodeEnum;

    /**
     * Constructor
     */
    public ApiException(ExceptionEnum errorCodeEnum) {
        this.errorCodeEnum = errorCodeEnum;
    }

    public ExceptionEnum getErrorCodeEnum() {
        return errorCodeEnum;
    }
}
