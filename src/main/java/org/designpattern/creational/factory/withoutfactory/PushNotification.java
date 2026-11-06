package org.designpattern.creational.factory.withoutfactory;

public class PushNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("[Push] " + message);
    }
}
