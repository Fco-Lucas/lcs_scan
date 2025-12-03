package com.lcs.scan.dtos.systemAuditLog;

import com.lcs.scan.enums.systemAuditLog.ActionSystemAuditLog;
import jakarta.validation.constraints.NotNull;

public class SystemAuditLogCreateDto {
    @NotNull(message = "O campo 'systemUserId' é obrigatório")
    private Long systemUserId;
    @NotNull(message = "O campo 'ActionSystemAuditLog' é obrigatório")
    private ActionSystemAuditLog action;
    private Object oldData;
    private Object newData;
    private String ipAddress;
    private String userAgent;

    public SystemAuditLogCreateDto() {
    }

    public SystemAuditLogCreateDto(Long systemUserId, ActionSystemAuditLog action, Object oldData, Object newData, String ipAddress, String userAgent) {
        this.systemUserId = systemUserId;
        this.action = action;
        this.oldData = oldData;
        this.newData = newData;
        this.ipAddress = ipAddress;
        this.userAgent = userAgent;
    }

    public Long getSystemUserId() {
        return systemUserId;
    }

    public void setSystemUserId(Long systemUserId) {
        this.systemUserId = systemUserId;
    }

    public ActionSystemAuditLog getAction() {
        return action;
    }

    public void setAction(ActionSystemAuditLog action) {
        this.action = action;
    }

    public Object getOldData() {
        return oldData;
    }

    public void setOldData(Object oldData) {
        this.oldData = oldData;
    }

    public Object getNewData() {
        return newData;
    }

    public void setNewData(Object newData) {
        this.newData = newData;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    @Override
    public String toString() {
        return "SystemAuditLogCreateDto{" +
                "systemUserId=" + systemUserId +
                ", action=" + action +
                ", oldData=" + oldData +
                ", newData=" + newData +
                ", ipAddress='" + ipAddress + '\'' +
                ", userAgent='" + userAgent + '\'' +
                '}';
    }
}
