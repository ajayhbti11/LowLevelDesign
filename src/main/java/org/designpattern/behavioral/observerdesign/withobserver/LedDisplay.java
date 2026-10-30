package org.designpattern.behavioral.observerdesign.withobserver;

public class LedDisplay implements Observer {

    @Override
    public void update(int temperature) {
        System.out.println("[LED Display] Temperature updated to " + temperature + "°C");
    }
}
