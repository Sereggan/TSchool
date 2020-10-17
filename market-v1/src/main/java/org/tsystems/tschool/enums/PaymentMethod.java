package org.tsystems.tschool.enums;

/**
 * The enum Payment method.
 */
public enum PaymentMethod {
    /**
     * The Method cards.
     */
    METHOD_CARDS("By card"),
    /**
     * Method cash payment method.
     */
    METHOD_CASH("Cash");

    private final String displayValue;

    private PaymentMethod(String displayValue) {
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
