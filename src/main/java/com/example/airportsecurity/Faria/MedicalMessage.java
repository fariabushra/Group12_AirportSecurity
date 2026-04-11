package com.example.airportsecurity.Faria;

import java.io.Serializable;

public class MedicalMessage implements Serializable {
    private String receiver, subject, details;

    public MedicalMessage(String receiver, String subject, String details) {
        this.receiver = receiver;
        this.subject = subject;
        this.details = details;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
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
        return "MedicalMessage{" +
                "receiver='" + receiver + '\'' +
                ", subject='" + subject + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
