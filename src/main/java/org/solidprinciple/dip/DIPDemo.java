package org.solidprinciple.dip;

/**
 * ❌ Demo — WITHOUT Dependency Inversion Principle
 *
 * <pre>
 * ─────────────────────────────────────────────────────────────────────────────────
 * D — DEPENDENCY INVERSION PRINCIPLE (DIP)
 *
 * "High-level modules should not depend on low-level modules.
 *  Both should depend on abstractions."
 * "Abstractions should not depend on details. Details should depend on abstractions."
 *
 * ─────────────────────────────────────────────────────────────────────────────────
 * ❌ Problem (this demo)
 *   PasswordReminder depends directly on MySQLConnection (a concrete class).
 *   Switching to PostgreSQL means editing PasswordReminder — tight coupling. ❌
 *   Unit testing PasswordReminder requires a real MySQL connection. ❌
 *
 * ✅ Fix → see withdip package
 *   PasswordReminder depends on DBConnectionInterface (an abstraction).
 *   Inject MySQLConnection, PostgresConnection, or a mock — PasswordReminder
 *   never needs to change.
 *
 * ─────────────────────────────────────────────────────────────────────────────────
 * 📦 REAL-WORLD USE CASES
 *
 * 1. Service → Repository
 *       Bad:  OrderService directly instantiates MySQLOrderRepository
 *       Good: OrderService depends on OrderRepository interface
 *             → inject MySQL, Mongo, or in-memory for tests
 *
 * 2. Notification Service
 *       Bad:  UserService directly calls SMSClient
 *       Good: UserService depends on NotificationService interface
 *             → swap SMS, Email, Push at runtime or in tests
 *
 * 3. Payment Gateway
 *       Bad:  CheckoutService directly instantiates StripeClient
 *       Good: CheckoutService depends on PaymentGateway interface
 *             → inject Stripe, PayPal, mock in tests
 *
 * 4. Logger
 *       Bad:  BusinessService directly calls FileLogger
 *       Good: BusinessService depends on Logger interface
 *             → inject FileLogger, CloudLogger, or NoOpLogger for tests
 *
 * 5. Cache
 *       Bad:  ProductService directly uses RedisCache
 *       Good: ProductService depends on CacheService interface
 *             → swap Redis, Memcached, or in-memory without touching ProductService
 *
 * ─────────────────────────────────────────────────────────────────────────────────
 * 🧠 KEY BENEFITS
 *   • High-level business logic stays stable as infrastructure changes
 *   • Easy to mock dependencies in unit tests
 *   • Foundation for Dependency Injection frameworks (Spring, Guice)
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
        // PasswordReminder is hard-wired to MySQLConnection ❌
        // Switching to Postgres means editing PasswordReminder
        MySQLConnection mysql = new MySQLConnection();
        PasswordReminder reminder = new PasswordReminder(mysql);

        System.out.println("Connected: " + mysql.connect());
        // Cannot swap to Postgres without modifying PasswordReminder ❌
    }
}
