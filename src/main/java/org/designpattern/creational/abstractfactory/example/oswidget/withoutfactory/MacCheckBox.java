package org.designpattern.creational.abstractfactory.example.oswidget.withoutfactory;

public class MacCheckBox implements CheckBox {
    @Override
    public void render() { System.out.println("Rendering Mac checkbox"); }
}
