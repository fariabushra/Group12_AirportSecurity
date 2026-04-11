package com.example.airportsecurity.Ahsan.ImmigrationOfficer;

public class Passport {

    private String name;
    private String passportNo;
    private String expiryDate;
    private String status;

    public Passport(String name, String passportNo, String expiryDate) {
        this.name = name;
        this.passportNo = passportNo;
        this.expiryDate = expiryDate;
        this.status = "Pending";
    }

    public String getName() { return name; }
    public String getPassportNo() { return passportNo; }
    public String getExpiryDate() { return expiryDate; }
    public String getStatus() { return status; }

    public void markValid() {
        status = "VALID";
    }

    public void markInvalid() {
        status = "INVALID";
    }
}