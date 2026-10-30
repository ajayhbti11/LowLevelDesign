package org.solidprinciple.isp;

/**
 * ❌ Demo — WITHOUT Interface Segregation Principle
 *
 * <pre>
 * ─────────────────────────────────────────────────────────────────────────────────
 * I — INTERFACE SEGREGATION PRINCIPLE (ISP)
 *
 * "Clients should not be forced to depend on interfaces they do not use."
 * Many small, focused interfaces are better than one large, fat interface.
 *
 * ─────────────────────────────────────────────────────────────────────────────────
 * ❌ Problem (this demo)
 *   Worker interface has both work() and eat().
 *   RobotWorker must implement eat() even though robots don't eat.
 *   Forced to throw UnsupportedOperationException — a runtime bomb. ❌
 *
 * ✅ Fix → see withisp package
 *   Workable  — work()
 *   Eatable   — eat()
 *   Chargable — charge()
 *   HumanWorker implements Workable + Eatable
 *   RobotWorker implements Workable + Chargable
 *
 * ─────────────────────────────────────────────────────────────────────────────────
 * 📦 REAL-WORLD USE CASES
 *
 * 1. Printer / Scanner / Fax
 *       Bad:  Machine interface with print() + scan() + fax()
 *             BasicPrinter forced to throw on scan() and fax()
 *       Good: Printable | Scannable | Faxable — implement only what applies
 *
 * 2. Media Player
 *       Bad:  MediaPlayer with play() + pause() + record() + stream()
 *             SimplePlayer forced to throw on record()
 *       Good: Playable | Recordable | Streamable
 *
 * 3. Repository pattern
 *       Bad:  Repository with read() + write() + delete() + bulkInsert()
 *             ReadOnlyRepository throws on write/delete
 *       Good: ReadRepository | WriteRepository | BulkRepository
 *
 * 4. Payment terminal
 *       Bad:  PaymentDevice with swipe() + tap() + insertChip() + scanQR()
 *             LegacyTerminal throws on tap() and scanQR()
 *       Good: SwipePayment | TapPayment | QRPayment — implement only supported ones
 *
 * ─────────────────────────────────────────────────────────────────────────────────
 * 🧠 KEY BENEFITS
 *   • No forced UnsupportedOperationException in implementations
 *   • Smaller interfaces are easier to test and mock
 *   • Changes to one capability don't ripple through unrelated implementors
 *
 * ─────────────────────────────────────────────────────────────────────────────────
 * ❓ Common Interview Questions
 *   "What is ISP and how does it differ from SRP?"
 *   "Why are fat interfaces a problem?"
 * ─────────────────────────────────────────────────────────────────────────────────
 * </pre>
 */
public class ISPDemo {

    public static void main(String[] args) {
        Worker human = new HumanWorker();
        human.work();
        human.eat();

        Worker robot = new RobotWorker();
        robot.work();
        robot.eat(); // ❌ throws UnsupportedOperationException at runtime
    }
}
