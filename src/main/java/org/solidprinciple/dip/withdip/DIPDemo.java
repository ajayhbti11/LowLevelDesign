package org.solidprinciple.dip.withdip;

/**
 * ✅ Demo — WITH Dependency Inversion Principle
 *
 * <pre>
 * ─────────────────────────────────────────────────────────────────────────────────
 * D — DEPENDENCY INVERSION PRINCIPLE (DIP)
 *
 * "High-level modules should not depend on low-level modules.
 *  Both should depend on abstractions."
 *
 * ─────────────────────────────────────────────────────────────────────────────────
 * ✅ Solution (this demo)
 *   PasswordReminder depends on DBConnectionInterface — an abstraction.
 *   Inject MySQLConnection or PostgresConnection at the call site.
 *   PasswordReminder never changes when the DB changes. ✅
 *   Pass a mock DBConnectionInterface in tests — no real DB needed. ✅
 *
 * ─────────────────────────────────────────────────────────────────────────────────
 * 📦 REAL-WORLD USE CASES
 *
 * 1. Service → Repository (Spring @Repository)
 *       OrderService depends on OrderRepository interface
 *       → Spring injects JpaOrderRepository in prod, MockOrderRepository in tests
 *
 * 2. Notification Service
 *       UserService depends on NotificationService interface
 *       → inject SMSNotifier, EmailNotifier, or PushNotifier at startup
 *
 * 3. Payment Gateway
 *       CheckoutService depends on PaymentGateway interface
 *       → inject StripeGateway, PayPalGateway, or a sandbox mock
 *
 * 4. Logger
 *       BusinessService depends on Logger interface
 *       → FileLogger in prod, NoOpLogger in unit tests
 *
 * 5. Cache
 *       ProductService depends on CacheService interface
 *       → RedisCache in prod, InMemoryCache in tests
 *
 * ─────────────────────────────────────────────────────────────────────────────────
 * 🧠 KEY BENEFITS
 *   • High-level business logic never changes due to infrastructure choices
 *   • Full testability — inject a mock, no real DB or network required
 *   • Foundation for Spring DI, Guice, CDI
 *
 * ─────────────────────────────────────────────────────────────────────────────────
 * ❓ Common Interview Questions
 *   "What is DIP and how is it different from DI (Dependency Injection)?"
 *   "How does Spring use DIP?"
 * ─────────────────────────────────────────────────────────────────────────────────
 * </pre>
 */
public class DIPDemo {

    public static void main(String[] args) {
        // Inject MySQL — PasswordReminder doesn't know or care which DB ✅
        DBConnectionInterface mysql = new MySQLConnection();
        PasswordReminder reminderMySQL = new PasswordReminder(mysql);
        System.out.println("MySQL    → " + mysql.connect());

        // Swap to Postgres — zero changes to PasswordReminder ✅
        DBConnectionInterface postgres = new PostgresSQLConnection();
        PasswordReminder reminderPostgres = new PasswordReminder(postgres);
        System.out.println("Postgres → " + postgres.connect());
    }
}
