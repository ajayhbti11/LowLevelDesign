package org.designpattern.creational.abstractfactory.withabstractfactory;

/**
 * ✅ Demo — WITH Abstract Factory Pattern
 *
 * <pre>
 * ─────────────────────────────────────────────────────────────────────────────
 * ABSTRACT FACTORY DESIGN PATTERN
 *
 * Provide an interface for creating FAMILIES of related or dependent objects
 * without specifying their concrete classes.
 *
 * Think of it as a "Factory of Factories" — each concrete factory produces
 * a complete, consistent product family.
 *
 * ─────────────────────────────────────────────────────────────────────────────
 * Structure (this demo — UI theming system)
 *
 *   Abstract Products
 *   ├─ Button        — interface
 *   ├─ CheckBox      — interface
 *   └─ TextField     — interface
 *
 *   Concrete Products — Light family
 *   ├─ LightButton, LightCheckBox, LightTextField
 *
 *   Concrete Products — Dark family
 *   ├─ DarkButton, DarkCheckBox, DarkTextField
 *
 *   UIFactory (Abstract Factory interface)
 *   ├─ LightThemeFactory  — creates the whole Light family ✅
 *   └─ DarkThemeFactory   — creates the whole Dark family  ✅
 *
 *   Application (Client)
 *   └─ Depends only on UIFactory + product interfaces.
 *      Has NO knowledge of Light* or Dark* classes. ✅
 *
 * ─────────────────────────────────────────────────────────────────────────────
 * ✅ Benefits over plain Factory
 *   - Guarantees a CONSISTENT family — impossible to mix LightButton + DarkCheckBox.
 *   - Adding a new theme (HighContrast) = add 3 product classes + 1 factory.
 *     The Application (client) and all callers stay unchanged. ✅
 *   - Open/Closed Principle: open for extension (new families), closed for modification.
 *   - Dependency Inversion: client depends on abstractions, not concretions.
 *
 * ─────────────────────────────────────────────────────────────────────────────
 * ❓ Factory vs Abstract Factory — quick rule
 *   Factory         → one product type, one family
 *                     e.g. NotificationFactory.create("sms")
 *   Abstract Factory → MULTIPLE product types, MULTIPLE families
 *                     e.g. UIFactory creates Button + CheckBox + TextField together
 *
 * ─────────────────────────────────────────────────────────────────────────────
 * 📦 Real-world examples
 *   1. UI theming / skinning systems (this demo)
 *   2. javax.xml.parsers.DocumentBuilderFactory (SAX vs DOM parsers)
 *   3. java.sql.Connection — getStatement(), getPreparedStatement(), …
 *   4. Spring — ApplicationContext creates beans as a consistent family
 *   5. Cross-platform UI: Windows vs macOS vs Linux widgets
 *   6. Cloud SDK: AwsFactory vs AzureFactory — each creates Storage, Queue, DB
 *
 * ─────────────────────────────────────────────────────────────────────────────
 * ❓ Common Interview Questions
 *   "How would you support multiple UI themes without changing the client?"
 *   "What is the difference between Factory Method and Abstract Factory?"
 *   "How do you add a new family (HighContrast) without touching existing code?"
 * ─────────────────────────────────────────────────────────────────────────────
 * </pre>
 */
public class AbstractFactoryDemo {

    public static void main(String[] args) {

        // ── Decide the family at runtime (config / user preference) ──────────
        String theme = "dark";  // swap to "light" — Application code never changes ✅

        UIFactory factory = resolveFactory(theme);

        // ── Client depends ONLY on UIFactory and product interfaces ──────────
        Application app = new Application(factory);  // ✅ no Light/Dark concrete ref

        System.out.println("=== Rendering UI (" + theme + " theme) ===");
        app.renderUI();

        System.out.println("\n=== Simulating interactions ===");
        app.simulateInteractions();

        // ── Switch to light theme — zero Application changes ✅ ─────────────
        System.out.println("\n=== Switching to light theme ===");
        Application lightApp = new Application(new LightThemeFactory());
        lightApp.renderUI();
    }

    /**
     * The one place that maps a string → factory.
     * In production this is typically a config value or DI container.
     */
    private static UIFactory resolveFactory(String theme) {
        return switch (theme.toLowerCase()) {
            case "light" -> new LightThemeFactory();
            case "dark"  -> new DarkThemeFactory();
            default      -> throw new IllegalArgumentException("Unknown theme: " + theme);
        };
    }
}
