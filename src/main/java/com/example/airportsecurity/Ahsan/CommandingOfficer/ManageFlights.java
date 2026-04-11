package com.example.airportsecurity.Ahsan.CommandingOfficer;

public class ManageFlights {

    private String flightId;
    private String destination;

    public ManageFlights(String flightId, String destination) {
        this.flightId = flightId;
        this.destination = destination;
    }

    public String getFlightId() {
        return flightId;
    }

    public String getDestination() {
        return destination;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}