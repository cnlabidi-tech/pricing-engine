package com.pricing.engine.list;

public class PricingEngine {
    public double calculateFinalPrice(double[] prices, int[] quantities, String customerType, String discountCode) {
        double subtotal = 0;

        // Calculate subtotal
        for (int i = 0; i < prices.length; i++) {
            subtotal += prices[i] * quantities[i];
        }

        // Calculate discount based on customer type
        double discount = 0;
        if (customerType.equals("VIP")) {
            discount = subtotal * 0.20; // 20% discount
        } else if (customerType.equals("REGULAR")) {
            discount = subtotal * 0.05; // 5% discount
        }

        // Additional discount for code
        if (discountCode.equals("SAVE10")) {
            discount += 10;
        }

        // Calculate tax 15% after discount
        double tax = (subtotal - discount) * 0.15;

        // Final price
        return subtotal - discount + tax;
    }
}