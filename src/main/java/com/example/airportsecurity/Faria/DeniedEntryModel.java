package com.example.airportsecurity.Faria;

import java.io.Serializable;

public class DeniedEntryModel implements Serializable {
    private String personID, reason, date;

    public DeniedEntryModel(String personID, String reason, String date) {
        this.personID = personID;
        this.reason = reason;
        this.date = date;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "DeniedEntryModel{" +
                "personID='" + personID + '\'' +
                ", reason='" + reason + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
