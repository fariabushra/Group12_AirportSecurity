package com.example.airportsecurity.Ahsan.ImmigrationOfficer;

public class ExitPermission {

    private String travelerName;
    private String passportNo;
    private String visaStatus;
    private String decision;

    public ExitPermission(String travelerName, String passportNo, String visaStatus) {
        this.travelerName = travelerName;
        this.passportNo = passportNo;
        this.visaStatus = visaStatus;
        this.decision = "Pending";
    }

    public String getTravelerName() { return travelerName; }
    public String getPassportNo() { return passportNo; }
    public String getVisaStatus() { return visaStatus; }
    public String getDecision() { return decision; }

    public void allowExit() {
        decision = "EXIT APPROVED";
    }

    public void denyExit() {
        decision = "EXIT DENIED";
    }
}