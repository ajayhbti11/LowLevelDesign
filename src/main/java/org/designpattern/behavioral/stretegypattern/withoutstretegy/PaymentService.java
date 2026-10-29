package org.designpattern.behavioral.stretegypattern;

/**
 * ❌ WITHOUT Strategy Pattern
 *
 * <p>All payment logic lives inside one class behind a growing if-else chain.
 * Adding a new payment method (e.g. "crypto") means editing this class — a direct
 * violation of the Open/Closed Principle.</p>
 *
 * <ul>
 *   <li>Hard to test each method in isolation.</li>
 *   <li>Every new method bloats the same file.</li>
 *   <li>Cannot swap behaviour at runtime without changing the class.</li>
 * </ul>
 *
 * @see org.designpattern.behavioral.stretegypattern.withstretegy.PaymentContext
 */
public class PaymentService {

    /**
     * Processes a payment using a hard-coded if-else dispatch.
     *
     * @param method one of {@code "credit"}, {@code "paypal"}, or {@code "upi"}
     * @param amount amount to charge in rupees / units
     */
    public void pay(String method, int amount) {
        if (method.equals("credit")) {
            // Credit-card logic hard-coded here
            System.out.println("Paid ₹" + amount + " using Credit Card");
        } else if (method.equals("paypal")) {
            // PayPal logic hard-coded here
            System.out.println("Paid ₹" + amount + " using PayPal");
        } else if (method.equals("upi")) {
            // UPI logic hard-coded here
            System.out.println("Paid ₹" + amount + " using UPI");
        } else {
            // Every new method requires yet another else-if block ⬆
            System.out.println("Unknown payment method: " + method);
        }
    }
}
