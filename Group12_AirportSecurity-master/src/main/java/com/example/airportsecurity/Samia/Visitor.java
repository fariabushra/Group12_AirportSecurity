package com.example.airportsecurity.Samia;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Visitor implements Serializable {
    private String name;
    private String purpose;
    private String idNumber;
    private String entryTime;

    public Visitor(String name, String purpose, String idNumber) {
        this.name = name;
        this.purpose = purpose;
        this.idNumber = idNumber;
        this.entryTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public String getName() { return name; }
    public String getPurpose() { return purpose; }
    public String getIdNumber() { return idNumber; }
    public String getEntryTime() { return entryTime; }
}