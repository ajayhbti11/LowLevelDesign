package org.designpattern.structural.decorator.withdecorator;

/** Adds whipped cream to any Coffee (+₹15). */
public class WhippedCreamDecorator extends CoffeeDecorator {

    public WhippedCreamDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + " + Whipped Cream";
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 15.0;
    }
}
