package com.pricing.engine.list.discount;

/**
 * Enum representing predefined discount codes
 * Each code has an associated discount strategy
 */
public enum DiscountCode {
    NONE("No discount code", new NoDiscount()),
    SAVE10("Save $10", new FixedDiscount(10.0)),
    SAVE20("Save $20", new FixedDiscount(20.0));

    private final String description;
    private final DiscountStrategy strategy;

    DiscountCode(String description, DiscountStrategy strategy) {
        this.description = description;
        this.strategy = strategy;
    }

    /**
     * Get discount strategy for this code
     * @return discount strategy
     */
    public DiscountStrategy getStrategy() {
        return strategy;
    }

    /**
     * Get description of this code
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Parse discount code from string (case-insensitive)
     * @param code code as string
     * @return DiscountCode enum value, defaults to NONE if invalid
     */
    public static DiscountCode fromString(String code) {
        if (code == null || code.trim().isEmpty() || "NONE".equals(code.toUpperCase())) {
            return NONE;
        }
        try {
            return DiscountCode.valueOf(code.toUpperCase().trim());
        } catch (IllegalArgumentException e) {
            // Unknown codes default to NONE
            return NONE;
        }
    }
}
