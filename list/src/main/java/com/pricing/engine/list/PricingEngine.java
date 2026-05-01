package com.pricing.engine.list;

/**
 * Main pricing engine that orchestrates price calculations
 * Delegates discount and tax calculations to specialized classes
 */
public class PricingEngine {
    
    private final DiscountCalculator discountCalculator;
    private final TaxCalculator taxCalculator;

    /**
     * Create PricingEngine with default calculators
     */
    public PricingEngine() {
        this(new DiscountCalculator(), new TaxCalculator());
    }

    /**
     * Create PricingEngine with custom calculators (useful for testing)
     * @param discountCalculator custom discount calculator
     * @param taxCalculator custom tax calculator
     */
    public PricingEngine(DiscountCalculator discountCalculator, TaxCalculator taxCalculator) {
        this.discountCalculator = discountCalculator;
        this.taxCalculator = taxCalculator;
    }

    /**
     * Calculate final price including tax and discount
     * @param prices array of item prices
     * @param quantities array of item quantities
     * @param customerTypeStr customer type as string (REGULAR or VIP)
     * @param discountCode discount code (SAVE10, SAVE20, NONE, etc.)
     * @return final price after discount and tax
     * @throws IllegalArgumentException if inputs are invalid
     */
    public double calculateFinalPrice(
            double[] prices,
            int[] quantities,
            String customerTypeStr,
            String discountCode) {
        
        // Validate inputs
        validateInputs(prices, quantities);
        
        // Parse customer type
        CustomerType customerType = CustomerType.fromString(customerTypeStr);
        
        // Normalize discount code
        String normalizedCode = DiscountCalculator.normalizeCode(discountCode);
        
        // Calculate subtotal
        double subtotal = calculateSubtotal(prices, quantities);
        
        // Calculate discount
        double discount = discountCalculator.calculateTotalDiscount(
            subtotal,
            customerType,
            normalizedCode
        );
        
        // Calculate tax
        double tax = taxCalculator.calculateTax(subtotal, discount);
        
        // Final price
        return subtotal - discount + tax;
    }

    /**
     * Calculate subtotal (sum of price * quantity for each item)
     * @param prices array of item prices
     * @param quantities array of item quantities
     * @return subtotal
     */
    private double calculateSubtotal(double[] prices, int[] quantities) {
        double subtotal = 0;
        for (int i = 0; i < prices.length; i++) {
            subtotal += prices[i] * quantities[i];
        }
        return subtotal;
    }

    /**
     * Validate input arrays
     * @param prices array of prices
     * @param quantities array of quantities
     * @throws IllegalArgumentException if inputs are invalid
     */
    private void validateInputs(double[] prices, int[] quantities) {
        if (prices == null || quantities == null) {
            throw new IllegalArgumentException("Prices and quantities cannot be null");
        }
        if (prices.length != quantities.length) {
            throw new IllegalArgumentException(
                "Prices and quantities arrays must have same length"
            );
        }
        if (prices.length == 0) {
            throw new IllegalArgumentException("Prices array cannot be empty");
        }
        
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < 0) {
                throw new IllegalArgumentException("Price cannot be negative: " + prices[i]);
            }
            if (quantities[i] < 0) {
                throw new IllegalArgumentException("Quantity cannot be negative: " + quantities[i]);
            }
        }
    }
}