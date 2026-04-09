package com.example.airportsecurity.Samia;

import java.io.Serializable;

public class BaggageLog implements Serializable {
    private String ownerName;
    private String flightNumber;
    private String inspectionReason;
    private String outcome;

    public BaggageLog(String ownerName, String flightNumber, String inspectionReason, String outcome) {
        this.ownerName = ownerName;
        this.flightNumber = flightNumber;
        this.inspectionReason = inspectionReason;
        this.outcome = outcome;
    }

    public String getOwnerName() { return ownerName; }
    public String getFlightNumber() { return flightNumber; }
    public String getInspectionReason() { return inspectionReason; }
    public String getOutcome() { return outcome; }
}