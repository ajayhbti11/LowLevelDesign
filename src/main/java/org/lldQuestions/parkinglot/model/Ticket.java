package org.lldQuestions.parkinglot.model;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Issued when a vehicle enters.
 * Holds everything needed to compute the fee on exit.
 */
public class Ticket {

    private final String        ticketId;
    private final Vehicle       vehicle;
    private final ParkingSpot   spot;
    private final int           floorNumber;
    private final LocalDateTime entryTime;

    public Ticket(Vehicle vehicle, ParkingSpot spot, int floorNumber) {
        this.ticketId    = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.vehicle     = vehicle;
        this.spot        = spot;
        this.floorNumber = floorNumber;
        this.entryTime   = LocalDateTime.now();
    }

    public String        getTicketId()    { return ticketId;    }
    public Vehicle       getVehicle()     { return vehicle;     }
    public ParkingSpot   getSpot()        { return spot;        }
    public int           getFloorNumber() { return floorNumber; }
    public LocalDateTime getEntryTime()   { return entryTime;   }

    @Override
    public String toString() {
        return "Ticket[" + ticketId + " | " + vehicle + " | Floor-" + floorNumber
                + " | " + spot + " | entry=" + entryTime + "]";
    }
}
