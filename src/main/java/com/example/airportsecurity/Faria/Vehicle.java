package com.example.airportsecurity.Faria;

import java.io.Serializable;

public class Vehicle implements Serializable {

    private String vehicleNo, vehicleType, driverName, driverID, entryExit;

    public Vehicle(String vehicleNo, String vehicleType, String driverName, String driverID, String entryExit) {
        this.vehicleNo = vehicleNo;
        this.vehicleType = vehicleType;
        this.driverName = driverName;
        this.driverID = driverID;
        this.entryExit = entryExit;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverID() {
        return driverID;
    }

    public void setDriverID(String driverID) {
        this.driverID = driverID;
    }

    public String getEntryExit() {
        return entryExit;
    }

    public void setEntryExit(String entryExit) {
        this.entryExit = entryExit;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleNo='" + vehicleNo + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", driverName='" + driverName + '\'' +
                ", driverID='" + driverID + '\'' +
                ", entryExit='" + entryExit + '\'' +
                '}';
    }
}
