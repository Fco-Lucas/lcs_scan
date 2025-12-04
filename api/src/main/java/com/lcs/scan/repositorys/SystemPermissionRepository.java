package com.lcs.scan.repositorys;

import com.lcs.scan.models.SystemPermission;
import com.lcs.scan.repositorys.projections.SystemPermissionProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SystemPermissionRepository extends JpaRepository<SystemPermission, Long> {
    @Query(value = """
                SELECT * FROM system_permissions
                WHERE (:name IS NULL OR name LIKE :name)
            """, nativeQuery = true)
    Page<SystemPermissionProjection> findAllPageable(
            Pageable pageable,
            @Param("name") String name
    );
}
