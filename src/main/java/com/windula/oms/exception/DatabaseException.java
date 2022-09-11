package com.windula.oms.exception;

public class DatabaseException extends OMSException{

    public DatabaseException(ExceptionEnum exceptionEnum, Throwable cause) {
        super(exceptionEnum, cause);
    }
}
