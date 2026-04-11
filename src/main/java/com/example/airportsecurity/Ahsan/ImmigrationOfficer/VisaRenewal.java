package com.example.airportsecurity.Ahsan.ImmigrationOfficer;

public class VisaRenewal {

    private String name;
    private String oldVisaNo;
    private String documentStatus;
    private int stayDays;
    private String status;

    public VisaRenewal(String name, String oldVisaNo, String documentStatus, int stayDays) {
        this.name = name;
        this.oldVisaNo = oldVisaNo;
        this.documentStatus = documentStatus;
        this.stayDays = stayDays;
        this.status = "Pending";
    }

    public String getName() { return name; }
    public String getOldVisaNo() { return oldVisaNo; }
    public String getDocumentStatus() { return documentStatus; }
    public int getStayDays() { return stayDays; }
    public String getStatus() { return status; }

    public void approve() {
        status = "APPROVED";
    }

    public void reject() {
        status = "REJECTED";
    }
}