package org.designpattern.creational.abstractfactory.withabstractfactory;

/** Abstract product B — every theme must provide a CheckBox. */
public interface CheckBox {
    void render();
    void toggle();
}
