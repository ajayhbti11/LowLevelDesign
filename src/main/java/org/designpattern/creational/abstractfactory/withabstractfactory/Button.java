package org.designpattern.creational.abstractfactory.withabstractfactory;

/** Abstract product A — every theme must provide a Button. */
public interface Button {
    void render();
    void onClick();
}
