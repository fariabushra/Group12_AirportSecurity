package com.example.airportsecurity.Faria;

import java.io.Serializable;

public class MedicalEmergency implements Serializable {
    private String patientName, patientID, emergencyType, severity, initialObservation;


    public MedicalEmergency(String patientName, String patientID, String emergencyType, String severity, String initialObservation) {
        this.patientName = patientName;
        this.patientID = patientID;
        this.emergencyType = emergencyType;
        this.severity = severity;
        this.initialObservation = initialObservation;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getEmergencyType() {
        return emergencyType;
    }

    public void setEmergencyType(String emergencyType) {
        this.emergencyType = emergencyType;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getInitialObservation() {
        return initialObservation;
    }

    public void setInitialObservation(String initialObservation) {
        this.initialObservation = initialObservation;
    }

    @Override
    public String toString() {
        return "MedicalEmergency{" +
                "patientName='" + patientName + '\'' +
                ", patientID='" + patientID + '\'' +
                ", emergencyType='" + emergencyType + '\'' +
                ", severity='" + severity + '\'' +
                ", initialObservation='" + initialObservation + '\'' +
                '}';
    }
}
