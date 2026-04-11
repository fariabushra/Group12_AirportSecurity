package com.example.airportsecurity.Ahsan.ImmigrationOfficer;

public class Visa {

    private String applicantName;
    private String visaType;
    private String expiryDate;
    private String status;

    public Visa(String applicantName, String visaType, String expiryDate) {
        this.applicantName = applicantName;
        this.visaType = visaType;
        this.expiryDate = expiryDate;
        this.status = "Pending";
    }

    public String getApplicantName() { return applicantName; }
    public String getVisaType() { return visaType; }
    public String getExpiryDate() { return expiryDate; }
    public String getStatus() { return status; }

    public void approve() { status = "Approved"; }
    public void reject() { status = "Rejected"; }
}