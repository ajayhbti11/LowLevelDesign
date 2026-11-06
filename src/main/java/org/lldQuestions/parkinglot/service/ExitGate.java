package org.lldQuestions.parkinglot.service;

import org.lldQuestions.parkinglot.model.Receipt;

/**
 * Physical exit gate — the barrier a vehicle passes through to leave the lot.
 *
 * Lives in the service layer because it holds a ParkingService dependency
 * and delegates operational logic to it. It is not a plain data model.
 *
 * Responsibilities:
 *  - Accept the driver's ticket ID
 *  - Delegate fee calculation and spot release to ParkingService
 *  - Return the Receipt to the driver
 */
public class ExitGate {

    private final String         gateId;
    private final ParkingService parkingService;

    public ExitGate(String gateId, ParkingService parkingService) {
        this.gateId         = gateId;
        this.parkingService = parkingService;
    }

    /**
     * Vehicle arrives at this gate to exit.
     * Processes payment, frees the spot, and raises the barrier.
     *
     * @param ticketId the ticket ID the driver received at entry
     * @return Receipt — proof of payment
     * @throws IllegalArgumentException if the ticket ID is not recognised
     */
    public Receipt exit(String ticketId) {
        System.out.println("🚖 [" + gateId + "] Processing exit for ticket: " + ticketId);
        Receipt receipt = parkingService.checkOut(ticketId);
        System.out.println("   [" + gateId + "] Payment done    → Barrier raised");
        return receipt;
    }

    public String getGateId() { return gateId; }
}
