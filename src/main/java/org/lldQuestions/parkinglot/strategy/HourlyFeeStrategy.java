package org.lldQuestions.parkinglot.strategy;

import org.lldQuestions.parkinglot.enums.VehicleType;

/**
 * Concrete strategy — flat hourly rate per vehicle type.
 *
 *  TWO_WHEELER  → ₹20 / hr
 *  CAR          → ₹40 / hr
 *  TRUCK        → ₹80 / hr
 */
public class HourlyFeeStrategy implements FeeStrategy {

    private static final double TWO_WHEELER_RATE = 20.0;
    private static final double CAR_RATE         = 40.0;
    private static final double TRUCK_RATE       = 80.0;

    @Override
    public double calculate(VehicleType vehicleType, long durationHours) {
        double rate = switch (vehicleType) {
            case TWO_WHEELER -> TWO_WHEELER_RATE;
            case CAR         -> CAR_RATE;
            case TRUCK       -> TRUCK_RATE;
        };
        return rate * durationHours;
    }
}
