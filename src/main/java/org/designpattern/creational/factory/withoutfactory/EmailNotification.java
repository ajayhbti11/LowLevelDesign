package org.designpattern.creational.factory.withoutfactory;

public class EmailNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("[Email] " + message);
    }
}
