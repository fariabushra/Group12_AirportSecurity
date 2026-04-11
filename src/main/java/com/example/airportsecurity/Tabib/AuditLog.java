package com.example.airportsecurity.Tabib;

import java.time.LocalDate;

public class AuditLog
{
    private String time;
    private String user;
    private String action;
    private String module;
    private String status;
    private String logType;
    private LocalDate logDate;

    public AuditLog(String time, String user, String action, String module, String status, String logType, LocalDate logDate) {
        this.time = time;
        this.user = user;
        this.action = action;
        this.module = module;
        this.status = status;
        this.logType = logType;
        this.logDate = logDate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public LocalDate getLogDate() {
        return logDate;
    }

    public void setLogDate(LocalDate logDate) {
        this.logDate = logDate;
    }

    @Override
    public String toString() {
        return "AuditLog{" +
                "time='" + time + '\'' +
                ", user='" + user + '\'' +
                ", action='" + action + '\'' +
                ", module='" + module + '\'' +
                ", status='" + status + '\'' +
                ", logType='" + logType + '\'' +
                ", logDate=" + logDate +
                '}';
    }
}