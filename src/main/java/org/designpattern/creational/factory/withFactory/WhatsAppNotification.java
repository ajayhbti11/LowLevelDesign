package org.designpattern.creational.factory.withfactory;

/**
 * Concrete product — WhatsApp channel.
 * ✅ Adding this required ZERO changes to any caller.
 * Only the factory needed updating.
 */
public class WhatsAppNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("[WhatsApp] " + message);
    }
}
