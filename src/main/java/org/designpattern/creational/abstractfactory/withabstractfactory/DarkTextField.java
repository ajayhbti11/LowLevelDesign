package org.designpattern.creational.abstractfactory.withabstractfactory;

/** Concrete product — Dark theme text field. */
public class DarkTextField implements TextField {
    @Override public void render()            { System.out.println("[Dark]  TextField → dark bg, white border"); }
    @Override public void onInput(String text){ System.out.println("[Dark]  TextField → input: " + text); }
}
