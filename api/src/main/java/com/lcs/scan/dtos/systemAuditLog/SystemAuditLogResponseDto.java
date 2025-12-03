package com.lcs.scan.dtos.systemAuditLog;

import com.lcs.scan.enums.systemAuditLog.ActionSystemAuditLog;

import java.time.LocalDateTime;

public class SystemAuditLogResponseDto {
    private Long id;
    private LocalDateTime createdAt;
    private Long systemUserId;
    private ActionSystemAuditLog action;
    private Object oldData;
    private Object newData;
    private String ipAddress;
    private String userAgent;

    public SystemAuditLogResponseDto() {
    }

    public SystemAuditLogResponseDto(Long id, LocalDateTime createdAt, Long systemUserId, ActionSystemAuditLog action, Object oldData, Object newData, String ipAddress, String userAgent) {
        this.id = id;
        this.createdAt = createdAt;
        this.systemUserId = systemUserId;
        this.action = action;
        this.oldData = oldData;
        this.newData = newData;
        this.ipAddress = ipAddress;
        this.userAgent = userAgent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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
        return "SystemAuditLogResponseDto{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", systemUserId=" + systemUserId +
                ", action=" + action +
                ", oldData=" + oldData +
                ", newData=" + newData +
                ", ipAddress='" + ipAddress + '\'' +
                ", userAgent='" + userAgent + '\'' +
                '}';
    }
}
