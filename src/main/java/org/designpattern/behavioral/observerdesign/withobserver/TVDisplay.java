package org.designpattern.behavioral.observerdesign.withobserver;

/**
 * A brand-new display added WITHOUT touching WeatherStation. ✅
 */
public class TVDisplay implements Observer {

    @Override
    public void update(int temperature) {
        System.out.println("[TV Display]     Temperature updated to " + temperature + "°C");
    }
}
