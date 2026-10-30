package org.designpattern.structural.decorator.withoutdecorator;

// Subclass for every single combination ❌
public class SimpleCoffeeWithMilk implements Coffee {
    @Override public String getDescription() { return "Simple Coffee + Milk"; }
    @Override public double getCost()        { return 60.0; }
}
