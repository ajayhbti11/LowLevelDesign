package org.designpattern.behavioral.observerdesign.stocknotify;

/** Sends a push notification when a product is back in stock (Amazon/Flipkart app style). */
public class AppNotification implements NotificationObserver {

    private final String userId;

    public AppNotification(String userId) {
        this.userId = userId;
    }

    @Override
    public void notifyUser(String productName, int availableStock) {
        System.out.println("[App Push → " + userId + "] \uD83D\uDD14 \""
                + productName + "\" is back! Only " + availableStock
                + " left in stock. Tap to order.");
    }
}
