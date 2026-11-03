package org.basicoops.oops.polymorphism;

public class PolymorphismDemo {

    public static void main(String[] args) {

        NotificationService smsService=new SMSNotificationService();

        smsService.sendNotification();

        NotificationService emailService=new EmailNotificationService();

        emailService.sendNotification();

    }
}
