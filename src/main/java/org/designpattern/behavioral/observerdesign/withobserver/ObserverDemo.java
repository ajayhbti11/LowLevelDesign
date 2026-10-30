package org.designpattern.behavioral.observerdesign.withobserver;

/**
 * ✅ Demo — WITH Observer Pattern
 *
 * <pre>
 * ─────────────────────────────────────────────────────────────────────────────────
 * ✅ OBSERVER DESIGN PATTERN
 *
 * When one object (Subject) changes state, all its dependents (Observers)
 * are notified and updated automatically.
 * Like YouTube — you subscribe once, and every new video notifies you.
 *
 * ─────────────────────────────────────────────────────────────────────────────────
 * 📦 REAL-WORLD USE CASES
 *
 * 1. Weather / IoT Sensor Updates  (this example)
 *       WeatherStation → LedDisplay, MobileDisplay, TVDisplay, SmartWatch
 *
 * 2. Event-Driven Systems / Kafka
 *       Producer publishes an event → multiple consumers react independently.
 *
 * 3. UI Frameworks (MVC)
 *       Model changes → View auto-refreshes. Used in Android LiveData,
 *       JavaFX properties, React state.
 *
 * 4. Stock Price Tracker
 *       StockMarket → EmailAlert, SMSAlert, DashboardWidget notified on price change.
 *
 * 5. Notification Systems
 *       OrderService → EmailNotifier, SMSNotifier, PushNotifier
 *       One order event fans out to all channels.
 *
 * 6. Logger / Audit Trail
 *       Service emits events → FileLogger, DBLogger, CloudLogger each react.
 *
 * 7. Social Media Feed
 *       User posts → all followers' feeds updated automatically.
 *
 * 8. Multiplayer Game State
 *       GameEngine broadcasts state change → all player clients re-render.
 *
 * ─────────────────────────────────────────────────────────────────────────────────
 * 🧠 KEY BENEFITS
 *   • Loose coupling — Subject knows only the Observer interface, not concrete classes
 *   • Open/Closed — add a new observer (TVDisplay) without touching WeatherStation
 *   • Dynamic subscription — observers can register / unregister at runtime
 *   • One-to-many — one event notifies unlimited observers
 *
 * ─────────────────────────────────────────────────────────────────────────────────
 * ❓ Common Interview Questions
 *   "Design a notification system (Email, SMS, Push)"
 *   "How would you implement an event-driven stock price alert system?"
 * ─────────────────────────────────────────────────────────────────────────────────
 * </pre>
 */
public class ObserverDemo {

    public static void main(String[] args) {
        WeatherStation station = new WeatherStation();

        Observer mobile = new MobileDisplay();
        Observer led    = new LedDisplay();
        Observer tv     = new TVDisplay();   // new display — WeatherStation unchanged ✅

        station.addObserver(mobile);
        station.addObserver(led);
        station.addObserver(tv);

        System.out.println("--- Temperature set to 25°C ---");
        station.setTemperature(25);

        System.out.println("\n--- Mobile unsubscribed ---");
        station.removeObserver(mobile);     // dynamic unsubscribe ✅

        System.out.println("--- Temperature set to 40°C ---");
        station.setTemperature(40);
    }
}
