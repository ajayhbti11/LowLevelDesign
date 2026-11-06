package org.designpattern.creational.abstractfactory.withabstractfactory;

/** Concrete product — Light theme text field. */
public class LightTextField implements TextField {
    @Override public void render()            { System.out.println("[Light] TextField → white bg, grey border"); }
    @Override public void onInput(String text){ System.out.println("[Light] TextField → input: " + text); }
}
