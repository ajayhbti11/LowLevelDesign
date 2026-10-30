package org.designpattern.behavioral.observerdesign.withoutobserver;

public class MobileDisplay {

    public void update(int temperature) {
        System.out.println("[Mobile Display] Temperature: " + temperature + "°C");
    }
}
