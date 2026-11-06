package org.designpattern.creational.abstractfactory.withabstractfactory;

/** Abstract product C — every theme must provide a TextField. */
public interface TextField {
    void render();
    void onInput(String text);
}
