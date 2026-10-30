package org.designpattern.behavioral.observerdesign.withobserver;

/**
 * Subject interface — any observable source of events implements this.
 */
public interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
