package com.lcs.scan.repositorys;

import com.lcs.scan.enums.customer.CustomerStatus;
import com.lcs.scan.models.Customer;
import com.lcs.scan.repositorys.projections.CustomerProjection;
import com.lcs.scan.repositorys.projections.SystemAuditLogProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query(value = """
        SELECT * FROM customers
        WHERE (:cpfCnpj IS NULL OR cpf_cnpj LIKE CAST(:cpfCnpj AS TEXT))
          AND (:name IS NULL OR name LIKE CAST(:name AS TEXT))
          AND (:status IS NULL OR status = CAST(:status AS TEXT))
        ORDER BY id DESC
    """, nativeQuery = true)
    Page<CustomerProjection> findAllPageable(
            Pageable pageable,
            @Param("cpfCnpj") String cpfCnpj,
            @Param("name") String name,
            @Param("status") String status
    );
    Optional<Customer> findByCpfCnpjAndStatus(String cpfCnpj, CustomerStatus status);

    List<Customer> findAllByIdPlanAndStatus(Long idPlan, CustomerStatus status);
}
