package com.lcs.scan.repositorys.projections;

import com.lcs.scan.enums.systemUser.SystemUserStatus;

import java.time.LocalDateTime;

public interface SystemUserProjection {
    Long getId();
    LocalDateTime getCreatedAt();
    String getName();
    String getEmail();
    String getPassword();
    SystemUserStatus getStatus();
}
