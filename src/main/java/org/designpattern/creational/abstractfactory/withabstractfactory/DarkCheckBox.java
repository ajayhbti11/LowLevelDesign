package org.designpattern.creational.abstractfactory.withabstractfactory;

/** Concrete product — Dark theme checkbox. */
public class DarkCheckBox implements CheckBox {
    @Override public void render() { System.out.println("[Dark]  CheckBox → dark bg, white border"); }
    @Override public void toggle() { System.out.println("[Dark]  CheckBox → toggled (white tick)"); }
}
