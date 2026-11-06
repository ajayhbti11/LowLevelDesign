package org.designpattern.creational.abstractfactory.example.oswidget.withfactory;

/**
 * ✅ Demo — WITH Abstract Factory Pattern (OS widget example)
 *
 * Client depends only on OSFactory, Button, CheckBox interfaces.
 * Switching OS = swap the factory. Client code never changes. ✅
 * Family consistency is guaranteed — impossible to mix Windows + Mac widgets. ✅
 */
public class AbstractFactoryDemo {

    public static void main(String[] args) {
        String osType = "windows";

        // ✅ One line to pick the family — client never references concrete classes
        OSFactory factory = osType.equalsIgnoreCase("windows")
                ? new WindowsFactory()
                : new MacFactory();

        Button   btn = factory.createButton();    // ✅ always from the same family
        CheckBox cb  = factory.createCheckBox();  // ✅ always from the same family

        btn.render();
        cb.render();
    }
}
