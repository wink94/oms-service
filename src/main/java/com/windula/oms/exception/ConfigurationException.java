package com.windula.oms.exception;

public class ConfigurationException extends OMSException {

    public ConfigurationException(ExceptionEnum exceptionEnum){
        super(exceptionEnum);
    }

    public ConfigurationException(ExceptionEnum exceptionEnum, Throwable cause){
        super(exceptionEnum, cause);
    }
}