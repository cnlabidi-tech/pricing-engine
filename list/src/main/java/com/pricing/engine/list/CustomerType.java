package com.pricing.engine.list;

/**
 * Enum representing different customer types
 * Each customer type has a specific discount percentage
 */
public enum CustomerType {
    REGULAR(0.05),   // 5% discount
    VIP(0.20);       // 20% discount

    private final double discountRate;

    CustomerType(double discountRate) {
        this.discountRate = discountRate;
    }

    /**
     * Get discount rate for this customer type
     * @return discount rate as decimal (0.05 = 5%)
     */
    public double getDiscountRate() {
        return discountRate;
    }

    /**
     * Parse customer type from string
     * @param type customer type as string (case-insensitive)
     * @return CustomerType enum value
     * @throws IllegalArgumentException if type is not valid
     */
    public static CustomerType fromString(String type) {
        try {
            return CustomerType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                "Invalid customer type: " + type + 
                ". Valid types are: REGULAR, VIP"
            );
        }
    }
}
