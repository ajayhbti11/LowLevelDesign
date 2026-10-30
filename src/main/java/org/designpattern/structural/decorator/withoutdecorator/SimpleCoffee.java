package org.designpattern.structural.decorator.withoutdecorator;

public class SimpleCoffee implements Coffee {
    @Override public String getDescription() { return "Simple Coffee"; }
    @Override public double getCost()        { return 50.0; }
}
