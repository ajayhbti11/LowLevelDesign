package org.designpattern.behavioral.observerdesign.stocknotify;

/** Subject — product inventory observers can subscribe to. */
public interface NotificationSubject {
    void subscribe(NotificationObserver observer);
    void unsubscribe(NotificationObserver observer);
    void notifyObservers();
}
