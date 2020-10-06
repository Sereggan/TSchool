package org.tsystems.tschool.enums;

public enum OrderStatus {
    STATUS_AWAITING_SHIPMENT("Waiting shipment"),
    STATUS_SHIPPED("Shipped"),
    STATUS_DELIVERED("Delivered");

    private final String displayValue;

    private OrderStatus(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}