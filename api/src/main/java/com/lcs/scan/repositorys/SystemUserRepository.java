package com.lcs.scan.repositorys;

import com.lcs.scan.enums.systemUser.SystemUserStatus;
import com.lcs.scan.models.SystemUser;
import com.lcs.scan.repositorys.projections.SystemUserProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SystemUserRepository extends JpaRepository<SystemUser, Long> {
    Optional<SystemUser> findByEmailAndStatus(String email, SystemUserStatus status);

    @Query(value = """
                SELECT * FROM system_users
                WHERE (:name IS NULL OR name LIKE :name)
                  AND (:status IS NULL OR status = CAST(:status AS TEXT))
            """, nativeQuery = true)
    Page<SystemUserProjection> findAllPageable(
            Pageable pageable,
            @Param("name") String name,
            @Param("status") String status
    );
}
