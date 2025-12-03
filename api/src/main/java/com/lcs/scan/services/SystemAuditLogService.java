package com.lcs.scan.services;

import com.lcs.scan.dtos.systemAuditLog.SystemAuditLogCreateDto;
import com.lcs.scan.dtos.systemAuditLog.SystemAuditLogResponseDto;
import com.lcs.scan.enums.systemAuditLog.ActionSystemAuditLog;
import com.lcs.scan.mappers.SystemAuditLogMapper;
import com.lcs.scan.models.SystemAuditLog;
import com.lcs.scan.repositorys.SystemAuditLogRepository;
import com.lcs.scan.repositorys.projections.SystemAuditLogProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class SystemAuditLogService {
    private static final LocalDateTime POSTGRES_MIN = LocalDateTime.of(1900, 1, 1, 0, 0);
    private static final LocalDateTime POSTGRES_MAX = LocalDateTime.of(2999, 12, 31, 23, 59, 59);

    private final SystemAuditLogRepository repository;

    public SystemAuditLogService(SystemAuditLogRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public SystemAuditLogResponseDto create (SystemAuditLogCreateDto createDto) {
        SystemAuditLog entity = SystemAuditLogMapper.createToEntity(createDto);
        SystemAuditLog saved = repository.save(entity);
        return SystemAuditLogMapper.entityToResponse(saved);
    }

    @Transactional(readOnly = true)
    public Page<SystemAuditLogResponseDto> getAllPageable (
        Pageable pageable,
        ActionSystemAuditLog action,
        String startDate,
        String endDate
    ) {
        String filterAction = action == null ? null : action.toString();
        LocalDateTime start = (startDate == null || startDate.isBlank()) ? POSTGRES_MIN : LocalDateTime.parse(startDate);
        LocalDateTime end = (endDate == null || endDate.isBlank()) ? POSTGRES_MAX : LocalDateTime.parse(endDate);

        Page<SystemAuditLogProjection> entries = repository.findAllPageable(
                pageable,
                filterAction,
                start,
                end
        );

        return entries.map(SystemAuditLogMapper::projectionToResponse);
    }
}
