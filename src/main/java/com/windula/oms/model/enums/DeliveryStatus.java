package com.windula.oms.model.enums;

public enum DeliveryStatus {
    FAILED(0),RECEIVED(1),PENDING(2),CANCELLED(3);
    private final int value;

    DeliveryStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
