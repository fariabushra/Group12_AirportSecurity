package com.example.airportsecurity.Ahsan.CommandingOfficer;

import java.io.Serializable;

public class Announcement implements Serializable {

    private String message;

    public Announcement(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
