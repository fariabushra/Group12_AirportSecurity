package com.example.airportsecurity.Ahsan.ImmigrationOfficer;

import java.io.Serializable;

public class OverstayCheck implements Serializable {

    private String name;
    private String passportNo;
    private int entryDate;
    private int allowedDays;
    private int currentDate;
    private String status;

    public OverstayCheck(String name, String passportNo, int entryDate, int allowedDays, int currentDate) {
        this.name = name;
        this.passportNo = passportNo;
        this.entryDate = entryDate;
        this.allowedDays = allowedDays;
        this.currentDate = currentDate;
        this.status = "OK";
    }

    public String getName() { return name; }
    public String getPassportNo() { return passportNo; }
    public int getEntryDate() { return entryDate; }
    public int getAllowedDays() { return allowedDays; }
    public int getCurrentDate() { return currentDate; }
    public String getStatus() { return status; }

    public void setStatus(String status) {
        this.status = status;
    }
}