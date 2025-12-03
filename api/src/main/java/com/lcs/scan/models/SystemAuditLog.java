package com.lcs.scan.models;

import com.lcs.scan.enums.systemAuditLog.ActionSystemAuditLog;
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "system_audit_logs")
public class SystemAuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false, name = "created_at", columnDefinition = "TIMESTAMPTZ")
    private LocalDateTime createdAt;

    @Column(name = "system_user_id", nullable = false)
    private Long systemUserId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ActionSystemAuditLog action;

    @Type(JsonBinaryType.class)
    @Column(name = "old_data", columnDefinition = "jsonb")
    private Object oldData;

    @Type(JsonBinaryType.class)
    @Column(name = "new_data", columnDefinition = "jsonb")
    private Object newData;

    @Column(name = "ip_address", length = 45)
    private String ipAddress;

    @Column(name = "user_agent")
    private String userAgent;

    public SystemAuditLog() {
    }

    public SystemAuditLog(Long id, LocalDateTime createdAt, Long systemUserId, ActionSystemAuditLog action, Object oldData, Object newData, String ipAddress, String userAgent) {
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
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SystemAuditLog systemAuditLog = (SystemAuditLog) o;
        return Objects.equals(id, systemAuditLog.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "SystemAuditLog{" +
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
