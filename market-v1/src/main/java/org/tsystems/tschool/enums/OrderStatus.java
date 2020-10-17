package org.tsystems.tschool.enums;

/**
 * The enum Order status.
 */
public enum OrderStatus {
    /**
     * The Status awaiting shipment.
     */
    STATUS_AWAITING_SHIPMENT("Waiting shipment"),
    /**
     * Status shipped order status.
     */
    STATUS_SHIPPED("Shipped"),
    /**
     * Status delivered order status.
     */
    STATUS_DELIVERED("Delivered");

    private final String displayValue;

    private OrderStatus(String displayValue) {
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