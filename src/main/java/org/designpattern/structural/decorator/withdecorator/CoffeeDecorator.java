package org.designpattern.structural.decorator.withdecorator;

/**
 * Abstract Decorator — wraps any Coffee and delegates by default.
 * Concrete decorators override only what they add.
 */
public abstract class CoffeeDecorator implements Coffee {

    protected final Coffee coffee;

    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public String getDescription() {
        return coffee.getDescription();
    }

    @Override
    public double getCost() {
        return coffee.getCost();
    }
}
