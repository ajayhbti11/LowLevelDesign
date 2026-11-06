# Abstract Factory Pattern

> **Intent:** Provide an interface for creating **families** of related objects  
> without specifying their concrete classes.  
> Think of it as a *factory of factories* — each concrete factory produces  
> a complete, consistent product family.

---

## The Problem (without abstract factory)

```
Client
  │
  ├─ if "dark"  → new DarkButton()      ❌ coupled to concrete
  │             → new DarkCheckBox()    ❌ coupled to concrete
  │             → new DarkTextField()   ❌ coupled to concrete
  │
  ├─ if "light" → new LightButton()     ❌ coupled to concrete
  │             → new LightCheckBox()   ❌ coupled to concrete
  │             → new LightTextField()  ❌ coupled to concrete
  │
  └─ Nothing stops:
       new LightButton() + new DarkCheckBox()  ← inconsistent family ❌
```

Adding a "HighContrast" theme → edit every caller. ❌  
Mixing families (Light button + Dark checkbox) is possible by mistake. ❌

---

## The Solution (with abstract factory)

```
Client (Application)
  │
  └─► UIFactory (interface)          ← depends only on abstraction ✅
          │
          ├─► LightThemeFactory  →  LightButton + LightCheckBox + LightTextField
          │
          └─► DarkThemeFactory   →  DarkButton  + DarkCheckBox  + DarkTextField

  Family consistency GUARANTEED — factory always returns matching widgets ✅
```

---

## Class Diagram

```
        «interface»                         «interface»
         UIFactory                           Button
   ┌─────────────────────┐            ┌─────────────┐
   │ + createButton()    │            │ + render()  │
   │ + createCheckBox()  │            │ + onClick() │
   │ + createTextField() │            └──────┬──────┘
   └──────────┬──────────┘                   │ implements
              │ implements          ┌─────────┴──────────┐
   ┌──────────┴──────────┐          │                    │
   ▼                     ▼      LightButton          DarkButton
LightTheme           DarkTheme
 Factory              Factory
   │                     │
   │ creates             │ creates
   ▼                     ▼
LightButton          DarkButton        «interface»
LightCheckBox        DarkCheckBox       CheckBox
LightTextField       DarkTextField    ┌──────────────┐
                                      │ + render()   │
                                      │ + toggle()   │
        Application (Client)          └──────┬───────┘
   ┌─────────────────────────────┐           │ implements
   │ - button:    Button         │  ┌────────┴────────┐
   │ - checkBox:  CheckBox       │  │                 │
   │ - textField: TextField      │ LightCheckBox  DarkCheckBox
   │                             │
   │ + Application(UIFactory)    │   «interface»
   │ + renderUI()                │    TextField
   │ + simulateInteractions()    │  ┌──────────────────┐
   └─────────────────────────────┘  │ + render()       │
                                    │ + onInput(text)  │
                                    └──────┬───────────┘
                                           │ implements
                                  ┌────────┴────────┐
                                  │                 │
                               LightTextField  DarkTextField
```

---

## Object Creation Flow

```
AbstractFactoryDemo          UIFactory            Application           Products
       │                       │                      │                    │
       │  resolveFactory("dark")                       │                    │
       │ ─────────────────────►│                      │                    │
       │  DarkThemeFactory     │                      │                    │
       │ ◄─────────────────────│                      │                    │
       │                       │                      │                    │
       │  new Application(factory)                    │                    │
       │ ─────────────────────────────────────────────►                    │
       │                       │  factory.createButton()                   │
       │                       │ ─────────────────────────────────────────►│
       │                       │         new DarkButton()                  │
       │                       │ ◄──────────────────────────── (returned) ─│
       │                       │  factory.createCheckBox()                 │
       │                       │ ─────────────────────────────────────────►│
       │                       │         new DarkCheckBox()                │
       │                       │ ◄──────────────────────────── (returned) ─│
       │                       │  factory.createTextField()                │
       │                       │ ─────────────────────────────────────────►│
       │                       │         new DarkTextField()               │
       │                       │ ◄──────────────────────────── (returned) ─│
       │                       │                      │                    │
       │  app.renderUI()       │                      │                    │
       │ ─────────────────────────────────────────────►                    │
       │                       │              button.render()              │
       │                       │              checkBox.render()            │
       │                       │              textField.render()           │
       │         [Dark] Button / CheckBox / TextField rendered             │
```

---

## Switching Families (zero client change)

```
theme = "dark"                        theme = "light"
──────────────                        ───────────────
resolveFactory("dark")                resolveFactory("light")
  → DarkThemeFactory                    → LightThemeFactory
  → DarkButton                          → LightButton
  → DarkCheckBox                        → LightCheckBox
  → DarkTextField                       → LightTextField

Application code: IDENTICAL ✅        Application code: IDENTICAL ✅
```

---

## Adding a New Family (HighContrast)

```
Step 1 — Create 3 product classes               (new files only)
  HighContrastButton    implements Button
  HighContrastCheckBox  implements CheckBox
  HighContrastTextField implements TextField

Step 2 — Create 1 factory class                 (new file only)
  HighContrastFactory   implements UIFactory
    createButton()    → new HighContrastButton()
    createCheckBox()  → new HighContrastCheckBox()
    createTextField() → new HighContrastTextField()

Step 3 — Register in resolveFactory()            (one line)
  case "highcontrast" -> new HighContrastFactory()

Application.java      → 0 changes ✅
renderUI()            → 0 changes ✅
simulateInteractions()→ 0 changes ✅
```

---

## Package Structure

```
abstractfactory/
│
├── withoutabstractfactory/              ❌ problem demo (UI theme)
│   └── WithoutAbstractFactoryDemo.java
│
├── withabstractfactory/                 ✅ solution demo  (primary)
│   ├── Button.java                      abstract product A
│   ├── CheckBox.java                    abstract product B
│   ├── TextField.java                   abstract product C
│   ├── UIFactory.java                   abstract factory interface
│   ├── LightButton / CheckBox / TextField.java   Light family
│   ├── DarkButton  / CheckBox / TextField.java   Dark family
│   ├── LightThemeFactory.java           concrete factory A
│   ├── DarkThemeFactory.java            concrete factory B
│   ├── Application.java                 client (depends only on interfaces)
│   └── AbstractFactoryDemo.java         entry point
│
└── example/
    └── oswidget/                        alternate example (Windows / Mac widgets)
        ├── withoutfactory/
        └── withfactory/
```

---

## Key Roles

| Role | Class in demo | Responsibility |
|------|--------------|----------------|
| Abstract Factory | `UIFactory` | Declares creation methods for every product |
| Concrete Factory | `LightThemeFactory`, `DarkThemeFactory` | Creates a full, consistent family |
| Abstract Product | `Button`, `CheckBox`, `TextField` | Interface the client codes against |
| Concrete Product | `LightButton`, `DarkButton`, … | Actual objects produced |
| Client | `Application` | Uses only abstract factory + product interfaces |

---

## Key Rules

| Rule | Why |
|------|-----|
| Client holds `UIFactory`, not `LightThemeFactory` | Decoupled — swap family by swapping factory |
| Each factory creates the **whole family** | Consistency — widgets always match |
| Client never calls `new ConcreteProduct()` | Testable, extensible |
| New family = new classes only, zero client edits | Open/Closed Principle ✅ |

---

## Factory vs Abstract Factory

| | Factory | Abstract Factory |
|---|---|---|
| Products | **One** type | **Multiple** related types |
| Families | One | Many (Light, Dark, HighContrast…) |
| Family consistency | Not enforced | **Guaranteed** |
| Code change for new type | +1 class, +1 factory line | +N product classes, +1 factory class |
| Use when | Need one object of varying subtypes | Need a whole consistent set of objects |

---

## Real-World Examples

| Example | Pattern role |
|---------|-------------|
| `javax.xml.parsers.DocumentBuilderFactory` | Abstract factory for SAX vs DOM parsers |
| `java.sql.Connection` → `getStatement()`, `getMetaData()` | Consistent family from one connection |
| Spring `ApplicationContext` | Creates a consistent family of beans per config |
| Cloud SDK: `AwsFactory` vs `AzureFactory` | Each creates Storage + Queue + DB for that cloud |
| Cross-platform UI: Windows vs macOS vs Linux | Each OS factory produces matching native widgets |
