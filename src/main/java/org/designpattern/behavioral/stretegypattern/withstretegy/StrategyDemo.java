package org.designpattern.behavioral.stretegypattern.withstretegy;

public class StrategyDemo {

    /*
     * ✅ STRATEGY DESIGN PATTERN
     *
     * Define a family of algorithms, encapsulate each one, and make them interchangeable.
     * The algorithm can vary independently from the client that uses it.
     *
     * Instead of hard-coding logic, we choose an algorithm at runtime based on context.
     * ─────────────────────────────────────────────────────────────────────────────────
     *
     * 📦 REAL-WORLD USE CASES
     *
     * 1. Payment Processing  (this example)
     *       CreditCard / PayPal / UPI / Crypto — swap at runtime without touching the context.
     *
     * 2. Sorting Algorithms
     *       Collections.sort() accepts a Comparator — the Comparator IS the strategy.
     *       e.g. sort by price, sort by rating, sort by date — all plug in at runtime.
     *
     * 3. Discount / Pricing Engine  (e-commerce)
     *       SeasonalDiscount, LoyaltyDiscount, CouponDiscount — same interface, different logic.
     *       Cart picks the right strategy without an if-else tree.
     *
     * 4. Compression
     *       ZipStrategy, GzipStrategy, LZ4Strategy — same compress() interface.
     *       File archiver switches algorithm based on file type or user preference.
     *
     * 5. Navigation / Route Planning  (Google Maps style)
     *       DrivingRoute, WalkingRoute, CyclingRoute, TransitRoute — strategy per mode.
     *
     * 6. Authentication
     *       JWTStrategy, OAuthStrategy, BasicAuthStrategy — auth middleware delegates
     *       to whichever strategy is configured for the route.
     *
     * 7. Logging
     *       ConsoleLogger, FileLogger, CloudLogger — injected as a strategy so the
     *       service never hard-codes where logs go.
     *
     * 8. Retry Policies  (resilience libraries like Resilience4j)
     *       ExponentialBackoff, FixedDelay, NoRetry — the HTTP client holds a strategy
     *       reference and calls retry() without knowing the algorithm.
     *
     * 9. Tax Calculation
     *       GSTStrategy, VATStrategy, SalesTaxStrategy — differs by region; the
     *       order service injects the correct one per customer locale.
     *
     * 10. Data Export
     *       CSVExport, PDFExport, ExcelExport — report generator delegates formatting
     *       to whichever export strategy the user chose.
     *
     * ─────────────────────────────────────────────────────────────────────────────────
     * 🧠 KEY BENEFITS
     *   • Replaces big if-else / switch blocks with clean polymorphism
     *   • Open/Closed — add a new algorithm without touching existing code
     *   • Strategies are independently testable units
     *   • Behaviour swappable at runtime (feature flags, user preferences, A/B tests)
     * ─────────────────────────────────────────────────────────────────────────────────
     * ❓ Common Interview Questions
     *   "Design a payment system supporting multiple payment methods"
     *   "How would you implement a sorting engine where the algorithm can change at runtime?"
     * ─────────────────────────────────────────────────────────────────────────────────
     */

    public static void main(String[] args) {
        PaymentContext context = new PaymentContext();

        // Swap strategy at runtime — no if-else, no changes to PaymentContext
        context.setPaymentStrategy(new CreditCardPayment());
        context.pay(500);

        context.setPaymentStrategy(new PaypalPayment());
        context.pay(1200);

        context.setPaymentStrategy(new UPIPayment());
        context.pay(300);
    }
}
