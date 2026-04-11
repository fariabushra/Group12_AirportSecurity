package com.example.airportsecurity.Tabib;

public class RolePermission
{
    private String serialNo;
    private String roleName;
    private boolean viewCCTV;
    private boolean exportReport;
    private boolean createIncident;
    private boolean manageUsers;
    private boolean systemBackup;

    public RolePermission(String serialNo, String roleName, boolean viewCCTV, boolean exportReport, boolean createIncident, boolean manageUsers, boolean systemBackup) {
        this.serialNo = serialNo;
        this.roleName = roleName;
        this.viewCCTV = viewCCTV;
        this.exportReport = exportReport;
        this.createIncident = createIncident;
        this.manageUsers = manageUsers;
        this.systemBackup = systemBackup;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public boolean isViewCCTV() {
        return viewCCTV;
    }

    public void setViewCCTV(boolean viewCCTV) {
        this.viewCCTV = viewCCTV;
    }

    public boolean isExportReport() {
        return exportReport;
    }

    public void setExportReport(boolean exportReport) {
        this.exportReport = exportReport;
    }

    public boolean isCreateIncident() {
        return createIncident;
    }

    public void setCreateIncident(boolean createIncident) {
        this.createIncident = createIncident;
    }

    public boolean isManageUsers() {
        return manageUsers;
    }

    public void setManageUsers(boolean manageUsers) {
        this.manageUsers = manageUsers;
    }

    public boolean isSystemBackup() {
        return systemBackup;
    }

    public void setSystemBackup(boolean systemBackup) {
        this.systemBackup = systemBackup;
    }

    @Override
    public String toString() {
        return "RolePermission{" +
                "serialNo='" + serialNo + '\'' +
                ", roleName='" + roleName + '\'' +
                ", viewCCTV=" + viewCCTV +
                ", exportReport=" + exportReport +
                ", createIncident=" + createIncident +
                ", manageUsers=" + manageUsers +
                ", systemBackup=" + systemBackup +
                '}';
    }
}