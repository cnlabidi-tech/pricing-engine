package com.pricing.engine.list;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PricingEngineTest {

    @Test
    void testRegularCustomer() {
        PricingEngine engine = new PricingEngine();
        double[] prices = {100.0};
        int[] qtys = {1};
        // 100 - 5 (discount) + 14.25 (tax 15% of 95) = 109.25
        double result = engine.calculateFinalPrice(prices, qtys, "REGULAR", "NONE");
        assertEquals(109.25, result, 0.01);
    }

    @Test
    void testVIPCustomerWithCode() {
        PricingEngine engine = new PricingEngine();
        double[] prices = {200.0};
        int[] qtys = {1};
        // 200 - 40 (VIP) - 10 (SAVE10) = 150. Tax 15% of 150 = 22.5. Total = 172.5
        double result = engine.calculateFinalPrice(prices, qtys, "VIP", "SAVE10");
        assertEquals(172.5, result, 0.01);
    }
}