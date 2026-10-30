package org.designpattern.structural.decorator.withdecorator;

/** Base concrete component — plain coffee, no add-ons. */
public class SimpleCoffee implements Coffee {

    @Override
    public String getDescription() { return "Simple Coffee"; }

    @Override
    public double getCost() { return 50.0; }
}
