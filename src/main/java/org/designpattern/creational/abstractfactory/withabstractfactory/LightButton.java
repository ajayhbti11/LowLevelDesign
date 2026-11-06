package org.designpattern.creational.abstractfactory.withabstractfactory;

/** Concrete product — Light theme button. */
public class LightButton implements Button {
    @Override public void render()  { System.out.println("[Light] Button   → white bg, dark text"); }
    @Override public void onClick() { System.out.println("[Light] Button   → clicked (light ripple)"); }
}
