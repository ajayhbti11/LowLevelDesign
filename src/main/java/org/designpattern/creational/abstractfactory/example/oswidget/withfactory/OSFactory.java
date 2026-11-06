package org.designpattern.creational.abstractfactory.example.oswidget.withfactory;

/** Abstract Factory — declares creation methods for every OS widget. */
public interface OSFactory {
    Button   createButton();
    CheckBox createCheckBox();
}
