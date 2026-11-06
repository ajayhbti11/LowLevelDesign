package org.designpattern.creational.factory.withfactory;

/** Component interface — every notification channel implements this. */
public interface Notification {
    void send(String message);
}
