package com.example.airportsecurity.Faria;

import java.io.Serializable;

public class SupportRequest implements Serializable {
    private String supportType, location, reason;

    public SupportRequest(String supportType, String location, String reason) {
        this.supportType = supportType;
        this.location = location;
        this.reason = reason;
    }

    public String getSupportType() {
        return supportType;
    }

    public void setSupportType(String supportType) {
        this.supportType = supportType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "SupportRequest{" +
                "supportType='" + supportType + '\'' +
                ", location='" + location + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}
