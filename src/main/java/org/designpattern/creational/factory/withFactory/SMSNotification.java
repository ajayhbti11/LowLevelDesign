package org.designpattern.creational.factory.withfactory;

/** Concrete product — SMS channel. */
public class SMSNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("[SMS] " + message);
    }
}
