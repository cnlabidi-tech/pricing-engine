package com.pricing.engine.list.discount;

/**
 * Fixed amount discount strategy
 */
public class FixedDiscount implements DiscountStrategy {
    
    private final double amount;
    private final String description;

    /**
     * Create fixed amount discount
     * @param amount fixed discount amount
     */
    public FixedDiscount(double amount) {
        if (amount < 0.0) {
            throw new IllegalArgumentException(
                "Discount amount cannot be negative, got: " + amount
            );
        }
        this.amount = amount;
        this.description = String.format("$%.2f discount", amount);
    }

    @Override
    public double calculateDiscount(double subtotal) {
        return amount;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
