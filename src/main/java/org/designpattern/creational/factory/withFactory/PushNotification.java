package org.designpattern.creational.factory.withfactory;

/** Concrete product — push channel. */
public class PushNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("[Push] " + message);
    }
}
