package org.lldQuestions.parkinglot.model;

import org.lldQuestions.parkinglot.enums.SpotType;

/**
 * A single parking spot on a floor.
 * Thread-safe: park / free are synchronized.
 */
public class ParkingSpot {

    private final int      spotNumber;
    private final SpotType type;
    private       Vehicle  parkedVehicle; // null when free

    public ParkingSpot(int spotNumber, SpotType type) {
        this.spotNumber = spotNumber;
        this.type       = type;
    }

    public synchronized boolean isFree() {
        return parkedVehicle == null;
    }

    /** @return true if the spot was free and the vehicle was parked */
    public synchronized boolean park(Vehicle vehicle) {
        if (!isFree()) return false;
        this.parkedVehicle = vehicle;
        return true;
    }

    /** @return the vehicle that was parked, or null if spot was already free */
    public synchronized Vehicle free() {
        Vehicle v = parkedVehicle;
        parkedVehicle = null;
        return v;
    }

    public int      getSpotNumber()   { return spotNumber; }
    public SpotType getType()         { return type; }
    public Vehicle  getParkedVehicle(){ return parkedVehicle; }

    @Override
    public String toString() {
        return "Spot-" + spotNumber + "(" + type + ")";
    }
}
