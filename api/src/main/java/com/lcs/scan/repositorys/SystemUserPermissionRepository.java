package com.lcs.scan.repositorys;

import com.lcs.scan.models.SystemUserPermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SystemUserPermissionRepository extends JpaRepository<SystemUserPermission, Long> {
    Optional<SystemUserPermission> findBySystemUserIdAndPermissionId (Long systemUserId, Long permissionId);
    List<SystemUserPermission> findAllBySystemUserId (Long systemUserId);
    List<SystemUserPermission> findAllByPermissionId (Long permissionId);
}
