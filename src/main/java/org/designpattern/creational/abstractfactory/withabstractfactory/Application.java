package org.designpattern.creational.abstractfactory.withabstractfactory;

/**
 * Client — uses only the abstract factory and product interfaces.
 *
 * <p>It has NO idea whether it is rendering a Light or Dark theme.
 * The concrete family is injected via the constructor — the classic
 * Dependency Inversion Principle. ✅
 */
public class Application {

    private final Button    button;
    private final CheckBox  checkBox;
    private final TextField textField;

    /** Inject a factory; the Application never calls "new LightButton()" etc. */
    public Application(UIFactory factory) {
        this.button    = factory.createButton();
        this.checkBox  = factory.createCheckBox();
        this.textField = factory.createTextField();
    }

    /** Render the complete, consistent UI. */
    public void renderUI() {
        button.render();
        checkBox.render();
        textField.render();
    }

    /** Simulate user interactions. */
    public void simulateInteractions() {
        button.onClick();
        checkBox.toggle();
        textField.onInput("Hello Abstract Factory!");
    }
}
