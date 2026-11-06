package org.designpattern.creational.factory.withfactory;

/**
 * Factory — single place that knows HOW to create a {@link Notification}.
 *
 * <p>Callers ask for a {@code Notification} by type string; they never
 * reference a concrete class.  Adding a new channel (e.g. Slack) only
 * requires updating this class — all callers stay unchanged. ✅
 */
public class NotificationFactory {

    /**
     * Creates and returns the appropriate {@link Notification} instance.
     *
     * @param type channel identifier ("email", "sms", "push", "whatsapp")
     * @return a ready-to-use Notification
     * @throws IllegalArgumentException for an unrecognised type
     */
    public static Notification create(String type) {
        return switch (type.toLowerCase()) {
            case "email"    -> new EmailNotification();
            case "sms"      -> new SMSNotification();
            case "push"     -> new PushNotification();
            case "whatsapp" -> new WhatsAppNotification();   // ✅ new channel, zero caller changes
            default         -> throw new IllegalArgumentException("Unknown channel: " + type);
        };
    }
}
