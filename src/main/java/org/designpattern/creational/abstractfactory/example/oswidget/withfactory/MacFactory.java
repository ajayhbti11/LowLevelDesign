package org.designpattern.creational.abstractfactory.example.oswidget.withfactory;

/** Concrete Factory — produces the Mac widget family. */
public class MacFactory implements OSFactory {
    @Override public Button   createButton()   { return new MacButton();   }
    @Override public CheckBox createCheckBox() { return new MacCheckBox(); }
}
