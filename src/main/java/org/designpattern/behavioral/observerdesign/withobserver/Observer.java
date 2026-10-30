package org.designpattern.behavioral.observerdesign.withobserver;

/**
 * Observer interface — any display that wants temperature updates implements this.
 */
public interface Observer {
    void update(int temperature);
}
