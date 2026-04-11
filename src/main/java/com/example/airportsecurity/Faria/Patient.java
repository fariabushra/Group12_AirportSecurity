package com.example.airportsecurity.Faria;

import java.io.Serializable;

public class Patient implements Serializable {
    private String patientID, firstAidGiven, updatedStatus;

    public Patient(String patientID, String firstAidGiven, String updatedStatus) {
        this.patientID = patientID;
        this.firstAidGiven = firstAidGiven;
        this.updatedStatus = updatedStatus;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getFirstAidGiven() {
        return firstAidGiven;
    }

    public void setFirstAidGiven(String firstAidGiven) {
        this.firstAidGiven = firstAidGiven;
    }

    public String getUpdatedStatus() {
        return updatedStatus;
    }

    public void setUpdatedStatus(String updatedStatus) {
        this.updatedStatus = updatedStatus;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientID='" + patientID + '\'' +
                ", firstAidGiven='" + firstAidGiven + '\'' +
                ", updatedStatus='" + updatedStatus + '\'' +
                '}';
    }
}
