package org.designpattern.structural.decorator.withoutdecorator;

// One more combination = one more class ❌
public class SimpleCoffeeWithMilkAndSugar implements Coffee {
    @Override public String getDescription() { return "Simple Coffee + Milk + Sugar"; }
    @Override public double getCost()        { return 65.0; }
}
