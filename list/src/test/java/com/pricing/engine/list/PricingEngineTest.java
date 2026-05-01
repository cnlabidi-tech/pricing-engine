package com.pricing.engine.list;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class PricingEngineTest {

    private PricingEngine engine;

    @BeforeEach
    void setUp() {
        engine = new PricingEngine();
    }

    @Test
    void testRegularCustomer() {
        double[] prices = {100.0};
        int[] quantities = {1};
        // Calculation: 100 - 5 (discount) + 14.25 (tax 15% of 95) = 109.25
        double result = engine.calculateFinalPrice(prices, quantities, "REGULAR", "NONE");
        assertEquals(109.25, result, 0.01);
    }

    @Test
    void testVIPCustomerWithCode() {
        double[] prices = {200.0};
        int[] quantities = {1};
        // Calculation: 200 - 40 (VIP) - 10 (SAVE10) = 150. Tax 15% of 150 = 22.5. Total = 172.5
        double result = engine.calculateFinalPrice(prices, quantities, "VIP", "SAVE10");
        assertEquals(172.5, result, 0.01);
    }

    @Test
    void testMultipleItems() {
        double[] prices = {100.0, 200.0};
        int[] quantities = {1, 2};
        // Calculation: (100*1 + 200*2) = 500. VIP discount 20% = 100. Tax 15% of 400 = 60. Total = 460
        double result = engine.calculateFinalPrice(prices, quantities, "VIP", "NONE");
        assertEquals(460.0, result, 0.01);
    }

    @Test
    void testNoDiscount() {
        double[] prices = {100.0};
        int[] quantities = {1};
        // Calculation: 100 - 0 + 15 (tax 15% of 100) = 115
        double result = engine.calculateFinalPrice(prices, quantities, "REGULAR", "NONE");
        // Actually REGULAR gets 5%, so: 100 - 5 + 14.25 = 109.25
        assertEquals(109.25, result, 0.01);
    }

    @Test
    void testSAVE20Code() {
        double[] prices = {100.0};
        int[] quantities = {1};
        // Calculation: 100 - 20 (SAVE20) + 12 (tax 15% of 80) = 92
        double result = engine.calculateFinalPrice(prices, quantities, "REGULAR", "SAVE20");
        // REGULAR: 100 - 5 - 20 = 75. Tax = 11.25. Total = 86.25
        assertEquals(86.25, result, 0.01);
    }

    @Test
    void testCaseInsensitiveCustomerType() {
        double[] prices = {100.0};
        int[] quantities = {1};
        double result1 = engine.calculateFinalPrice(prices, quantities, "VIP", "NONE");
        double result2 = engine.calculateFinalPrice(prices, quantities, "vip", "NONE");
        assertEquals(result1, result2);
    }

    @Test
    void testCaseInsensitiveDiscountCode() {
        double[] prices = {100.0};
        int[] quantities = {1};
        double result1 = engine.calculateFinalPrice(prices, quantities, "VIP", "SAVE10");
        double result2 = engine.calculateFinalPrice(prices, quantities, "VIP", "save10");
        assertEquals(result1, result2);
    }

    @Test
    void testInvalidCustomerType() {
        double[] prices = {100.0};
        int[] quantities = {1};
        assertThrows(IllegalArgumentException.class, () ->
            engine.calculateFinalPrice(prices, quantities, "PREMIUM", "NONE")
        );
    }

    @Test
    void testNullPrices() {
        assertThrows(IllegalArgumentException.class, () ->
            engine.calculateFinalPrice(null, new int[]{1}, "REGULAR", "NONE")
        );
    }

    @Test
    void testNullQuantities() {
        assertThrows(IllegalArgumentException.class, () ->
            engine.calculateFinalPrice(new double[]{100.0}, null, "REGULAR", "NONE")
        );
    }

    @Test
    void testMismatchedArrayLengths() {
        double[] prices = {100.0, 200.0};
        int[] quantities = {1};
        assertThrows(IllegalArgumentException.class, () ->
            engine.calculateFinalPrice(prices, quantities, "REGULAR", "NONE")
        );
    }

    @Test
    void testEmptyArrays() {
        assertThrows(IllegalArgumentException.class, () ->
            engine.calculateFinalPrice(new double[]{}, new int[]{}, "REGULAR", "NONE")
        );
    }

    @Test
    void testNegativePrice() {
        double[] prices = {-100.0};
        int[] quantities = {1};
        assertThrows(IllegalArgumentException.class, () ->
            engine.calculateFinalPrice(prices, quantities, "REGULAR", "NONE")
        );
    }

    @Test
    void testNegativeQuantity() {
        double[] prices = {100.0};
        int[] quantities = {-1};
        assertThrows(IllegalArgumentException.class, () ->
            engine.calculateFinalPrice(prices, quantities, "REGULAR", "NONE")
        );
    }

    @Test
    void testZeroPrices() {
        double[] prices = {0.0};
        int[] quantities = {1};
        double result = engine.calculateFinalPrice(prices, quantities, "REGULAR", "NONE");
        assertEquals(0.0, result, 0.01);
    }
}
