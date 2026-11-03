package org.basicoops.oops.polymorphism;

public class EmailNotificationService implements NotificationService{

    @Override
    public void sendNotification() {
        System.out.println("Email sent successfully ");
    }

}
