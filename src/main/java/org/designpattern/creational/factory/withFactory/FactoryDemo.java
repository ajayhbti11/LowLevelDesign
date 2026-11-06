package org.designpattern.creational.factory.withfactory;

/**
 * ✅ Demo — WITH Factory Pattern
 *
 * <pre>
 * ─────────────────────────────────────────────────────────────────────────────
 * FACTORY (a.k.a. Simple Factory / Factory Method) DESIGN PATTERN
 *
 * Define an interface for creating an object, but let a dedicated factory
 * class decide which concrete class to instantiate.
 * The client depends only on the interface, never on concrete classes.
 *
 * ─────────────────────────────────────────────────────────────────────────────
 * Structure (this demo — notification system)
 *
 *   Notification             — product interface
 *   ├─ EmailNotification     — concrete product
 *   ├─ SMSNotification       — concrete product
 *   ├─ PushNotification      — concrete product
 *   └─ WhatsAppNotification  — concrete product (added later, zero caller change ✅)
 *
 *   NotificationFactory      — knows HOW to create; clients ask it by type
 *
 * ─────────────────────────────────────────────────────────────────────────────
 * ✅ Benefits
 *   - Caller is decoupled from concrete classes (depends only on interface).
 *   - New products (channels) = add one class + one factory line.
 *     All callers stay unchanged  → Open/Closed Principle. ✅
 *   - Object creation logic lives in one place (DRY). ✅
 *   - Easy to test: swap factory to return mocks. ✅
 *
 * ─────────────────────────────────────────────────────────────────────────────
 * ❓ When to use Factory vs Abstract Factory
 *   Factory        — ONE family, ONE product type  (create one Notification)
 *   Abstract Factory — MULTIPLE families, MULTIPLE related products
 *                      (create a whole UI toolkit: Button + CheckBox + TextField)
 *
 * ─────────────────────────────────────────────────────────────────────────────
 * 📦 Real-world examples
 *   1. Notification / alert dispatch systems (this demo)
 *   2. java.util.Calendar.getInstance()
 *   3. java.sql.DriverManager.getConnection()  (JDBC factory)
 *   4. Spring BeanFactory — getBean() returns the right impl
 *   5. Logback / Log4j — LoggerFactory.getLogger(...)
 *   6. Payment gateway: PaymentFactory.create("upi" | "card" | "netbanking")
 *
 * ─────────────────────────────────────────────────────────────────────────────
 * ❓ Common Interview Questions
 *   "How do you add a new payment method without touching existing code?"
 *   "What is the difference between Factory and Abstract Factory?"
 *   "Where is Factory used in Spring / Java SDK?"
 * ─────────────────────────────────────────────────────────────────────────────
 * </pre>
 */
public class FactoryDemo {

    public static void main(String[] args) {

        // ✅ Caller knows NOTHING about EmailNotification, SMSNotification, etc.
        //    It only talks to the Notification interface.

        String[] channels = {"email", "sms", "push", "whatsapp"};

        for (String channel : channels) {
            Notification notification = NotificationFactory.create(channel);  // ✅ factory call
            notification.send("Order #1024 confirmed.");
        }

        // ✅ Adding WhatsApp required:
        //   1. Create WhatsAppNotification.java
        //   2. Add one case in NotificationFactory
        //   3. THIS FILE: zero changes ✅
    }
}
