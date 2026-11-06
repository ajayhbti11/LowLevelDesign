# Factory Pattern

> **Intent:** Define a single place responsible for creating objects.  
> The client asks for a product by type — it never calls `new ConcreteClass()` directly.

---

## The Problem (without factory)

```
Client
  │
  ├─ if "email"    → new EmailNotification()    ❌ tight coupling
  ├─ if "sms"      → new SMSNotification()      ❌ tight coupling
  ├─ if "push"     → new PushNotification()     ❌ tight coupling
  └─ (add WhatsApp → edit EVERY caller)         ❌ OCP violated
```

Every caller owns the `if/else` chain.  
Add a new channel → change every file that has this logic.

---

## The Solution (with factory)

```
Client
  │
  └─► NotificationFactory.create("whatsapp")
              │
              └─► returns Notification  (interface only — client never sees the concrete class)
```

All creation logic lives in **one place**.  
Add a channel → add one class + one line in the factory. Every caller stays unchanged. ✅

---

## Class Diagram

```
              «interface»
             Notification
            ┌─────────────┐
            │ + send(msg) │
            └──────┬──────┘
                   │ implements
       ┌───────────┼────────────┬──────────────┐
       ▼           ▼            ▼              ▼
 EmailNotif   SMSNotif    PushNotif    WhatsAppNotif


        NotificationFactory
       ┌──────────────────────────────────┐
       │ + create(type: String)           │
       │     "email"    → EmailNotif      │
       │     "sms"      → SMSNotif        │
       │     "push"     → PushNotif       │
       │     "whatsapp" → WhatsAppNotif   │
       └──────────────────────────────────┘
                   ▲
                   │ calls
                Client
       ┌──────────────────────────┐
       │  Notification n =        │
       │  NotificationFactory     │
       │    .create("sms")        │
       │  n.send("Order placed!") │
       └──────────────────────────┘
```

---

## Object Creation Flow

```
 Client                  NotificationFactory            ConcreteProduct
   │                            │                             │
   │  create("sms")             │                             │
   │ ─────────────────────────► │                             │
   │                            │  new SMSNotification()      │
   │                            │ ───────────────────────────►│
   │                            │      SMSNotification        │
   │                            │ ◄─── (object returned) ─── │
   │   Notification (interface) │                             │
   │ ◄───────────────────────── │                             │
   │                            │                             │
   │  n.send("Order placed!")   │                             │
   │ ──────────────────────────────────────────────────────► │
   │                       [SMS] Order placed!               │
```

---

## Adding a New Channel (flow)

```
BEFORE (without factory)          AFTER (with factory)
────────────────────────          ────────────────────
1. Create SlackNotification  ✅   1. Create SlackNotification   ✅
2. Edit caller A             ❌   2. Add one case in factory     ✅
3. Edit caller B             ❌   3. Done — all callers intact   ✅
4. Edit caller C             ❌
```

---

## Package Structure

```
factory/
├── withoutfactory/                 ❌ problem demo
│   ├── Notification.java
│   ├── EmailNotification.java
│   ├── SMSNotification.java
│   ├── PushNotification.java
│   └── WithoutFactoryDemo.java
│
├── withfactory/                    ✅ solution demo  (primary)
│   ├── Notification.java           interface (product)
│   ├── EmailNotification.java      concrete product
│   ├── SMSNotification.java        concrete product
│   ├── PushNotification.java       concrete product
│   ├── WhatsAppNotification.java   concrete product (added with zero caller change)
│   ├── NotificationFactory.java    THE factory
│   └── FactoryDemo.java            client / entry point
│
└── example/
    ├── database/                   alternate example (DB connections)
    ├── ecommerce/                  alternate example (shipping methods)
    └── example2/                   alternate example (shapes)
```

---

## Key Rules

| Rule | Why |
|------|-----|
| Client depends only on the **interface** | Decoupling — can swap impl without touching caller |
| Creation logic in **one class** | DRY — change in one place propagates everywhere |
| New product = new class + one factory line | Open/Closed Principle |
| No `new ConcreteClass()` in the client | Testable — inject mocks via the factory |

---

## Factory vs Abstract Factory (quick rule)

| | Factory | Abstract Factory |
|---|---|---|
| Products | **One** type (`Notification`) | **Multiple** related types (`Button + CheckBox + TextField`) |
| Families | One | Many (Light theme, Dark theme, …) |
| Family consistency | Not enforced | **Guaranteed** — can't mix families |
| Use when | You need one object of varying subtypes | You need a whole consistent set of objects |

---

## Real-World Examples

| Example | Factory used |
|---------|-------------|
| `java.util.Calendar.getInstance()` | Returns right Calendar impl for locale |
| `java.sql.DriverManager.getConnection()` | Returns the right JDBC driver |
| `LoggerFactory.getLogger(...)` | Returns SLF4J, Log4j, or Logback logger |
| Spring `BeanFactory.getBean()` | Returns the right Spring bean |
| Payment: `PaymentFactory.create("upi")` | Returns UPI, Card, or NetBanking handler |
