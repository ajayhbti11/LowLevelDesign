package org.designpattern.structural.decorator.withoutdecorator;

/**
 * ❌ Demo — WITHOUT Decorator Pattern
 *
 * <pre>
 * ─────────────────────────────────────────────────────────────────────────────
 * DECORATOR DESIGN PATTERN
 *
 * Attach additional behaviour to objects dynamically at runtime,
 * without modifying their class or creating a subclass for every combination.
 *
 * ─────────────────────────────────────────────────────────────────────────────
 * ❌ Problem (this demo) — Subclass explosion
 *
 *   Every add-on combination needs its own class:
 *     SimpleCoffee
 *     SimpleCoffeeWithMilk
 *     SimpleCoffeeWithSugar
 *     SimpleCoffeeWithMilkAndSugar
 *     SimpleCoffeeWithMilkAndSugarAndWhip  ... and so on
 *
 *   With 4 add-ons that's up to 2^4 = 16 classes for one base type.
 *   Add a second base (Espresso)? Multiply by 2 again. ❌
 *
 * ✅ Fix → see withdecorator package
 *   One abstract CoffeeDecorator wraps any Coffee.
 *   Each add-on is one small class. Combine at runtime — no new classes.
 *
 * ─────────────────────────────────────────────────────────────────────────────
 * 📦 REAL-WORLD USE CASES
 *
 * 1. Coffee / Pizza / Burger customisation (this example)
 * 2. Java I/O — BufferedReader wraps FileReader wraps File
 * 3. Spring Security filter chain — each filter decorates the next
 * 4. HTTP middleware — logging, auth, rate-limit wrap the core handler
 * 5. Notification channels — SMS + Email + Push layered on base notifier
 *
 * ─────────────────────────────────────────────────────────────────────────────
 * ❓ Common Interview Questions
 *   "Design a pizza/burger system with dynamic toppings"
 *   "How does Java I/O use the Decorator pattern?"
 * ─────────────────────────────────────────────────────────────────────────────
 * </pre>
 */
public class WithoutDecoratorDemo {

    public static void main(String[] args) {
        // Every combination is a hard-coded class — not composable at runtime ❌
        Coffee plain    = new SimpleCoffee();
        Coffee withMilk = new SimpleCoffeeWithMilk();
        Coffee milkSugar = new SimpleCoffeeWithMilkAndSugar();
        Coffee fullHouse = new SimpleCoffeeWithMilkAndSugarAndWhip();

        print(plain);
        print(withMilk);
        print(milkSugar);
        print(fullHouse);

        // Want Espresso + Sugar + Whip? Write two more classes. ❌
    }

    static void print(Coffee c) {
        System.out.printf("%-45s ₹%.1f%n", c.getDescription(), c.getCost());
    }
}
