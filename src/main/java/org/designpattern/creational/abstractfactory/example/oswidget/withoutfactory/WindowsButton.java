package org.designpattern.creational.abstractfactory.example.oswidget.withoutfactory;

public class WindowsButton implements Button {
    @Override
    public void render() { System.out.println("Rendering Windows button"); }
}
