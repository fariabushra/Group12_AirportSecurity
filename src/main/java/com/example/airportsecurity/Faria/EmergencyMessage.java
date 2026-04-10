package com.example.airportsecurity.Faria;

import java.io.Serializable;

public class EmergencyMessage implements Serializable {
    private String subject, details;

    public EmergencyMessage(String details, String subject) {
        this.details = details;
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "EmergencyMessage{" +
                "subject='" + subject + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
