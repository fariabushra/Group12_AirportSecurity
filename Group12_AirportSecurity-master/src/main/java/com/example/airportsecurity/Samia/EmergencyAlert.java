package com.example.airportsecurity.Samia;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EmergencyAlert implements Serializable {
    private String alertType;
    private String location;
    private String severity;
    private String timestamp;

    public EmergencyAlert(String alertType, String location, String severity) {
        this.alertType = alertType;
        this.location = location;
        this.severity = severity;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String getAlertType() { return alertType; }
    public String getLocation() { return location; }
    public String getSeverity() { return severity; }
    public String getTimestamp() { return timestamp; }
}