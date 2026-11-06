package org.designpattern.creational.abstractfactory.withabstractfactory;

/**
 * Concrete Factory B — produces the entire Dark theme family.
 * Guarantees that all created widgets belong to the same family. ✅
 */
public class DarkThemeFactory implements UIFactory {
    @Override public Button    createButton()    { return new DarkButton();    }
    @Override public CheckBox  createCheckBox()  { return new DarkCheckBox();  }
    @Override public TextField createTextField() { return new DarkTextField(); }
}
