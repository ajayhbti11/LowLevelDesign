package org.lldQuestions.parkinglot;

import org.lldQuestions.parkinglot.enums.SpotType;
import org.lldQuestions.parkinglot.enums.VehicleType;
import org.lldQuestions.parkinglot.model.ParkingFloor;
import org.lldQuestions.parkinglot.model.ParkingSpot;
import org.lldQuestions.parkinglot.model.Ticket;
import org.lldQuestions.parkinglot.model.Vehicle;
import org.lldQuestions.parkinglot.service.EntryGate;
import org.lldQuestions.parkinglot.service.ExitGate;
import org.lldQuestions.parkinglot.service.ParkingLot;
import org.lldQuestions.parkinglot.service.ParkingService;
import org.lldQuestions.parkinglot.strategy.HourlyFeeStrategy;

/**
 * ──────────────────────────────────────────────────────────────────────────────
 * PARKING LOT — LLD Demo  (with Entry / Exit Gates)
 *
 * Design overview
 * ───────────────
 *  ParkingLot        (Singleton)  — holds floors + gates, finds free spots
 *  ParkingFloor                   — holds spots on one level
 *  ParkingSpot                    — one physical space, thread-safe park/free
 *  Vehicle                        — licence plate + type
 *  EntryGate                      — physical barrier on entry, issues Ticket
 *  ExitGate                       — physical barrier on exit, issues Receipt
 *  Ticket                         — issued on entry (entry time, spot, floor)
 *  Receipt                        — issued on exit (duration, fee)
 *  ParkingService                 — core logic: checkIn / checkOut
 *  FeeStrategy        (Strategy)  — pluggable fee calculation
 *  HourlyFeeStrategy              — ₹20/hr (2W) | ₹40/hr (Car) | ₹80/hr (Truck)
 *
 * Layout (this demo)
 * ──────────────────
 *  Floor 1 : 3 Small, 3 Medium, 2 Large
 *  Floor 2 : 3 Small, 3 Medium, 2 Large
 *  Entry Gates : Gate-A (north), Gate-B (south)
 *  Exit  Gates : Gate-X (north), Gate-Y (south)
 * ──────────────────────────────────────────────────────────────────────────────
 */
public class ParkingLotDemo {

    public static void main(String[] args) throws InterruptedException {

        // ── 1. Build the parking lot ─────────────────────────────────────────
        ParkingLot lot = ParkingLot.getInstance();
        lot.addFloor(buildFloor(1));
        lot.addFloor(buildFloor(2));

        // ── 2. Wire up service with fee strategy ─────────────────────────────
        ParkingService service = new ParkingService(lot, new HourlyFeeStrategy());

        // ── 3. Register entry and exit gates ─────────────────────────────────
        EntryGate entryA = new EntryGate("Gate-A", service);   // north entry
        EntryGate entryB = new EntryGate("Gate-B", service);   // south entry
        ExitGate  exitX  = new ExitGate("Gate-X", service);    // north exit
        ExitGate  exitY  = new ExitGate("Gate-Y", service);    // south exit

        lot.addEntryGate(entryA);
        lot.addEntryGate(entryB);
        lot.addExitGate(exitX);
        lot.addExitGate(exitY);

        // ── 4. Show initial availability ─────────────────────────────────────
        lot.printAvailability();

        // ── 5. Vehicles enter through different gates ─────────────────────────
        System.out.println("\n── Vehicles entering ────────────────────────────");
        Ticket t1 = entryA.enter(new Vehicle("MH01AB1234", VehicleType.CAR));
        Ticket t2 = entryA.enter(new Vehicle("DL02CD5678", VehicleType.TWO_WHEELER));
        Ticket t3 = entryB.enter(new Vehicle("KA03EF9012", VehicleType.TRUCK));
        Ticket t4 = entryB.enter(new Vehicle("TN04GH3456", VehicleType.CAR));
        Ticket t5 = entryB.enter(new Vehicle("MH05IJ7890", VehicleType.TWO_WHEELER));

        lot.printAvailability();

        // ── 6. Simulate time passing ──────────────────────────────────────────
        Thread.sleep(1000);

        // ── 7. Vehicles exit through different gates ──────────────────────────
        // Vehicle that entered Gate-A exits through Gate-Y — any gate works ✅
        System.out.println("\n── Vehicles exiting ─────────────────────────────");
        exitY.exit(t1.getTicketId());   // CAR exits via Gate-Y (south)
        exitX.exit(t3.getTicketId());   // TRUCK exits via Gate-X (north)

        lot.printAvailability();

        // ── 8. Re-park in a freed spot ────────────────────────────────────────
        System.out.println("\n── Re-park after free ───────────────────────────");
        Ticket t6 = entryA.enter(new Vehicle("GJ06KL1122", VehicleType.CAR));

        // ── 9. Fill all TRUCK spots and test full-lot ─────────────────────────
        System.out.println("\n── Fill all TRUCK spots ─────────────────────────");
        Ticket tt1 = entryA.enter(new Vehicle("RJ07MN2233", VehicleType.TRUCK));
        Ticket tt2 = entryB.enter(new Vehicle("UP08OP3344", VehicleType.TRUCK));
        Ticket tt3 = entryA.enter(new Vehicle("MP09QR4455", VehicleType.TRUCK));
        Ticket tt4 = entryB.enter(new Vehicle("HR10ST5566", VehicleType.TRUCK));

        System.out.println("\n── Attempt entry when TRUCK spots full ──────────");
        try {
            entryA.enter(new Vehicle("PB11UV6677", VehicleType.TRUCK));
        } catch (IllegalStateException e) {
            System.out.println("❌ [Gate-A] " + e.getMessage());
        }

        lot.printAvailability();

        // ── 10. Clear remaining vehicles ──────────────────────────────────────
        System.out.println("\n── All remaining exits ──────────────────────────");
        exitX.exit(t2.getTicketId());
        exitY.exit(t4.getTicketId());
        exitX.exit(t5.getTicketId());
        exitY.exit(t6.getTicketId());
        exitX.exit(tt1.getTicketId());
        exitY.exit(tt2.getTicketId());
        exitX.exit(tt3.getTicketId());
        exitY.exit(tt4.getTicketId());

        lot.printAvailability();
    }

    // ── Helper ────────────────────────────────────────────────────────────────

    private static ParkingFloor buildFloor(int floorNumber) {
        ParkingFloor floor = new ParkingFloor(floorNumber);
        int spotNum = (floorNumber - 1) * 8 + 1;

        for (int i = 0; i < 3; i++) floor.addSpot(new ParkingSpot(spotNum++, SpotType.SMALL));
        for (int i = 0; i < 3; i++) floor.addSpot(new ParkingSpot(spotNum++, SpotType.MEDIUM));
        for (int i = 0; i < 2; i++) floor.addSpot(new ParkingSpot(spotNum++, SpotType.LARGE));

        return floor;
    }
}
