package com.pricing.engine.app;

import com.pricing.engine.list.PricingEngine;

public class App {
    public static void main(String[] args) {
        // Create an instance of the engine
        PricingEngine engine = new PricingEngine();
        
        // Sample data (prices and quantities)
        double[] prices = {100.0, 200.0};
        int[] quantities = {1, 2};
        
        // Calculate final price for VIP customer with discount code
        double finalPrice = engine.calculateFinalPrice(prices, quantities, "VIP", "SAVE10");
        
        // Print the result
        System.out.println("================================");
        System.out.println("   PRICING ENGINE REPORT");
        System.out.println("================================");
        System.out.println("Final Price after Tax & Discount: " + finalPrice);
        System.out.println("================================");
    }
}