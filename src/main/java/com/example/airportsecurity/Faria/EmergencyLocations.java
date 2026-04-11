package com.example.airportsecurity.Faria;

import java.io.Serializable;

public class EmergencyLocations implements Serializable {
    private String date, time, location, staffID;

    public EmergencyLocations(String date, String time, String location, String staffID) {
        this.date = date;
        this.time = time;
        this.location = location;
        this.staffID = staffID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    @Override
    public String toString() {
        return "EmergencyLocations{" +
                "date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", location='" + location + '\'' +
                ", staffID='" + staffID + '\'' +
                '}';
    }
}
