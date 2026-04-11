package com.example.airportsecurity.Ahsan.CommandingOfficer;

public class Assignment {
    private String flightId;
    private String staffName;

    public Assignment(String flightId, String staffName) {
        this.flightId = flightId;
        this.staffName = staffName;
    }

    public String getFlightId() {
        return flightId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
}

