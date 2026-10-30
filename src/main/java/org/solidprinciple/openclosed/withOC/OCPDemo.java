package org.solidprinciple.openclosed.withOC;

/**
 * ✅ Demo — WITH Open/Closed Principle
 *
 * <pre>
 * ─────────────────────────────────────────────────────────────────────────────────
 * O — OPEN/CLOSED PRINCIPLE (OCP)
 *
 * "Software entities should be open for extension, but closed for modification."
 *
 * ─────────────────────────────────────────────────────────────────────────────────
 * ✅ Solution (this demo)
 *   Shape interface defines area() — each shape owns its formula.
 *   AreaCalculator.calculateArea(Shape) never needs to change.
 *   Adding Triangle = new class implementing Shape. AreaCalculator untouched. ✅
 *
 * ─────────────────────────────────────────────────────────────────────────────────
 * 📦 REAL-WORLD USE CASES
 *
 * 1. Payment Gateway
 *       PaymentGateway interface → StripeGateway, PayPalGateway, CryptoGateway
 *       New provider = new class, PaymentProcessor untouched.
 *
 * 2. Discount Engine (e-commerce)
 *       DiscountStrategy interface → MemberDiscount, CouponDiscount, SeasonalDiscount
 *
 * 3. Export / Report Generation
 *       Exporter interface → PDFExporter, CSVExporter, ExcelExporter
 *
 * 4. Notification Channels
 *       Notifier interface → EmailNotifier, SMSNotifier, PushNotifier
 *
 * 5. Tax Rules by Region
 *       TaxStrategy → GSTStrategy, VATStrategy, SalesTaxStrategy
 *
 * ─────────────────────────────────────────────────────────────────────────────────
 * 🧠 KEY BENEFITS
 *   • Zero edits to stable, tested code when requirements grow
 *   • Works hand-in-hand with Strategy and Factory patterns
 *   • New shapes / strategies are isolated — can't break existing ones
 *
 * ─────────────────────────────────────────────────────────────────────────────────
 * ❓ Common Interview Questions
 *   "How do you add a new feature without breaking existing functionality?"
 *   "What's wrong with using instanceof in business logic?"
 * ─────────────────────────────────────────────────────────────────────────────────
 * </pre>
 */
public class OCPDemo {

    public static void main(String[] args) {
        AreaCalculator calculator = new AreaCalculator();

        // Each shape owns its area formula — AreaCalculator never changes ✅
        Shape rect   = new Rectangle(5, 3);
        Shape circle = new Circle(4);

        System.out.println("Rectangle area: " + calculator.calculateArea(rect));
        System.out.println("Circle area:    " + calculator.calculateArea(circle));

        // New shape added — AreaCalculator untouched ✅
        // Shape triangle = new Triangle(6, 4);
        // System.out.println("Triangle area:  " + calculator.calculateArea(triangle));
    }
}
