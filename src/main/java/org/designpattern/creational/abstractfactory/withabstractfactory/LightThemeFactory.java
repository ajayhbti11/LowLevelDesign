package org.designpattern.creational.abstractfactory.withabstractfactory;

/**
 * Concrete Factory A — produces the entire Light theme family.
 * Guarantees that all created widgets belong to the same family. ✅
 */
public class LightThemeFactory implements UIFactory {
    @Override public Button    createButton()    { return new LightButton();    }
    @Override public CheckBox  createCheckBox()  { return new LightCheckBox();  }
    @Override public TextField createTextField() { return new LightTextField(); }
}
