package org.designpattern.behavioral.observerdesign.withoutobserver;

/**
 * ❌ WITHOUT Observer Pattern
 *
 * <p>WeatherStation manually calls every display directly.
 * Adding a new display (e.g. TVDisplay) means editing this class — violates OCP.</p>
 */
public class WeatherStation {

    private int temperature;

    // Hard-coded references to every display — tight coupling
    private LedDisplay    ledDisplay    = new LedDisplay();
    private MobileDisplay mobileDisplay = new MobileDisplay();

    public void setTemperature(int temperature) {
        this.temperature = temperature;
        notifyDisplays();
    }

    /**
     * Every new display requires a new line here.
     * Cannot add/remove displays at runtime.
     */
    private void notifyDisplays() {
        ledDisplay.update(temperature);
        mobileDisplay.update(temperature);
        // Adding TVDisplay? Edit this method. ❌
    }
}
