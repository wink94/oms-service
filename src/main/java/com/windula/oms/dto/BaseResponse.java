package com.windula.oms.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Set;

public class BaseResponse {

    protected Set<StatusResponse> requestStatuses;

    @JsonIgnore
    protected String correlationId;

    public Set<StatusResponse> getRequestStatuses() {
        return requestStatuses;
    }

    public void setRequestStatuses(Set<StatusResponse> requestStatuses) {
        this.requestStatuses = requestStatuses;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public BaseResponse(Set<StatusResponse> requestStatuses) {
        this.requestStatuses = requestStatuses;
    }

    public void addRequestStatuses(int code, String state, String message) {
        this.requestStatuses.add(new StatusResponse(code, state, message));
    }
    public BaseResponse() {
        requestStatuses = new HashSet<>();
    }
}
