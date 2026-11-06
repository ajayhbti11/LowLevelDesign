package org.designpattern.creational.factory.withfactory;

/** Concrete product — email channel. */
public class EmailNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("[Email] " + message);
    }
}
