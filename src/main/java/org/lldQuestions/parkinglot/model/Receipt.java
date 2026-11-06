package org.lldQuestions.parkinglot.model;

import java.time.LocalDateTime;

/**
 * Issued when a vehicle exits.
 * Summarises the stay and the fee charged.
 */
public class Receipt {

    private final Ticket        ticket;
    private final LocalDateTime exitTime;
    private final long          durationHours; // ceiling hours
    private final double        fee;

    public Receipt(Ticket ticket, LocalDateTime exitTime, long durationHours, double fee) {
        this.ticket        = ticket;
        this.exitTime      = exitTime;
        this.durationHours = durationHours;
        this.fee           = fee;
    }

    public Ticket        getTicket()        { return ticket;        }
    public LocalDateTime getExitTime()      { return exitTime;      }
    public long          getDurationHours() { return durationHours; }
    public double        getFee()           { return fee;           }

    @Override
    public String toString() {
        return "\n─────────────────────────────────────────\n"
             + "  PARKING RECEIPT\n"
             + "  Ticket     : " + ticket.getTicketId() + "\n"
             + "  Vehicle    : " + ticket.getVehicle()  + "\n"
             + "  Floor      : " + ticket.getFloorNumber() + "\n"
             + "  Spot       : " + ticket.getSpot()     + "\n"
             + "  Entry      : " + ticket.getEntryTime()+ "\n"
             + "  Exit       : " + exitTime              + "\n"
             + "  Duration   : " + durationHours + " hr(s)\n"
             + "  Fee        : ₹" + String.format("%.2f", fee) + "\n"
             + "─────────────────────────────────────────";
    }
}
