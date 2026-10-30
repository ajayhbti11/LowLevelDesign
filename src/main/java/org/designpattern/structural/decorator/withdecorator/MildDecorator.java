package org.designpattern.structural.decorator.withdecorator;

/** Adds milk to any Coffee (+₹10). */
public class MildDecorator extends CoffeeDecorator {

    public MildDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + " + Milk";
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 10.0;
    }
}
