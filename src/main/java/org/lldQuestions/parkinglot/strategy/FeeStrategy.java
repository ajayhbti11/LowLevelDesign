package org.lldQuestions.parkinglot.strategy;

import org.lldQuestions.parkinglot.enums.VehicleType;

/**
 * Strategy interface for fee calculation.
 * Swap implementations without touching ParkingService.
 */
public interface FeeStrategy {
    /**
     * @param vehicleType  type of vehicle
     * @param durationHours  ceiling hours parked (minimum 1)
     * @return total fee in ₹
     */
    double calculate(VehicleType vehicleType, long durationHours);
}
