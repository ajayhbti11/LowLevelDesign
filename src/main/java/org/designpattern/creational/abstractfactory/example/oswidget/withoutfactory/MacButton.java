package org.designpattern.creational.abstractfactory.example.oswidget.withoutfactory;

public class MacButton implements Button {
    @Override
    public void render() { System.out.println("Rendering Mac button"); }
}
