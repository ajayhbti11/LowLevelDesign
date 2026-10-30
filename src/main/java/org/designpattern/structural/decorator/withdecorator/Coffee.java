package org.designpattern.structural.decorator.withdecorator;

/** Component interface — both base and decorators implement this. */
public interface Coffee {
    String getDescription();
    double getCost();
}
