package org.designpattern.creational.abstractfactory.example.oswidget.withoutfactory;

/**
 * ❌ Demo — WITHOUT Abstract Factory Pattern (OS widget example)
 *
 * Caller hard-codes concrete classes per OS.
 * Easy to accidentally mix WindowsButton with MacCheckBox.
 * Adding a Linux theme forces edits in every caller.
 */
public class WithoutAbstractFactoryDemo {

    public static void main(String[] args) {
        String osType = "windows";

        // ❌ Caller coupled to every concrete class
        Button   btn;
        CheckBox cb;

        if (osType.equalsIgnoreCase("windows")) {
            btn = new WindowsButton();    // ❌ tight coupling
            cb  = new WindowsCheckBox();  // ❌ tight coupling
        } else {
            btn = new MacButton();        // ❌ tight coupling
            cb  = new MacCheckBox();      // ❌ tight coupling
            // Nothing stops: new WindowsButton() + new MacCheckBox() ← inconsistent family ❌
        }

        btn.render();
        cb.render();
    }
}
