package com.example.airportsecurity.Faria;

import java.io.Serializable;

public class Schedule implements Serializable {
    private String date, shiftTime, location, officerID;

    public Schedule(String date, String shiftTime, String location, String officerID) {
        this.date = date;
        this.shiftTime = shiftTime;
        this.location = location;
        this.officerID = officerID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getShiftTime() {
        return shiftTime;
    }

    public void setShiftTime(String shiftTime) {
        this.shiftTime = shiftTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOfficerID() {
        return officerID;
    }

    public void setOfficerID(String officerID) {
        this.officerID = officerID;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "date='" + date + '\'' +
                ", shiftTime='" + shiftTime + '\'' +
                ", location='" + location + '\'' +
                ", officerID='" + officerID + '\'' +
                '}';
    }
}
