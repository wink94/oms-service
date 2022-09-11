package com.windula.oms.exception;

public class OMSException extends RuntimeException{

    private final ExceptionEnum exceptionEnum;

    public ExceptionEnum getExceptionEnum() {
        return exceptionEnum;
    }

    OMSException(ExceptionEnum exceptionEnum, Throwable cause) {

        super(exceptionEnum.getErrorMessage(),cause);

        this.exceptionEnum = exceptionEnum;

    }

    OMSException(ExceptionEnum exceptionEnum) {

        super(exceptionEnum.getErrorMessage());

        this.exceptionEnum = exceptionEnum;

    }
}
