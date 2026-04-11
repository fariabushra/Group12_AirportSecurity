package com.example.airportsecurity.Tabib;

public class ServiceStatus
{
    private String serialNo;
    private String serviceName;
    private String serviceType;
    private String status;
    private String lastChecked;

    public ServiceStatus(String serialNo, String serviceName, String serviceType, String status, String lastChecked) {
        this.serialNo = serialNo;
        this.serviceName = serviceName;
        this.serviceType = serviceType;
        this.status = status;
        this.lastChecked = lastChecked;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLastChecked() {
        return lastChecked;
    }

    public void setLastChecked(String lastChecked) {
        this.lastChecked = lastChecked;
    }

    @Override
    public String toString() {
        return "ServiceStatus{" +
                "serialNo='" + serialNo + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", serviceType='" + serviceType + '\'' +
                ", status='" + status + '\'' +
                ", lastChecked='" + lastChecked + '\'' +
                '}';
    }
}