package com.example.airportsecurity.Tabib;

public class SecurityAlert
{
    private String alertId;
    private String time;
    private String location;
    private String camera;
    private String sensorType;
    private String status;

    public SecurityAlert(String alertId, String time, String location, String camera, String sensorType, String status) {
        this.alertId = alertId;
        this.time = time;
        this.location = location;
        this.camera = camera;
        this.sensorType = sensorType;
        this.status = status;
    }

    public String getAlertId() {
        return alertId;
    }

    public void setAlertId(String alertId) {
        this.alertId = alertId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SecurityAlert{" +
                "alertId='" + alertId + '\'' +
                ", time='" + time + '\'' +
                ", location='" + location + '\'' +
                ", camera='" + camera + '\'' +
                ", sensorType='" + sensorType + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}