package org.designpattern.behavioral.observerdesign.stocknotify;

/** Observer — called when a product comes back in stock. */
public interface NotificationObserver {
    void notifyUser(String productName, int availableStock);
}
