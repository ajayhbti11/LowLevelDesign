package org.solidprinciple.openclosed.withOutOC;

/**
 * ❌ Demo — WITHOUT Open/Closed Principle
 *
 * <pre>
 * ─────────────────────────────────────────────────────────────────────────────────
 * O — OPEN/CLOSED PRINCIPLE (OCP)
 *
 * "Software entities should be open for extension, but closed for modification."
 * You should be able to add new behaviour without editing existing, tested code.
 *
 * ─────────────────────────────────────────────────────────────────────────────────
 * ❌ Problem (this demo)
 *   AreaCalculator.calculateArea() uses instanceof checks.
 *   Adding Triangle requires editing AreaCalculator — reopening tested code. ❌
 *   Every new shape is another else-if. Risk of regression grows with each change.
 *
 * ✅ Fix → see withOC package
 *   Shape interface with area() — each shape owns its own formula.
 *   AreaCalculator just calls shape.area() — never needs to change again.
 *
 * ─────────────────────────────────────────────────────────────────────────────────
 * 📦 REAL-WORLD USE CASES
 *
 * 1. Payment Gateway
 *       Bad:  PaymentProcessor with if (type == "stripe") / if (type == "paypal")
 *       Good: PaymentGateway interface → StripeGateway, PayPalGateway, CryptoGateway
 *
 * 2. Discount Engine
 *       Bad:  if (user.isMember()) ... else if (coupon != null) ...
 *       Good: DiscountStrategy interface → MemberDiscount, CouponDiscount, SeasonalDiscount
 *
 * 3. Export / Report Generation
 *       Bad:  if (format == "pdf") ... else if (format == "csv") ...
 *       Good: Exporter interface → PDFExporter, CSVExporter, ExcelExporter
 *
 * 4. Notification Channels
 *       Bad:  if (channel == "email") ... else if (channel == "sms") ...
 *       Good: Notifier interface → EmailNotifier, SMSNotifier, PushNotifier
 *
 * ─────────────────────────────────────────────────────────────────────────────────
 * 🧠 KEY BENEFITS
 *   • New shape / strategy / channel = new class, zero edits to existing code
 *   • Existing tests keep passing — nothing was touched
 *   • Follows the Strategy pattern naturally
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

        Rectangle rect   = new Rectangle(5, 3);
        Circle circle    = new Circle(4);

        // Works — but adding Triangle means editing AreaCalculator ❌
        System.out.println("Rectangle area: " + calculator.calculateArea(rect));
        System.out.println("Circle area:    " + calculator.calculateArea(circle));
    }
}
