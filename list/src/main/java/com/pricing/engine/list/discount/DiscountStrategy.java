package com.pricing.engine.list.discount;

/**
 * Strategy interface for calculating discount amounts
 * Implements Strategy Design Pattern for flexible discount calculations
 */
public interface DiscountStrategy {
    
    /**
     * Calculate discount amount
     * @param subtotal the subtotal amount
     * @return discount amount
     */
    double calculateDiscount(double subtotal);
    
    /**
     * Get description of this discount strategy
     * @return description
     */
    String getDescription();
}
