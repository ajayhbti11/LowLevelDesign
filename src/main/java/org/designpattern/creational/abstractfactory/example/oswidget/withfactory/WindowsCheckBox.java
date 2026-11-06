package org.designpattern.creational.abstractfactory.example.oswidget.withfactory;

public class WindowsCheckBox implements CheckBox {
    @Override
    public void render() { System.out.println("Rendering Windows checkbox"); }
}
