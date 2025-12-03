package com.lcs.scan.repositorys;

import com.lcs.scan.models.SystemAuditLog;
import com.lcs.scan.repositorys.projections.SystemAuditLogProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface SystemAuditLogRepository extends JpaRepository<SystemAuditLog, Long> {
    @Query(value = """
        SELECT 
            A.id,
            A.created_at,
            A.system_user_id,
            A.action,
            A.old_data,
            A.new_data,
            A.ip_address,
            A.user_agent
        FROM system_audit_log A
        WHERE (:action IS NULL OR A.action = CAST(:action AS TEXT))
          AND A.created_at BETWEEN :startDate AND :endDate 
        ORDER BY A.id DESC
    """, nativeQuery = true)
    Page<SystemAuditLogProjection> findAllPageable(
            Pageable pageable,
            @Param("action") String action,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );
}
