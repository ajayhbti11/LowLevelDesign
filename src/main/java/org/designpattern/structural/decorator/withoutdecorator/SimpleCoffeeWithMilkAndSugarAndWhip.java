package org.designpattern.structural.decorator.withoutdecorator;

// 3 add-ons = already the 4th class for this one base ❌
// n add-ons = 2^n subclasses needed — class explosion
public class SimpleCoffeeWithMilkAndSugarAndWhip implements Coffee {
    @Override public String getDescription() { return "Simple Coffee + Milk + Sugar + Whipped Cream"; }
    @Override public double getCost()        { return 80.0; }
}
