package org.solidprinciple.srp;

/**
 * ❌ Demo — WITHOUT Single Responsibility Principle
 *
 * <pre>
 * ─────────────────────────────────────────────────────────────────────────────────
 * S — SINGLE RESPONSIBILITY PRINCIPLE (SRP)
 *
 * "A class should have only one reason to change."
 * Every class should own exactly one piece of behaviour. If it needs to change,
 * there should be only one business reason driving that change.
 *
 * ─────────────────────────────────────────────────────────────────────────────────
 * ❌ Problem (this demo)
 *   Employee owns three unrelated concerns:
 *     1. Business logic   → calculateSalary()
 *     2. Persistence      → saveToDatabase()
 *     3. Reporting        → generateReport()
 *
 *   Changing the DB schema forces you to touch Employee.
 *   Changing the report format forces you to touch Employee.
 *   One class, three reasons to change. ❌
 *
 * ✅ Fix → see withsrp package
 *   Employee         — pure data (fields + getters)
 *   SalaryCalculator — owns salary logic only
 *   EmployeeRepository — owns persistence only
 *   EmployeeReport   — owns reporting only
 *
 * ─────────────────────────────────────────────────────────────────────────────────
 * 📦 REAL-WORLD USE CASES
 *
 * 1. E-commerce Order
 *       Bad:  Order.calculateTotal() + Order.saveToDb() + Order.sendEmail()
 *       Good: OrderPricer | OrderRepository | OrderNotifier
 *
 * 2. User Management
 *       Bad:  User.hashPassword() + User.sendWelcomeMail() + User.saveUser()
 *       Good: PasswordService | MailService | UserRepository
 *
 * 3. Invoice System
 *       Bad:  Invoice.calculate() + Invoice.print() + Invoice.export()
 *       Good: InvoiceCalculator | InvoicePrinter | InvoiceExporter
 *
 * 4. Logger
 *       Bad:  Logger.format() + Logger.writeToFile() + Logger.sendToCloud()
 *       Good: LogFormatter | FileLogWriter | CloudLogWriter
 *
 * ─────────────────────────────────────────────────────────────────────────────────
 * 🧠 KEY BENEFITS
 *   • Easier to test — one class, one concern, one mock
 *   • Lower risk of regression — changing salary logic can't break the report
 *   • Clearer ownership — every class has one team/story responsible for it
 *
 * ─────────────────────────────────────────────────────────────────────────────────
 * ❓ Common Interview Questions
 *   "What is SRP and why does it matter?"
 *   "How would you refactor a God class?"
 * ─────────────────────────────────────────────────────────────────────────────────
 * </pre>
 */
public class SRPDemo {

    public static void main(String[] args) {
        Employee emp = new Employee(1, "Alice", 50000);

        // All three concerns crammed into one class ❌
        double salary = emp.calculateSalary();
        System.out.println("Salary: " + salary);

        emp.saveToDatabase();    // persistence mixed into domain object ❌
        emp.generateReport();    // reporting mixed into domain object ❌
    }
}
