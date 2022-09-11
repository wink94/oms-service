package com.windula.oms.exception;

public enum ExceptionEnum {


    DATABASE_PROPERTIES_FETCH(101,"Database properties fetch failed"),
    DATABASE_CONNECTION_FAILURE(102, "Database connection failed"),

    INVALID_JSON(199, "Invalid JSON",null),
    UNEXPECTED_EXCEPTION(  198,"Internal Server Error","Error"),

    BAD_REQUEST_EXCEPTION(  199,"Bad Request","Error"),

    DATABASE_QUERY_FAILURE(103, "Database query failed");


    private int errorCode;
    private String errorMessage;

    private String errorState = null;

    ExceptionEnum(int errorCode, String errorMessage, String errorState) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorState = errorState;
    }

    ExceptionEnum(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getErrorState() {
        return errorState;
    }
}
