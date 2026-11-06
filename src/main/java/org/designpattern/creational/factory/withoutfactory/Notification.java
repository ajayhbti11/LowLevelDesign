package org.designpattern.creational.factory.withoutfactory;

/** Common interface every notification channel must implement. */
public interface Notification {
    void send(String message);
}
