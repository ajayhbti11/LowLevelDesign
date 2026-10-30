package org.designpattern.behavioral.observerdesign.stocknotify;

/**
 * Demo — "Notify Me When Available" (Amazon / Flipkart style).
 *
 * Product is out of stock → users subscribe → seller restocks → all notified.
 * Notification fires only on 0 → in-stock transition, never on subsequent updates.
 */
public class StockNotifyDemo {

    public static void main(String[] args) {

        // out of stock
        Product iPhone = new Product("iPhone 15 Pro Max", 0);

        // users click "Notify Me"
        NotificationObserver alice = new EmailNotification("alice@gmail.com");
        NotificationObserver bob   = new SMSNotification("+91-9876543210");
        NotificationObserver carol = new AppNotification("user_carol_99");
        NotificationObserver dave  = new AppNotification("user_dave_42");

        iPhone.subscribe(alice);
        iPhone.subscribe(bob);
        iPhone.subscribe(carol);
        iPhone.subscribe(dave);

        // dave unsubscribes
        iPhone.unsubscribe(dave);

        // restock — alice, bob, carol notified; dave is not
        System.out.println("\n=== Seller adds 50 units ===");
        iPhone.addStock(50);

        // already in stock — no notification
        System.out.println("\n=== Seller adds 20 more units ===");
        iPhone.addStock(20);

        // sold out
        System.out.println("\n=== Flash sale ===");
        iPhone.removeStock(70);

        // new subscriber while out of stock
        NotificationObserver eve = new EmailNotification("eve@example.com");
        iPhone.subscribe(eve);

        // restock — alice, bob, carol, eve all notified
        System.out.println("\n=== New shipment ===");
        iPhone.addStock(10);
    }
}
