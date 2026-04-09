package com.example.airportsecurity.Samia;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Handover implements Serializable {
    private String officerName;
    private String shiftType; //  Day, Night
    private String keyNotes;
    private String timestamp;

    public Handover(String officerName, String shiftType, String keyNotes) {
        this.officerName = officerName;
        this.shiftType = shiftType;
        this.keyNotes = keyNotes;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public String getOfficerName() { return officerName; }
    public String getShiftType() { return shiftType; }
    public String getKeyNotes() { return keyNotes; }
    public String getTimestamp() { return timestamp; }
}