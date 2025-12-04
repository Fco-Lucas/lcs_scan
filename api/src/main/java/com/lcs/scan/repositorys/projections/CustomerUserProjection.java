package com.lcs.scan.repositorys.projections;

import com.lcs.scan.enums.customerUser.CustomerUserStatus;

import java.time.LocalDateTime;

public interface CustomerUserProjection {
    Long getId();
    LocalDateTime getCreatedAt();
    Long getIdCustomer();
    String getLogin();
    String getPassword();
    CustomerUserStatus getStatus();
}
