package org.solidprinciple.liskov.withOutLSP;

/**
 * ❌ Demo — WITHOUT Liskov Substitution Principle
 *
 * <pre>
 * ─────────────────────────────────────────────────────────────────────────────────
 * L — LISKOV SUBSTITUTION PRINCIPLE (LSP)
 *
 * "Objects of a subclass should be replaceable with objects of their superclass
 *  without altering the correctness of the program."
 *
 * ─────────────────────────────────────────────────────────────────────────────────
 * ❌ Problem (this demo)
 *   Square extends Rectangle but overrides setWidth/setHeight to keep both sides
 *   equal. When a method treats a Square as a Rectangle and sets dimensions
 *   independently, the result is wrong — substitution breaks correctness.
 *
 *   Expected: setWidth(4), setHeight(5) → area = 20
 *   Actual:   setHeight(5) also sets width to 5 → area = 25  ❌
 *
 * ✅ Fix → see withLSP package
 *   Both Rectangle and Square extend Shape independently.
 *   Neither inherits from the other — no broken assumptions.
 *
 * ─────────────────────────────────────────────────────────────────────────────────
 * 📦 REAL-WORLD USE CASES
 *
 * 1. Bird / Penguin hierarchy
 *       Bad:  Penguin extends Bird, Bird.fly() throws UnsupportedOperationException
 *       Good: FlyingBird and NonFlyingBird extend Bird separately
 *
 * 2. ReadOnlyList extending ArrayList
 *       Bad:  ReadOnlyList.add() throws exception — callers expecting ArrayList break
 *       Good: ReadOnlyList implements a read-only interface only
 *
 * 3. Bank accounts
 *       Bad:  FixedDeposit extends SavingsAccount, withdraw() throws exception
 *       Good: BankAccount base with only shared behaviour; subtypes add their own
 *
 * 4. HTTP Client override
 *       Bad:  MockHttpClient extends RealHttpClient, some methods crash
 *       Good: Both implement HttpClient interface — truly substitutable
 *
 * ─────────────────────────────────────────────────────────────────────────────────
 * 🧠 KEY BENEFITS
 *   • Polymorphism actually works — no surprise exceptions at runtime
 *   • Collections of base types behave predictably
 *   • Design stays honest about IS-A relationships
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
     * Expects any Rectangle — width and height should be independent.
     * Breaks when a Square is passed. ❌
     */
    static void printArea(Rectangle rect) {
        rect.setWidth(4);
        rect.setHeight(5);
        System.out.println("Expected area: 20, Actual area: " + rect.getArea());
    }

    public static void main(String[] args) {
        System.out.println("--- With Rectangle ---");
        printArea(new Rectangle());      // ✅ prints 20

        System.out.println("--- With Square (LSP violated) ---");
        printArea(new Square());         // ❌ prints 25 — Square is not substitutable
    }
}
