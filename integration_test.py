"""
Integration tests for Pricing Engine
Tests the Java application output using subprocess calls
"""

import subprocess
import re
import pytest
import sys
import os


class TestPricingEngine:
    """Test pricing engine by running the Gradle application"""

    @classmethod
    def setup_class(cls):
        """Build the project once before running tests"""
        print("Building project...")
        result = subprocess.run(
            ["gradle", "clean", "build"],
            cwd=".",
            capture_output=True,
            text=True
        )
        if result.returncode != 0:
            print("Build failed!")
            print(result.stdout)
            print(result.stderr)
            raise RuntimeError("Failed to build project")

    def run_app(self):
        """Run the Gradle app and capture output"""
        result = subprocess.run(
            ["gradle", "run"],
            cwd=".",
            capture_output=True,
            text=True,
            timeout=30
        )
        return result.stdout, result.stderr, result.returncode

    def extract_final_price(self, output):
        """Extract final price from app output"""
        match = re.search(r"Final Price after Tax & Discount:\s*([\d.]+)", output)
        if match:
            return float(match.group(1))
        return None

    def test_app_runs_successfully(self):
        """Test that application runs without errors"""
        stdout, stderr, returncode = self.run_app()
        assert returncode == 0, f"Application failed: {stderr}"
        assert "PRICING ENGINE REPORT" in stdout, "Missing report header"

    def test_final_price_calculated(self):
        """Test that final price is calculated and displayed"""
        stdout, stderr, returncode = self.run_app()
        assert returncode == 0
        final_price = self.extract_final_price(stdout)
        assert final_price is not None, "Final price not found in output"
        assert final_price > 0, "Final price should be positive"

    def test_expected_price_for_vip_with_discount(self):
        """Test expected price for VIP customer with SAVE10 code
        
        Expected calculation:
        - Prices: [100.0, 200.0], Quantities: [1, 2]
        - Subtotal: 100*1 + 200*2 = 500
        - VIP Discount (20%): 100
        - SAVE10: 10
        - Total Discount: 110
        - Tax (15% on 390): 58.5
        - Final Price: 448.5
        """
        stdout, stderr, returncode = self.run_app()
        assert returncode == 0
        final_price = self.extract_final_price(stdout)
        assert final_price is not None
        assert abs(final_price - 448.5) < 0.1, \
            f"Expected ~448.5, got {final_price}"

    def test_output_format(self):
        """Test that output is properly formatted"""
        stdout, stderr, returncode = self.run_app()
        assert returncode == 0
        assert "================================" in stdout
        assert "PRICING ENGINE REPORT" in stdout
        assert "Final Price" in stdout


if __name__ == "__main__":
    # Run tests with pytest
    pytest.main([__file__, "-v", "--tb=short"])
