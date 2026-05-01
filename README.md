# Pricing Engine

A Java-based pricing and discount calculation engine built with Gradle, featuring comprehensive unit testing with JUnit 5 and integration testing with Python.

## Project Overview

This lab project implements a pricing engine that calculates the final price of orders based on:
- **Inputs**: Item prices, quantities, customer type (REGULAR/VIP), discount codes
- **Outputs**: Subtotal, discount amount, tax, and final price

## Project Structure

```
pricing-engine/
├── app/                          # Application module
│   ├── src/main/java/.../app/
│   │   └── App.java             # Main entry point
│   └── build.gradle
├── list/                         # Core pricing engine library
│   ├── src/main/java/.../list/
│   │   ├── PricingEngine.java   # Main orchestrator
│   │   ├── PricingResult.java   # Result object with breakdown
│   │   ├── CustomerType.java    # Enum for customer types
│   │   ├── DiscountCalculator.java  # Discount calculation
│   │   ├── TaxCalculator.java   # Tax calculation
│   │   └── discount/            # Strategy pattern for discounts
│   │       ├── DiscountStrategy.java
│   │       ├── DiscountCode.java
│   │       ├── PercentageDiscount.java
│   │       ├── FixedDiscount.java
│   │       └── NoDiscount.java
│   ├── src/test/java/.../list/
│   │   └── PricingEngineTest.java # 14 comprehensive JUnit 5 tests
│   └── build.gradle
├── utilities/                    # Utilities module
│   └── build.gradle
├── settings.gradle
├── README.md
├── simple_integration_test.py    # Python integration tests (4 tests)
└── .gitignore
```

## Technology Stack

- **Language**: Java 11+
- **Build System**: Gradle
- **Testing**: JUnit 5
- **Version Control**: Git
- **Integration Testing**: Python (pytest)

## Getting Started

### Prerequisites
- JDK 11 or higher
- Gradle (via gradlew)
- Python 3.8+ (for integration tests)

### Build the Project
```bash
./gradlew clean build
```

### Run Tests
```bash
# Unit tests
./gradlew test

# Integration tests (Python)
python integration_test.py
```

### Run the Application
```bash
./gradlew run
```

## Refactoring & Design Patterns

This project demonstrates **progressive refactoring** with clear separation of concerns:

### Phase 1: Separation of Concerns
- Extracted `CustomerType` enum for customer type management
- Created `DiscountCalculator` for all discount logic
- Created `TaxCalculator` for tax calculations
- Refactored `PricingEngine` to orchestrate calculations

### Phase 2: Strategy Pattern
- Implemented `DiscountStrategy` interface for flexible discount calculations
- Created concrete strategies: `PercentageDiscount`, `FixedDiscount`, `NoDiscount`
- Introduced `DiscountCode` enum with built-in strategies
- Enables easy extension with new discount types

### Phase 3: Result Object
- Created `PricingResult` immutable class for detailed breakdowns
- Added `calculateWithDetails()` method to get complete pricing information
- Provides `getDetailedBreakdown()` for formatted output
- Better API design for client code

### Key Design Benefits
✅ **Single Responsibility**: Each class has one reason to change
✅ **Open/Closed**: Easy to extend with new discount strategies
✅ **Dependency Inversion**: Depends on abstractions (DiscountStrategy)
✅ **Testability**: Each component can be tested independently
✅ **Maintainability**: Clear, well-documented code structure

## Testing

### Unit Tests (JUnit 5)
**14 comprehensive tests** covering:
- Happy path scenarios (VIP, REGULAR customers)
- Multiple items with various discount codes
- Edge cases (zero prices, negative values, null inputs)
- Input validation and error handling
- Case-insensitive input handling

Run tests:
```bash
./gradlew test
```

### Integration Tests (Python)
**4 integration tests** verifying:
- Application runs successfully
- Final price is calculated correctly
- Expected values match calculations
- Output format is correct

Run tests:
```bash
python simple_integration_test.py
```

## Example Usage

```java
PricingEngine engine = new PricingEngine();

double[] prices = {100.0, 200.0};
int[] quantities = {1, 2};

// Simple calculation
double finalPrice = engine.calculateFinalPrice(
    prices, quantities, "VIP", "SAVE10"
);
// Returns: 448.5

// Detailed breakdown
PricingResult result = engine.calculateWithDetails(
    prices, quantities, "VIP", "SAVE10"
);
System.out.println(result.getDetailedBreakdown());
// Output:
// Subtotal:         $500.00
// Customer Discount: -$100.00
// Code Discount:     -$10.00
// Total Discount:    -$110.00
// Tax (15%):         +$58.50
// Final Price:       $448.50
```

## Lab Workflow

This project demonstrates a complete software engineering workflow:
1. ✅ Gradle-based multi-project build (2 modules: app, list)
2. ✅ Unit testing with JUnit 5 (14 comprehensive tests)
3. ✅ Git/GitHub version control (5 commits with clear messages)
4. ✅ Code refactoring with clear commits (3 refactoring phases)
5. ✅ Integration testing with Python (4 tests passing)
6. ✅ Comprehensive documentation

## Git Commit History

- `2143397` - Initial commit: Gradle project setup with tests
- `d9e2ea6` - Refactor: Separate concerns (CustomerType, DiscountCalculator, TaxCalculator)
- `4fdc0e8` - Refactor: Implement Strategy pattern for discounts
- `c0b0d5b` - Refactor: Add PricingResult class
- `41bc6eb` - Add: Python-based integration tests

## How to Contribute

1. Create a feature branch: `git checkout -b feature/your-feature`
2. Make your changes and test: `./gradlew test`
3. Commit with clear messages: `git commit -m "Add feature: description"`
4. Push to GitHub: `git push origin feature/your-feature`
5. Create a Pull Request

## License

MIT License - See LICENSE file for details

## Author

Created as part of a Software Engineering Lab on Refactoring & Testing
