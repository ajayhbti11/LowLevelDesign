package org.lldQuestions.parkinglot.service;

import org.lldQuestions.parkinglot.enums.SpotType;
import org.lldQuestions.parkinglot.enums.VehicleType;
import org.lldQuestions.parkinglot.model.ParkingFloor;
import org.lldQuestions.parkinglot.model.ParkingSpot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Singleton — represents the physical parking lot.
 * Holds floors; knows nothing about tickets or fees.
 *
 * Thread-safe singleton via static inner holder (Bill Pugh idiom).
 */
public class ParkingLot {

    private final String             name;
    private final List<ParkingFloor> floors;
    private final List<EntryGate>    entryGates;
    private final List<ExitGate>     exitGates;

    private ParkingLot(String name) {
        this.name       = name;
        this.floors     = new ArrayList<>();
        this.entryGates = new ArrayList<>();
        this.exitGates  = new ArrayList<>();
    }

    // ── Singleton ────────────────────────────────────────────────────────────

    private static class Holder {
        private static final ParkingLot INSTANCE = new ParkingLot("Central Parking");
    }

    public static ParkingLot getInstance() {
        return Holder.INSTANCE;
    }

    // ── Setup (called once during initialisation) ─────────────────────────────

    public void addFloor(ParkingFloor floor) {
        floors.add(floor);
    }

    public void addEntryGate(EntryGate gate) {
        entryGates.add(gate);
    }

    public void addExitGate(ExitGate gate) {
        exitGates.add(gate);
    }

    /** Get entry gate by id — useful for routing vehicles to a specific gate. */
    public Optional<EntryGate> getEntryGate(String gateId) {
        return entryGates.stream().filter(g -> g.getGateId().equals(gateId)).findFirst();
    }

    /** Get exit gate by id. */
    public Optional<ExitGate> getExitGate(String gateId) {
        return exitGates.stream().filter(g -> g.getGateId().equals(gateId)).findFirst();
    }

    // ── Core operations ───────────────────────────────────────────────────────

    /**
     * Find the first available spot across all floors for the given vehicle type.
     * Searches floor 1 → floor N, spot 1 → spot M.
     *
     * @return Optional containing (floor, spot) or empty if lot is full
     */
    public Optional<FloorSpotPair> findAvailableSpot(VehicleType vehicleType) {
        SpotType required = toSpotType(vehicleType);
        for (ParkingFloor floor : floors) {
            Optional<ParkingSpot> spot = floor.findFreeSpot(required);
            if (spot.isPresent()) {
                return Optional.of(new FloorSpotPair(floor, spot.get()));
            }
        }
        return Optional.empty();
    }

    /** Print a snapshot of available spots per floor. */
    public void printAvailability() {
        System.out.println("\n── Parking Lot: " + name + " ──────────────────");
        for (ParkingFloor floor : floors) {
            long small  = floor.countFreeSpots(SpotType.SMALL);
            long medium = floor.countFreeSpots(SpotType.MEDIUM);
            long large  = floor.countFreeSpots(SpotType.LARGE);
            System.out.printf("  Floor %-2d │ Small(2W): %2d  Medium(Car): %2d  Large(Truck): %2d%n",
                    floor.getFloorNumber(), small, medium, large);
        }
        System.out.println("──────────────────────────────────────────────");
    }

    public String             getName()       { return name;                                    }
    public List<ParkingFloor> getFloors()     { return Collections.unmodifiableList(floors);     }
    public List<EntryGate>    getEntryGates() { return Collections.unmodifiableList(entryGates); }
    public List<ExitGate>     getExitGates()  { return Collections.unmodifiableList(exitGates);  }

    // ── Helper ────────────────────────────────────────────────────────────────

    public static SpotType toSpotType(VehicleType v) {
        return switch (v) {
            case TWO_WHEELER -> SpotType.SMALL;
            case CAR         -> SpotType.MEDIUM;
            case TRUCK       -> SpotType.LARGE;
        };
    }

    // ── Inner result type ─────────────────────────────────────────────────────

    public record FloorSpotPair(ParkingFloor floor, ParkingSpot spot) {}
}
