package com.windula.oms.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatusResponse {

    private int code;

    private String state;

    private String message;

    private String info;

    public StatusResponse(String state, String message) {
        this.state = state;
        this.message = message;
    }

    public StatusResponse(int code, String state, String message) {
        this.code = code;
        this.state = state;
        this.message = message;
    }

    public StatusResponse(int code, String state) {
        this.code = code;
        this.state = state;
    }

    public StatusResponse(int code, String state, String message, String info) {
        this.code = code;
        this.state = state;
        this.message = message;
        this.info = info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
