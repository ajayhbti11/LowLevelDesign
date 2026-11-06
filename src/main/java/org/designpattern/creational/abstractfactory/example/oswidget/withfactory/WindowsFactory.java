package org.designpattern.creational.abstractfactory.example.oswidget.withfactory;

/** Concrete Factory — produces the Windows widget family. */
public class WindowsFactory implements OSFactory {
    @Override public Button   createButton()   { return new WindowsButton();   }
    @Override public CheckBox createCheckBox() { return new WindowsCheckBox(); }
}
