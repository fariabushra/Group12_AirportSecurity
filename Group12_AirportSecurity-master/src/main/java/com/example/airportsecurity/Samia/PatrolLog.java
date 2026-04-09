package com.example.airportsecurity.Samia;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PatrolLog implements Serializable {
    private String zone;
    private String status;
    private String timestamp;

    public PatrolLog(String zone, String status) {
        this.zone = zone;
        this.status = status;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public String getZone() { return zone; }
    public String getStatus() { return status; }
    public String getTimestamp() { return timestamp; }
}