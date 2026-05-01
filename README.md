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
│   │   └── PricingEngine.java   # Pricing calculation logic
│   ├── src/test/java/.../list/
│   │   └── PricingEngineTest.java # JUnit 5 tests
│   └── build.gradle
├── utilities/                    # Utilities module (optional)
│   └── build.gradle
├── settings.gradle
├── README.md
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

## Pricing Logic

### Discount Calculation
- **VIP customers**: 20% discount on subtotal
- **REGULAR customers**: 5% discount on subtotal
- **Discount codes** (e.g., SAVE10): Additional fixed amount discount

### Tax Calculation
- 15% tax applied on (subtotal - discount)

### Final Price Formula
```
Final Price = (Subtotal - Discount) + Tax
```

## Example

```
Prices: [100.0, 200.0]
Quantities: [1, 2]
Customer Type: VIP
Discount Code: SAVE10

Calculation:
- Subtotal: 100*1 + 200*2 = 500
- VIP Discount (20%): 100
- Code Discount (SAVE10): 10
- Total Discount: 110
- Tax (15% on 390): 58.5
- Final Price: 390 + 58.5 = 448.5
```

## Lab Workflow

This project demonstrates a complete software engineering workflow:
1. ✅ Gradle-based multi-project build
2. ✅ Unit testing with JUnit 5
3. ✅ Git/GitHub version control
4. 🔄 Code refactoring with clear commits
5. 🔄 Integration testing with Python
6. 🔄 Continuous improvement and testing

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
