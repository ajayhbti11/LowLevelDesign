package org.solidprinciple.srp.withsrp;

/**
 * ✅ Demo — WITH Single Responsibility Principle
 *
 * <pre>
 * ─────────────────────────────────────────────────────────────────────────────────
 * S — SINGLE RESPONSIBILITY PRINCIPLE (SRP)
 *
 * "A class should have only one reason to change."
 *
 * ─────────────────────────────────────────────────────────────────────────────────
 * ✅ Solution (this demo)
 *   Employee           — pure data carrier (fields + getters), one reason to change:
 *                        the employee data model
 *   SalaryCalculator   — one reason to change: salary formula
 *   EmployeeRepository — one reason to change: DB schema / persistence tech
 *   EmployeeReport     — one reason to change: report format / output channel
 *
 * ─────────────────────────────────────────────────────────────────────────────────
 * 📦 REAL-WORLD USE CASES
 *
 * 1. E-commerce Order
 *       OrderPricer | OrderRepository | OrderNotifier
 *
 * 2. User Management
 *       PasswordService | MailService | UserRepository
 *
 * 3. Invoice System
 *       InvoiceCalculator | InvoicePrinter | InvoiceExporter (CSV/PDF)
 *
 * 4. Logging Pipeline
 *       LogFormatter | FileLogWriter | CloudLogWriter
 *
 * 5. Payment Processing
 *       PaymentValidator | PaymentGatewayClient | PaymentAuditLogger
 *
 * ─────────────────────────────────────────────────────────────────────────────────
 * 🧠 KEY BENEFITS
 *   • Each class has one focused test suite
 *   • Changing the DB never risks the salary formula
 *   • New report format = new class, existing classes untouched
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

        // Each concern handled by its own dedicated class ✅
        SalaryCalculator calculator = new SalaryCalculator();
        double salary = calculator.calculateSalary(emp);
        System.out.println("Salary: " + salary);

        EmployeeRepository repository = new EmployeeRepository();
        repository.saveToDatabase(emp);    // persistence is EmployeeRepository's job only ✅

        EmployeeReport report = new EmployeeReport();
        report.generateReport(emp);        // reporting is EmployeeReport's job only ✅
    }
}
