package com.lcs.scan.repositorys;

import com.lcs.scan.models.SystemRolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SystemRolePermissionRepository extends JpaRepository<SystemRolePermission, Long> {
    Optional<SystemRolePermission> findByRoleIdAndPermissionId(Long roleId, Long permissionId);
    List<SystemRolePermission> findAllByRoleId(Long roleId);
    List<SystemRolePermission> findAllByPermissionId(Long permissionId);
}
