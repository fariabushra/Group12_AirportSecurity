package com.example.airportsecurity.Faria;

import java.io.Serializable;

public class CaseProgress implements Serializable {
    private String patientID, progressStatus;

    public CaseProgress(String patientID, String progressStatus) {
        this.patientID = patientID;
        this.progressStatus = progressStatus;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getProgressStatus() {
        return progressStatus;
    }

    public void setProgressStatus(String progressStatus) {
        this.progressStatus = progressStatus;
    }

    @Override
    public String toString() {
        return "CaseProgress{" +
                "patientID='" + patientID + '\'' +
                ", progressStatus='" + progressStatus + '\'' +
                '}';
    }
}
