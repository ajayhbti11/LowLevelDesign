package org.designpattern.behavioral.observerdesign.stocknotify;

/** Sends an email when a product is back in stock (Amazon "Notify Me" style). */
public class EmailNotification implements NotificationObserver {

    private final String emailId;

    public EmailNotification(String emailId) {
        this.emailId = emailId;
    }

    @Override
    public void notifyUser(String productName, int availableStock) {
        System.out.println("[Email → " + emailId + "] \""
                + productName + "\" is back in stock! ("
                + availableStock + " units available). Shop now before it sells out.");
    }
}
