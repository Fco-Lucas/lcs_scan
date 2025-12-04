package com.lcs.scan.repositorys;

import com.lcs.scan.enums.customerUser.CustomerUserStatus;
import com.lcs.scan.models.CustomerUser;
import com.lcs.scan.repositorys.projections.CustomerUserProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CustomerUserRepository extends JpaRepository<CustomerUser, Long> {
    Optional<CustomerUser> findByIdCustomerAndLoginAndStatus(Long idCustomer, String login, CustomerUserStatus status);
    @Query(value = """
                SELECT * FROM customer_users
                WHERE (:idCustomer IS NULL OR id_customer = :idCustomer)
                  AND (:status IS NULL OR status = CAST(:status AS TEXT))
            """, nativeQuery = true)
    Page<CustomerUserProjection> findAllPageable(
            Pageable pageable,
            @Param("idCustomer") Long idCustomer,
            @Param("status") String status
    );
    Optional<CustomerUser> findByIdAndIdCustomer(Long id, Long idCustomer);
    List<CustomerUser> findAllByIdCustomer(Long idCustomer);
    List<CustomerUser> findAllByIdCustomerAndStatus(Long idCustomer, CustomerUserStatus status);
}
