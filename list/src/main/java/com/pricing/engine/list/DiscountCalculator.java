package com.pricing.engine.list;

/**
 * Responsible for calculating discounts
 * Handles customer type discounts and discount codes
 */
public class DiscountCalculator {
    
    private static final double SAVE_10_AMOUNT = 10.0;
    private static final double SAVE_20_AMOUNT = 20.0;

    /**
     * Calculate total discount amount
     * @param subtotal the subtotal amount before discount
     * @param customerType the customer type (REGULAR or VIP)
     * @param discountCode the discount code (SAVE10, SAVE20, NONE, etc.)
     * @return total discount amount
     */
    public double calculateTotalDiscount(
            double subtotal,
            CustomerType customerType,
            String discountCode) {
        
        // Customer type discount
        double customerDiscount = subtotal * customerType.getDiscountRate();
        
        // Additional code discount
        double codeDiscount = calculateCodeDiscount(discountCode);
        
        return customerDiscount + codeDiscount;
    }

    /**
     * Calculate discount from code
     * @param discountCode the discount code
     * @return discount amount from code
     */
    public double calculateCodeDiscount(String discountCode) {
        if (discountCode == null || discountCode.isEmpty() || "NONE".equals(discountCode)) {
            return 0.0;
        }

        switch (discountCode) {
            case "SAVE10":
                return SAVE_10_AMOUNT;
            case "SAVE20":
                return SAVE_20_AMOUNT;
            default:
                // Unknown codes provide no discount
                return 0.0;
        }
    }

    /**
     * Parse discount code from string (case-insensitive)
     * @param code the discount code
     * @return normalized discount code
     */
    public static String normalizeCode(String code) {
        if (code == null) {
            return "NONE";
        }
        String normalized = code.toUpperCase().trim();
        return normalized.isEmpty() ? "NONE" : normalized;
    }
}
