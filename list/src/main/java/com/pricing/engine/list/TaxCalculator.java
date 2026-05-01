package com.pricing.engine.list;

/**
 * Responsible for calculating taxes
 * Applies tax percentage on taxable amount (subtotal - discount)
 */
public class TaxCalculator {
    
    private static final double DEFAULT_TAX_RATE = 0.15; // 15% tax
    private final double taxRate;

    /**
     * Create TaxCalculator with default 15% tax rate
     */
    public TaxCalculator() {
        this(DEFAULT_TAX_RATE);
    }

    /**
     * Create TaxCalculator with custom tax rate
     * @param taxRate tax rate as decimal (0.15 = 15%)
     */
    public TaxCalculator(double taxRate) {
        if (taxRate < 0.0 || taxRate > 1.0) {
            throw new IllegalArgumentException(
                "Tax rate must be between 0.0 and 1.0, got: " + taxRate
            );
        }
        this.taxRate = taxRate;
    }

    /**
     * Calculate tax on taxable amount
     * Tax is calculated on (subtotal - discount)
     * @param subtotal the original subtotal
     * @param discount the discount amount
     * @return tax amount
     */
    public double calculateTax(double subtotal, double discount) {
        double taxableAmount = subtotal - discount;
        if (taxableAmount < 0) {
            taxableAmount = 0;
        }
        return taxableAmount * taxRate;
    }

    /**
     * Get current tax rate
     * @return tax rate as decimal
     */
    public double getTaxRate() {
        return taxRate;
    }
}
