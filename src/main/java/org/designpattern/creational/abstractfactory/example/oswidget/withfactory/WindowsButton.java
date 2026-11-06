package org.designpattern.creational.abstractfactory.example.oswidget.withfactory;

public class WindowsButton implements Button {
    @Override
    public void render() { System.out.println("Rendering Windows button"); }
}
