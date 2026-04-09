package com.example.airportsecurity.Samia;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Incident implements Serializable {
    private String title;
    private String description;
    private LocalDateTime dateTime;

    public Incident(String title, String description) {
        this.title = title;
        this.description = description;
        this.dateTime = LocalDateTime.now();
    }


    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public LocalDateTime getDateTime() { return dateTime; }
}