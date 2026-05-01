"""
Simple integration test runner for Pricing Engine
Runs the Java application and verifies output
"""

import subprocess
import re
import sys


def run_app():
    """Run the Gradle app and capture output"""
    try:
        result = subprocess.run(
            ["gradlew.bat", "run"],
            cwd=".",
            capture_output=True,
            text=True,
            timeout=30
        )
        return result.stdout, result.stderr, result.returncode
    except subprocess.TimeoutExpired:
        return None, "Timeout", 1
    except FileNotFoundError:
        return None, "Gradlew not found", 1


def extract_final_price(output):
    """Extract final price from app output"""
    match = re.search(r"Final Price after Tax & Discount:\s*([\d.]+)", output)
    if match:
        return float(match.group(1))
    return None


def test_app_runs():
    """Test that application runs without errors"""
    stdout, stderr, code = run_app()
    if code == 0 and stdout and "PRICING ENGINE REPORT" in stdout:
        print("✅ Test 1 PASSED: Application runs successfully")
        return True
    else:
        print("❌ Test 1 FAILED: Application failed to run")
        print(f"   stderr: {stderr}")
        return False


def test_final_price_exists():
    """Test that final price is calculated"""
    stdout, stderr, code = run_app()
    if code == 0:
        price = extract_final_price(stdout)
        if price and price > 0:
            print(f"✅ Test 2 PASSED: Final price calculated: ${price:.2f}")
            return True
    print("❌ Test 2 FAILED: Final price not found or invalid")
    return False


def test_expected_price():
    """Test expected price for VIP with SAVE10"""
    stdout, stderr, code = run_app()
    if code == 0:
        price = extract_final_price(stdout)
        expected = 448.5
        if price and abs(price - expected) < 0.1:
            print(f"✅ Test 3 PASSED: Expected price ${expected:.2f}, got ${price:.2f}")
            return True
    print(f"❌ Test 3 FAILED: Expected price ~${448.5:.2f}")
    return False


def test_output_format():
    """Test output format"""
    stdout, stderr, code = run_app()
    if code == 0 and "================================" in stdout and "PRICING ENGINE REPORT" in stdout:
        print("✅ Test 4 PASSED: Output format is correct")
        return True
    print("❌ Test 4 FAILED: Output format incorrect")
    return False


if __name__ == "__main__":
    print("\n" + "="*60)
    print("PRICING ENGINE - INTEGRATION TESTS")
    print("="*60 + "\n")
    
    tests = [
        test_app_runs,
        test_final_price_exists,
        test_expected_price,
        test_output_format
    ]
    
    passed = 0
    failed = 0
    
    for test in tests:
        try:
            if test():
                passed += 1
            else:
                failed += 1
        except Exception as e:
            print(f"❌ {test.__name__} FAILED with exception: {e}")
            failed += 1
    
    print("\n" + "="*60)
    print(f"RESULTS: {passed} PASSED, {failed} FAILED")
    print("="*60 + "\n")
    
    sys.exit(0 if failed == 0 else 1)
