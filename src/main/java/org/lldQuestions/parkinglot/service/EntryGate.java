package org.lldQuestions.parkinglot.service;

import org.lldQuestions.parkinglot.model.Ticket;
import org.lldQuestions.parkinglot.model.Vehicle;

/**
 * Physical entry gate — the barrier a vehicle passes through to enter the lot.
 *
 * Lives in the service layer because it holds a ParkingService dependency
 * and delegates operational logic to it. It is not a plain data model.
 *
 * Responsibilities:
 *  - Accept a vehicle
 *  - Delegate spot-finding and ticket creation to ParkingService
 *  - Return the Ticket to the driver
 */
public class EntryGate {

    private final String         gateId;
    private final ParkingService parkingService;

    public EntryGate(String gateId, ParkingService parkingService) {
        this.gateId         = gateId;
        this.parkingService = parkingService;
    }

    /**
     * Vehicle arrives at this gate.
     * Raises the barrier and issues a Ticket if a spot is available.
     *
     * @param vehicle the vehicle requesting entry
     * @return Ticket — driver keeps this until exit
     * @throws IllegalStateException if the lot is full for this vehicle type
     */
    public Ticket enter(Vehicle vehicle) {
        System.out.println("🚗 [" + gateId + "] Vehicle arrived: " + vehicle);
        Ticket ticket = parkingService.checkIn(vehicle);
        System.out.println("   [" + gateId + "] Barrier raised  → Ticket: " + ticket.getTicketId());
        return ticket;
    }

    public String getGateId() { return gateId; }
}
