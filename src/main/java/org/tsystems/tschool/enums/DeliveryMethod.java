package org.tsystems.tschool.enums;

/**
 * The enum Delivery method.
 */
public enum DeliveryMethod {
    /**
     * Method pickup delivery method.
     */
    METHOD_PICKUP("Pickup"),
    /**
     * Method courier delivery method.
     */
    METHOD_COURIER("Courier");

    private final String displayValue;

    private DeliveryMethod(String displayValue) {
        this.displayValue = displayValue;
    }

    /**
     * Gets display value.
     *
     * @return the display value
     */
    public String getDisplayValue() {
        return displayValue;
    }
}
