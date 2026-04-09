package com.example.airportsecurity.Samia;

import java.io.Serializable;

public class LostItem implements Serializable {
    private String itemName;
    private String category;
    private String locationFound;

    public LostItem(String itemName, String category, String locationFound) {
        this.itemName = itemName;
        this.category = category;
        this.locationFound = locationFound;
    }

    public String getItemName() { return itemName; }
    public String getCategory() { return category; }
    public String getLocationFound() { return locationFound; }
}