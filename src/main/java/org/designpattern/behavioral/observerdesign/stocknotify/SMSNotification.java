package org.designpattern.behavioral.observerdesign.stocknotify;

/** Sends an SMS when a product is back in stock (Flipkart "saved item available" style). */
public class SMSNotification implements NotificationObserver {

    private final String phoneNumber;

    public SMSNotification(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void notifyUser(String productName, int availableStock) {
        System.out.println("[SMS → " + phoneNumber + "] ALERT: \""
                + productName + "\" is available now! "
                + availableStock + " units left. Hurry!");
    }
}
