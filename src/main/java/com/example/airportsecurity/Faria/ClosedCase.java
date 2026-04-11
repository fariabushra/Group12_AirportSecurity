package com.example.airportsecurity.Faria;

import java.io.Serializable;

public class ClosedCase implements Serializable {
    private String patientID, finalStatus;

    public ClosedCase(String patientID, String finalStatus) {
        this.patientID = patientID;
        this.finalStatus = finalStatus;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getFinalStatus() {
        return finalStatus;
    }

    public void setFinalStatus(String finalStatus) {
        this.finalStatus = finalStatus;
    }

    @Override
    public String toString() {
        return "ClosedCase{" +
                "patientID='" + patientID + '\'' +
                ", finalStatus='" + finalStatus + '\'' +
                '}';
    }
}
