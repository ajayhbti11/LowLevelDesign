package org.designpattern.structural.decorator.withoutdecorator;

/** Component interface — same contract with and without the pattern. */
public interface Coffee {
    String getDescription();
    double getCost();
}
