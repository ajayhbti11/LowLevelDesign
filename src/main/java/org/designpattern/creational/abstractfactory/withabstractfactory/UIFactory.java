package org.designpattern.creational.abstractfactory.withabstractfactory;

/**
 * Abstract Factory — declares creation methods for every product
 * in a UI family.
 *
 * Each concrete factory (LightThemeFactory, DarkThemeFactory, …)
 * returns a complete, consistent set of widgets.
 */
public interface UIFactory {
    Button    createButton();
    CheckBox  createCheckBox();
    TextField createTextField();
}
