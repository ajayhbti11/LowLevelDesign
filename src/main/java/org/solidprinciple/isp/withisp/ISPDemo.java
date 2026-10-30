package org.solidprinciple.isp.withisp;

/**
 * ✅ Demo — WITH Interface Segregation Principle
 *
 * <pre>
 * ─────────────────────────────────────────────────────────────────────────────────
 * I — INTERFACE SEGREGATION PRINCIPLE (ISP)
 *
 * "Clients should not be forced to depend on interfaces they do not use."
 *
 * ─────────────────────────────────────────────────────────────────────────────────
 * ✅ Solution (this demo)
 *   Workable  — work()         (humans and robots both work)
 *   Eatable   — eat()          (only humans eat)
 *   Chargable — charge()       (only robots charge)
 *
 *   HumanWorker implements Workable + Eatable   — no forced methods ✅
 *   RobotWorker implements Workable + Chargable — no forced methods ✅
 *   No UnsupportedOperationException anywhere.
 *
 * ─────────────────────────────────────────────────────────────────────────────────
 * 📦 REAL-WORLD USE CASES
 *
 * 1. Printer / Scanner / Fax
 *       Printable | Scannable | Faxable
 *       BasicPrinter implements Printable only — no forced stubs
 *
 * 2. Media Player
 *       Playable | Recordable | Streamable
 *       SimplePlayer implements Playable only
 *
 * 3. Repository pattern
 *       ReadRepository | WriteRepository | BulkRepository
 *       ReadOnlyRepository implements ReadRepository only
 *
 * 4. Payment terminal
 *       SwipePayment | TapPayment | QRPayment
 *       LegacyTerminal implements SwipePayment only
 *
 * ─────────────────────────────────────────────────────────────────────────────────
 * 🧠 KEY BENEFITS
 *   • Implementations only carry methods they actually support
 *   • Interfaces stay focused — easier to mock in tests
 *   • Adding a new capability (e.g. Printable) doesn't affect Workable implementors
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
        HumanWorker human = new HumanWorker();
        human.work();   // ✅ HumanWorker implements Workable
        human.eat();    // ✅ HumanWorker implements Eatable

        RobotWorker robot = new RobotWorker();
        robot.work();   // ✅ RobotWorker implements Workable
        robot.charge(); // ✅ RobotWorker implements Chargable — no eat() forced ✅
    }
}
