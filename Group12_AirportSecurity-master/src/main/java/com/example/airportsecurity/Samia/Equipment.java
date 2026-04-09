package com.example.airportsecurity.Samia;

import java.io.Serializable;

public class Equipment implements Serializable {
    private String name;
    private String serialNumber;
    private String condition;

    public Equipment(String name, String serialNumber, String condition) {
        this.name = name;
        this.serialNumber = serialNumber;
        this.condition = condition;
    }

    public String getName() { return name; }
    public String getSerialNumber() { return serialNumber; }
    public String getCondition() { return condition; }
}