package org.lldQuestions.parkinglot.service;

import org.lldQuestions.parkinglot.model.Receipt;
import org.lldQuestions.parkinglot.model.Ticket;
import org.lldQuestions.parkinglot.model.Vehicle;
import org.lldQuestions.parkinglot.strategy.FeeStrategy;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

/**
 * Orchestrates entry and exit.
 *
 * Responsibilities:
 *  - Find a spot via ParkingLot
 *  - Issue a Ticket on entry
 *  - Free the spot and generate a Receipt on exit
 *  - Delegate fee calculation to FeeStrategy (Strategy pattern)
 */
public class ParkingService {

    private final ParkingLot              parkingLot;
    private final FeeStrategy             feeStrategy;
    private final Map<String, Ticket>     activeTickets; // ticketId → Ticket

    public ParkingService(ParkingLot parkingLot, FeeStrategy feeStrategy) {
        this.parkingLot    = parkingLot;
        this.feeStrategy   = feeStrategy;
        this.activeTickets = new HashMap<>();
    }

    // ── Entry ─────────────────────────────────────────────────────────────────

    /**
     * Park the vehicle.
     *
     * @return Ticket if a spot was available
     * @throws IllegalStateException if the lot is full for this vehicle type
     */
    public Ticket checkIn(Vehicle vehicle) {
        ParkingLot.FloorSpotPair pair = parkingLot.findAvailableSpot(vehicle.getType())
                .orElseThrow(() -> new IllegalStateException(
                        "Parking lot is full for vehicle type: " + vehicle.getType()));

        boolean parked = pair.spot().park(vehicle);
        if (!parked) {
            // Race condition: spot was taken between find and park — retry once
            return checkIn(vehicle);
        }

        Ticket ticket = new Ticket(vehicle, pair.spot(), pair.floor().getFloorNumber());
        activeTickets.put(ticket.getTicketId(), ticket);

        System.out.println("✅ Check-in  : " + vehicle + " → Floor-"
                + pair.floor().getFloorNumber() + " " + pair.spot());
        System.out.println("   Ticket ID : " + ticket.getTicketId());
        return ticket;
    }

    // ── Exit ──────────────────────────────────────────────────────────────────

    /**
     * Release the spot and compute the fee.
     *
     * @param ticketId  the ticket ID given at check-in
     * @return Receipt with duration and fee details
     * @throws IllegalArgumentException for an unknown ticket ID
     */
    public Receipt checkOut(String ticketId) {
        Ticket ticket = activeTickets.remove(ticketId);
        if (ticket == null) {
            throw new IllegalArgumentException("Unknown ticket: " + ticketId);
        }

        LocalDateTime exitTime = LocalDateTime.now();

        // Ceiling hours — minimum 1 hour charge
        long minutes = ChronoUnit.MINUTES.between(ticket.getEntryTime(), exitTime);
        long hours   = Math.max(1, (long) Math.ceil(minutes / 60.0));

        double fee = feeStrategy.calculate(ticket.getVehicle().getType(), hours);

        ticket.getSpot().free();

        Receipt receipt = new Receipt(ticket, exitTime, hours, fee);
        System.out.println(receipt);
        return receipt;
    }

    /** Convenience — get active ticket count. */
    public int activeTicketCount() {
        return activeTickets.size();
    }
}
