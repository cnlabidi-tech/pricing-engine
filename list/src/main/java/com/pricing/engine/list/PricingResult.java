package com.pricing.engine.list;

import java.util.Objects;

/**
 * Immutable result object for pricing calculations
 * Contains all pricing details (subtotal, discount, tax, final price)
 */
public class PricingResult {
    
    private final double subtotal;
    private final double customerDiscount;
    private final double codeDiscount;
    private final double totalDiscount;
    private final double tax;
    private final double finalPrice;

    /**
     * Create PricingResult
     */
    public PricingResult(
            double subtotal,
            double customerDiscount,
            double codeDiscount,
            double tax,
            double finalPrice) {
        this.subtotal = subtotal;
        this.customerDiscount = customerDiscount;
        this.codeDiscount = codeDiscount;
        this.totalDiscount = customerDiscount + codeDiscount;
        this.tax = tax;
        this.finalPrice = finalPrice;
    }

    // Getters
    public double getSubtotal() {
        return subtotal;
    }

    public double getCustomerDiscount() {
        return customerDiscount;
    }

    public double getCodeDiscount() {
        return codeDiscount;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public double getTax() {
        return tax;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    /**
     * Get formatted string representation
     */
    @Override
    public String toString() {
        return String.format(
            "PricingResult{" +
            "subtotal=%.2f, " +
            "customerDiscount=%.2f, " +
            "codeDiscount=%.2f, " +
            "totalDiscount=%.2f, " +
            "tax=%.2f, " +
            "finalPrice=%.2f" +
            "}",
            subtotal, customerDiscount, codeDiscount, totalDiscount, tax, finalPrice
        );
    }

    /**
     * Get detailed breakdown as formatted string
     */
    public String getDetailedBreakdown() {
        return String.format(
            "Subtotal:         $%.2f\n" +
            "Customer Discount: -$%.2f\n" +
            "Code Discount:     -$%.2f\n" +
            "Total Discount:    -$%.2f\n" +
            "Tax (15%):         +$%.2f\n" +
            "Final Price:       $%.2f",
            subtotal, customerDiscount, codeDiscount, totalDiscount, tax, finalPrice
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PricingResult result = (PricingResult) o;
        return Double.compare(result.finalPrice, finalPrice) == 0 &&
               Double.compare(result.subtotal, subtotal) == 0 &&
               Double.compare(result.totalDiscount, totalDiscount) == 0 &&
               Double.compare(result.tax, tax) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(subtotal, totalDiscount, tax, finalPrice);
    }
}
