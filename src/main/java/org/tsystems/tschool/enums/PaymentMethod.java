package org.tsystems.tschool.enums;

public enum PaymentMethod {
    METHOD_CARDS("By card"),
    METHOD_CASH("Cash");

    private final String displayValue;

    private PaymentMethod(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
