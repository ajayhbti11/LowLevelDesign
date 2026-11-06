package org.designpattern.creational.abstractfactory.withabstractfactory;

/** Concrete product — Dark theme button. */
public class DarkButton implements Button {
    @Override public void render()  { System.out.println("[Dark]  Button   → dark bg, white text"); }
    @Override public void onClick() { System.out.println("[Dark]  Button   → clicked (light glow)"); }
}
