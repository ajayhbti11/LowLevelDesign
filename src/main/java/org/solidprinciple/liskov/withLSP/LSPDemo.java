package org.solidprinciple.liskov.withLSP;

/**
 * ✅ Demo — WITH Liskov Substitution Principle
 *
 * <pre>
 * ─────────────────────────────────────────────────────────────────────────────────
 * L — LISKOV SUBSTITUTION PRINCIPLE (LSP)
 *
 * "Objects of a subclass should be replaceable with objects of their superclass
 *  without altering the correctness of the program."
 *
 * ─────────────────────────────────────────────────────────────────────────────────
 * ✅ Solution (this demo)
 *   Shape interface defines only getArea() — the one behaviour both share.
 *   Rectangle and Square implement Shape independently.
 *   Neither inherits from the other, so no broken assumptions exist.
 *   Any code that works with Shape works correctly with both. ✅
 *
 * ─────────────────────────────────────────────────────────────────────────────────
 * 📦 REAL-WORLD USE CASES
 *
 * 1. Bird hierarchy
 *       FlyingBird (sparrow, eagle) and NonFlyingBird (penguin, ostrich)
 *       both extend Bird — fly() only on FlyingBird, no UnsupportedOperationException
 *
 * 2. Read-only vs mutable collections
 *       ReadOnlyList and MutableList both implement List interface correctly
 *       — callers can substitute either without surprises
 *
 * 3. Bank accounts
 *       SavingsAccount and FixedDeposit both implement BankAccount
 *       — only shared behaviour in the interface; no forced throws
 *
 * 4. HTTP clients (testing)
 *       RealHttpClient and MockHttpClient both implement HttpClient
 *       — tests substitute mock freely, production uses real one
 *
 * ─────────────────────────────────────────────────────────────────────────────────
 * 🧠 KEY BENEFITS
 *   • Polymorphism is trustworthy — no runtime exceptions from subtypes
 *   • Collections of Shape type work safely with any implementation
 *   • Honest IS-A relationships: Square IS-A Shape, not IS-A Rectangle
 *
 * ─────────────────────────────────────────────────────────────────────────────────
 * ❓ Common Interview Questions
 *   "Why can't Square extend Rectangle in a well-designed OOP system?"
 *   "What is LSP and how does it relate to polymorphism?"
 * ─────────────────────────────────────────────────────────────────────────────────
 * </pre>
 */
public class LSPDemo {

    /**
     * Works with any Shape — substitution is safe and correct. ✅
     */
    static void printArea(Shape shape) {
        System.out.println("Area: " + shape.getArea());
    }

    public static void main(String[] args) {
        Shape rect   = new Rectangle(4, 5);   // area = 20
        Shape square = new Square(4);          // area = 16

        // Both substitutable — no broken behaviour ✅
        printArea(rect);
        printArea(square);
    }
}
