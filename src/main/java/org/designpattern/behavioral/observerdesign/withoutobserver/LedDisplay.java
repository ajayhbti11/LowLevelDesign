package org.designpattern.behavioral.observerdesign.withoutobserver;

public class LedDisplay {

    public void update(int temperature) {
        System.out.println("[LED Display] Temperature: " + temperature + "°C");
    }
}
