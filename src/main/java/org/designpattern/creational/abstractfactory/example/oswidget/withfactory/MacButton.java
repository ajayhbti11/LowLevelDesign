package org.designpattern.creational.abstractfactory.example.oswidget.withfactory;

public class MacButton implements Button {
    @Override
    public void render() { System.out.println("Rendering Mac button"); }
}
