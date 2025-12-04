package com.lcs.scan.repositorys;

import com.lcs.scan.models.SystemUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SystemUserRoleRepository extends JpaRepository<SystemUserRole, Long> {
    Optional<SystemUserRole> findBySystemUserIdAndRoleId(Long systemUserId, Long roleId);
    List<SystemUserRole> findAllBySystemUserId(Long systemUserId);
    List<SystemUserRole> findAllByRoleId(Long roleId);
}
