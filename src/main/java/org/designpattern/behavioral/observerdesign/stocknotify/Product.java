package org.designpattern.behavioral.observerdesign.stocknotify;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Thread-safe product subject. Notifies subscribers only on 0 → in-stock transition.
 *
 * Concurrency choices:
 *   CopyOnWriteArrayList — safe concurrent subscribe/unsubscribe during iteration.
 *   AtomicInteger        — lock-free stock arithmetic.
 *   compareAndSet(0, qty) in addStock — ensures exactly one notification fires on restock.
 *   CAS loop in removeStock — prevents stock going below 0 under concurrent purchases.
 */
public class Product implements NotificationSubject {

    private final String name;

    // AtomicInteger — lock-free, no lost updates under concurrent addStock/removeStock
    private final AtomicInteger stock;

    // CopyOnWriteArrayList — no ConcurrentModificationException during iteration
    private final List<NotificationObserver> observers = new CopyOnWriteArrayList<>();

    public Product(String name, int initialStock) {
        this.name  = name;
        this.stock = new AtomicInteger(initialStock);
    }

    // ── Subscription management ──────────────────────────────────────────────

    @Override
    public void subscribe(NotificationObserver observer) {
        observers.add(observer);
        System.out.println("[" + name + "] New subscriber added. Total: " + observers.size());
    }

    @Override
    public void unsubscribe(NotificationObserver observer) {
        observers.remove(observer);
        System.out.println("[" + name + "] Subscriber removed. Total: " + observers.size());
    }

    @Override
    public void notifyObservers() {
        // snapshot stock value so all observers see the same count
        int currentStock = stock.get();
        System.out.println("[" + name + "] Notifying " + observers.size() + " subscriber(s)...");
        for (NotificationObserver observer : observers) {
            observer.notifyUser(name, currentStock);
        }
    }

    // ── Inventory management ─────────────────────────────────────────────────

    /** Adds stock. Notifies observers only on 0 → positive transition. */
    public void addStock(int quantity) {
        if (quantity <= 0) return;

        // CAS wins for exactly one thread — guarantees single notification on restock
        boolean wasOutOfStock = stock.compareAndSet(0, quantity);

        if (wasOutOfStock) {
            System.out.println("\n[" + name + "] Stock updated → " + stock.get() + " units available.");
            notifyObservers();
        } else {
            int updated = stock.addAndGet(quantity);
            System.out.println("\n[" + name + "] Stock updated → " + updated + " units available (already in stock, no notification).");
        }
    }

    /** Deducts stock atomically. CAS loop prevents underflow below 0. */
    public void removeStock(int quantity) {
        // CAS loop — prevents underflow under concurrent purchases
        int current, updated;
        do {
            current = stock.get();
            updated = Math.max(0, current - quantity);
        } while (!stock.compareAndSet(current, updated));

        System.out.println("[" + name + "] " + quantity + " units sold. Remaining: " + stock.get());
    }

    public boolean isInStock() { return stock.get() > 0; }
    public int     getStock()  { return stock.get(); }
    public String  getName()   { return name; }
}
