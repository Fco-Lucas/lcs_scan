package com.lcs.scan.repositorys.projections;

import com.lcs.scan.enums.customer.CustomerStatus;

import java.time.LocalDateTime;

public interface CustomerProjection {
    Long getId();
    LocalDateTime getCreatedAt();
    String getName();
    String getCpfCnpj();
    String getTenantId();
    Long getIdPlan();
    String getUrlToPost();
    CustomerStatus getStatus();
}
