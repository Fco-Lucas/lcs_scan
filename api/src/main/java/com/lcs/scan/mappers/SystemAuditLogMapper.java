package com.lcs.scan.mappers;

import com.lcs.scan.dtos.systemAuditLog.SystemAuditLogCreateDto;
import com.lcs.scan.dtos.systemAuditLog.SystemAuditLogResponseDto;
import com.lcs.scan.models.SystemAuditLog;
import com.lcs.scan.repositorys.projections.SystemAuditLogProjection;
import org.modelmapper.ModelMapper;

public class SystemAuditLogMapper {
    private static final ModelMapper mapper = new ModelMapper();

    static {
        mapper.typeMap(SystemAuditLogCreateDto.class, SystemAuditLog.class).addMappings(map -> {
            map.skip(SystemAuditLog::setId);
            map.skip(SystemAuditLog::setCreatedAt);
        });
    }

    public static SystemAuditLog createToEntity (SystemAuditLogCreateDto createDto) {
        return mapper.map(createDto, SystemAuditLog.class);
    }

    public static SystemAuditLogResponseDto projectionToResponse (SystemAuditLogProjection projection) {
        return mapper.map(projection, SystemAuditLogResponseDto.class);
    }

    public static SystemAuditLogResponseDto entityToResponse (SystemAuditLog entity) {
        return mapper.map(entity, SystemAuditLogResponseDto.class);
    }
}
