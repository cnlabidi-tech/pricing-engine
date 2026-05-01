package com.pricing.engine.list.discount;

/**
 * Percentage-based discount strategy
 */
public class PercentageDiscount implements DiscountStrategy {
    
    private final double percentage;
    private final String description;

    /**
     * Create percentage-based discount
     * @param percentage discount percentage (0.20 = 20%)
     */
    public PercentageDiscount(double percentage) {
        if (percentage < 0.0 || percentage > 1.0) {
            throw new IllegalArgumentException(
                "Percentage must be between 0 and 1, got: " + percentage
            );
        }
        this.percentage = percentage;
        this.description = String.format("%.0f%% discount", percentage * 100);
    }

    @Override
    public double calculateDiscount(double subtotal) {
        return subtotal * percentage;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
