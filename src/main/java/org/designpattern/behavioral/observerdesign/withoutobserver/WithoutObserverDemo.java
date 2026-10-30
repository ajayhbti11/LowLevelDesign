package org.designpattern.behavioral.observerdesign.withoutobserver;

/**
 * ❌ Demo — WITHOUT Observer Pattern
 *
 * <pre>
 * Problem
 * -------
 *  • WeatherStation holds hard-coded references to LedDisplay and MobileDisplay.
 *  • Adding a new display (TVDisplay, SmartWatch) requires editing WeatherStation.
 *  • Cannot add or remove displays at runtime.
 *  • WeatherStation and displays are tightly coupled — impossible to test in isolation.
 *
 * Fix → see withobserver package
 *  • Observer        : common interface — update(int temperature)
 *  • Subject         : interface for subscribe / unsubscribe / notify
 *  • WeatherStation  : implements Subject, notifies all registered observers
 *  • LedDisplay / MobileDisplay / TVDisplay : implement Observer, register themselves
 * </pre>
 */
public class WithoutObserverDemo {

    public static void main(String[] args) {
        WeatherStation station = new WeatherStation();

        // Works, but displays are fixed inside WeatherStation
        station.setTemperature(25);
        station.setTemperature(40);

        // Can't add TVDisplay at runtime without modifying WeatherStation ❌
    }
}
