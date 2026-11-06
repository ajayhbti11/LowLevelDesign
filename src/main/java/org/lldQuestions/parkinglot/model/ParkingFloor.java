package org.lldQuestions.parkinglot.model;

import org.lldQuestions.parkinglot.enums.SpotType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * One floor of the parking lot.
 * Holds a flat list of spots; spot allocation scans from spot-1 upwards.
 */
public class ParkingFloor {

    private final int              floorNumber;
    private final List<ParkingSpot> spots;

    public ParkingFloor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.spots       = new ArrayList<>();
    }

    public void addSpot(ParkingSpot spot) {
        spots.add(spot);
    }

    /**
     * Find the first free spot of the required type on this floor.
     */
    public Optional<ParkingSpot> findFreeSpot(SpotType type) {
        return spots.stream()
                .filter(s -> s.getType() == type && s.isFree())
                .findFirst();
    }

    /** Count free spots of a given type. */
    public long countFreeSpots(SpotType type) {
        return spots.stream()
                .filter(s -> s.getType() == type && s.isFree())
                .count();
    }

    public int              getFloorNumber() { return floorNumber; }
    public List<ParkingSpot> getSpots()      { return Collections.unmodifiableList(spots); }
}
