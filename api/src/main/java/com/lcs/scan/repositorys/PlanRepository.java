package com.lcs.scan.repositorys;

import com.lcs.scan.enums.plan.PlanStatus;
import com.lcs.scan.models.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlanRepository extends JpaRepository<Plan, Long> {
    Optional<Plan> findByNameAndStatus(String name, PlanStatus status);
    List<Plan> findAllByStatus(PlanStatus status);
}
