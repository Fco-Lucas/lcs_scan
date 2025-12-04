package com.lcs.scan.repositorys;

import com.lcs.scan.models.SystemRole;
import com.lcs.scan.repositorys.projections.SystemRoleProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SystemRoleRepository extends JpaRepository<SystemRole, Long> {
    Optional<SystemRole> findByName(String name);
    @Query(value = """
                SELECT * FROM system_roles
                WHERE (:name IS NULL OR name LIKE :name)
            """, nativeQuery = true)
    Page<SystemRoleProjection> findAllPageable(
            Pageable pageable,
            @Param("name") String name
    );
}
