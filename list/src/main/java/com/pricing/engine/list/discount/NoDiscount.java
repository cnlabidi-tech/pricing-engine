package com.pricing.engine.list.discount;

/**
 * No discount strategy (0% discount)
 */
public class NoDiscount implements DiscountStrategy {
    
    @Override
    public double calculateDiscount(double subtotal) {
        return 0.0;
    }

    @Override
    public String getDescription() {
        return "No discount";
    }
}
