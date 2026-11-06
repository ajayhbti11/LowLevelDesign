package org.designpattern.creational.abstractfactory.withoutabstractfactory;

/**
 * ❌ Demo — WITHOUT Abstract Factory Pattern
 *
 * <pre>
 * ─────────────────────────────────────────────────────────────────────────────
 * PROBLEM
 *
 * We need to render a UI that is consistent for a given OS theme (Light / Dark).
 * Each theme has its own Button, CheckBox, and TextField look-and-feel.
 *
 * Without a factory, the client hard-codes "new LightButton()", "new DarkCheckBox()"
 * etc., resulting in:
 *   ❌ Inconsistent families — easy to mix a LightButton with a DarkCheckBox.
 *   ❌ Adding a "HighContrast" theme = edit every caller.
 *   ❌ Client coupled to 6+ concrete classes instead of 3 interfaces.
 *   ❌ Unit-testing the client requires real UI widgets (no mock injection).
 *
 * ─────────────────────────────────────────────────────────────────────────────
 * </pre>
 */
public class WithoutAbstractFactoryDemo {

    public static void main(String[] args) {

        String theme = "dark"; // imagine this comes from user preferences

        // ❌ Caller must know about every concrete class for every theme.
        //    Mixing families (e.g. LightButton + DarkCheckBox) is possible by mistake.

        if (theme.equals("light")) {
            LightButton   button   = new LightButton();    // ❌ coupled to concrete
            LightCheckBox checkBox = new LightCheckBox();  // ❌ coupled to concrete
            LightTextField textField = new LightTextField();

            button.render();
            checkBox.render();
            textField.render();

        } else if (theme.equals("dark")) {
            DarkButton   button   = new DarkButton();      // ❌ coupled to concrete
            DarkCheckBox checkBox = new DarkCheckBox();    // ❌ coupled to concrete
            DarkTextField textField = new DarkTextField();

            button.render();
            checkBox.render();
            textField.render();

            // ❌ Nothing stops someone from accidentally writing:
            //    new LightButton() + new DarkCheckBox()  ← inconsistent family
        }

        // ❌ Adding "HighContrast" theme = add 3 classes AND edit every caller.
    }

    // ── Inline concrete classes (normally separate files) ──────────────────

    static class LightButton    { void render() { System.out.println("[Light] Button rendered");    } }
    static class LightCheckBox  { void render() { System.out.println("[Light] CheckBox rendered");  } }
    static class LightTextField { void render() { System.out.println("[Light] TextField rendered"); } }

    static class DarkButton     { void render() { System.out.println("[Dark]  Button rendered");    } }
    static class DarkCheckBox   { void render() { System.out.println("[Dark]  CheckBox rendered");  } }
    static class DarkTextField  { void render() { System.out.println("[Dark]  TextField rendered"); } }
}
