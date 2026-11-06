package org.designpattern.creational.abstractfactory.withabstractfactory;

/** Concrete product — Light theme checkbox. */
public class LightCheckBox implements CheckBox {
    @Override public void render() { System.out.println("[Light] CheckBox → white bg, dark border"); }
    @Override public void toggle() { System.out.println("[Light] CheckBox → toggled (dark tick)"); }
}
