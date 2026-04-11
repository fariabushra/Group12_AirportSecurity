package com.example.airportsecurity.Ahsan.CommandingOfficer;

public class MonitorFlights {

    private String flightId;
    private String destination;
    private String status;

    public MonitorFlights(String flightId, String destination, String status) {
        this.flightId = flightId;
        this.destination = destination;
        this.status = status;
    }

    public String getFlightId() {
        return flightId;
    }

    public String getDestination() {
        return destination;
    }

    public String getStatus() {
        return status;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}