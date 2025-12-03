package com.lcs.scan.repositorys.projections;

import com.lcs.scan.enums.systemAuditLog.ActionSystemAuditLog;

import java.time.LocalDateTime;

public interface SystemAuditLogProjection {
    Long getId();
    LocalDateTime getCreatedAt();
    Long getSystemUserId();
    ActionSystemAuditLog getAction();
    Object getOldData();
    Object getNewData();
    String getIpAddress();
    String getUserAgent();
}
