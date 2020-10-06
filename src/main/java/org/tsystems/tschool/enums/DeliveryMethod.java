package org.tsystems.tschool.enums;

public enum DeliveryMethod {
    METHOD_PICKUP("Pickup"),
    METHOD_COURIER("Courier");

    private final String displayValue;

    private DeliveryMethod(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
