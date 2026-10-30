package org.designpattern.structural.decorator.withdecorator;

/**
 * ✅ Demo — WITH Decorator Pattern
 *
 * <pre>
 * ─────────────────────────────────────────────────────────────────────────────
 * DECORATOR DESIGN PATTERN
 *
 * Attach additional behaviour to objects dynamically at runtime,
 * without modifying their class or creating a subclass for every combination.
 *
 * ─────────────────────────────────────────────────────────────────────────────
 * ✅ Solution (this demo)
 *   Coffee            — component interface
 *   SimpleCoffee      — base concrete component
 *   CoffeeDecorator   — abstract decorator, wraps any Coffee
 *   MildDecorator     — adds milk  (+₹10)
 *   SugarDecorator    — adds sugar (+₹5)
 *   WhippedCreamDecorator — adds whip (+₹15)
 *
 *   Any combination at runtime — no new classes needed. ✅
 *   n add-ons = n decorator classes (not 2^n subclasses).
 *
 * ─────────────────────────────────────────────────────────────────────────────
 * 📦 REAL-WORLD USE CASES
 *
 * 1. Coffee / Pizza / Burger customisation (this example)
 * 2. Java I/O — new BufferedReader(new FileReader(file))
 * 3. Spring Security filter chain — each filter decorates the next
 * 4. HTTP middleware — logging → auth → rate-limit wrap the core handler
 * 5. Notification channels — SMS + Email + Push layered on base notifier
 *
 * ─────────────────────────────────────────────────────────────────────────────
 * ❓ Common Interview Questions
 *   "Design a pizza/burger system with dynamic toppings"
 *   "How does Java I/O use the Decorator pattern?"
 * ─────────────────────────────────────────────────────────────────────────────
 * </pre>
 */
public class DecoratorDemo {

    public static void main(String[] args) {

        // Plain coffee
        Coffee coffee = new SimpleCoffee();
        print(coffee);

        // Add milk — wrap at runtime, no new class ✅
        coffee = new MildDecorator(coffee);
        print(coffee);

        // Add sugar on top of milk
        coffee = new SugarDecorator(coffee);
        print(coffee);

        // Add whipped cream — still the same Coffee reference ✅
        coffee = new WhippedCreamDecorator(coffee);
        print(coffee);

        // Different order, different combo — zero new classes ✅
        System.out.println("\n--- Sugar only ---");
        Coffee sugarOnly = new SugarDecorator(new SimpleCoffee());
        print(sugarOnly);

        System.out.println("\n--- Whip + Milk (no sugar) ---");
        Coffee whipMilk = new WhippedCreamDecorator(new MildDecorator(new SimpleCoffee()));
        print(whipMilk);
    }

    static void print(Coffee c) {
        System.out.printf("%-50s ₹%.1f%n", c.getDescription(), c.getCost());
    }
}
