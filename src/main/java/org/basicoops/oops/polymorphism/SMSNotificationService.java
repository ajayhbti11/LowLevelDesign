package org.basicoops.oops.polymorphism;

public class SMSNotificationService implements NotificationService{

    @Override
    public void sendNotification() {
        System.out.println("SMS sent successfully ");
    }


}
