package org.designpattern.creational.factory.withoutfactory;

/**
 * ❌ Demo — WITHOUT Factory Pattern
 *
 * <pre>
 * ─────────────────────────────────────────────────────────────────────────────
 * PROBLEM
 *
 * The client (this class) knows about EVERY concrete notification class.
 * Every time a new channel is added (WhatsApp, Slack, …) the caller must
 * be changed — violating the Open/Closed Principle.
 *
 * The "new" keyword is scattered across all callers, making the codebase
 * hard to test (can't inject mocks) and hard to maintain.
 *
 * ─────────────────────────────────────────────────────────────────────────────
 * ❌ Problems in this demo
 *   - Caller depends on concrete classes, not on the interface.
 *   - Adding a new channel forces changes in every caller.
 *   - Object creation logic is duplicated everywhere.
 *   - Unit testing requires real objects (no easy substitution).
 * ─────────────────────────────────────────────────────────────────────────────
 * </pre>
 */
public class WithoutFactoryDemo {

    public static void main(String[] args) {

        String channelType = "email"; // imagine this comes from config / DB

        // ❌ Caller has to know about every concrete class
        Notification notification;

        if (channelType.equalsIgnoreCase("email")) {
            notification = new EmailNotification();       // ❌ tight coupling
        } else if (channelType.equalsIgnoreCase("sms")) {
            notification = new SMSNotification();         // ❌ tight coupling
        } else if (channelType.equalsIgnoreCase("push")) {
            notification = new PushNotification();        // ❌ tight coupling
        } else {
            throw new IllegalArgumentException("Unknown channel: " + channelType);
        }

        notification.send("Your order has been placed!");

        // ❌ If we add WhatsAppNotification tomorrow, we must edit this block
        //    AND every other place in the codebase that does the same switch.
    }
}
