package org.designpattern.behavioral.observerdesign.withobserver;

public class MobileDisplay implements Observer {

    @Override
    public void update(int temperature) {
        System.out.println("[Mobile Display] Temperature updated to " + temperature + "°C");
    }
}
