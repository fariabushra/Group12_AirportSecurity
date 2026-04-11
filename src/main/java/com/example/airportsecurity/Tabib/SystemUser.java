package com.example.airportsecurity.Tabib;

public class SystemUser
{
    private String serialNo;
    private String name;
    private String employeeId;
    private String role;
    private String accountStatus;

    public SystemUser(String serialNo, String name, String employeeId, String role, String accountStatus) {
        this.serialNo = serialNo;
        this.name = name;
        this.employeeId = employeeId;
        this.role = role;
        this.accountStatus = accountStatus;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    @Override
    public String toString() {
        return "SystemUser{" +
                "serialNo='" + serialNo + '\'' +
                ", name='" + name + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", role='" + role + '\'' +
                ", accountStatus='" + accountStatus + '\'' +
                '}';
    }
}