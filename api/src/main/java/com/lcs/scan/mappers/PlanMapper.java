package com.lcs.scan.mappers;

import com.lcs.scan.dtos.plan.PlanCreateDto;
import com.lcs.scan.dtos.plan.PlanResponseDto;
import com.lcs.scan.enums.plan.PlanStatus;
import com.lcs.scan.models.Plan;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

public class PlanMapper {
    private static final ModelMapper mapper = new ModelMapper();

    static {
        mapper.typeMap(PlanCreateDto.class, Plan.class).addMappings(map -> {
            map.skip(Plan::setId);
        });
    }

    public static Plan createToEntity (PlanCreateDto createDto) {
        Plan plan = mapper.map(createDto, Plan.class);
        plan.setCreatedAt(LocalDateTime.now());
        plan.setStatus(PlanStatus.ACTIVE);
        return plan;
    }

    public static PlanResponseDto planToResponse (Plan entity) {
        return mapper.map(entity, PlanResponseDto.class);
    }
}
