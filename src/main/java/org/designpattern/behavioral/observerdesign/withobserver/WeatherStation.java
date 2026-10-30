package org.designpattern.behavioral.observerdesign.withobserver;

import java.util.ArrayList;
import java.util.List;

/**
 * ✅ Concrete Subject — knows nothing about specific displays.
 *
 * <p>Observers register themselves; WeatherStation just iterates the list.
 * Adding a new display never requires touching this class.</p>
 */
public class WeatherStation implements Subject {

    private final List<Observer> observers = new ArrayList<>();
    private int temperature;

    public void setTemperature(int temperature) {
        this.temperature = temperature;
        notifyObservers();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature);
        }
    }
}
