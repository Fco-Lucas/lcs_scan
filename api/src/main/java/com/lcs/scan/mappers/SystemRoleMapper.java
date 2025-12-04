package com.lcs.scan.mappers;

import com.lcs.scan.dtos.systemRole.SystemRoleCreateDto;
import com.lcs.scan.dtos.systemRole.SystemRoleResponseDto;
import com.lcs.scan.models.SystemRole;
import com.lcs.scan.repositorys.projections.SystemRoleProjection;
import org.modelmapper.ModelMapper;

public class SystemRoleMapper {
    private static final ModelMapper mapper = new ModelMapper();

    static {
        mapper.typeMap(SystemRoleCreateDto.class, SystemRole.class).addMappings(map -> {
            map.skip(SystemRole::setId);
        });
    }

    public static SystemRole createToEntity (SystemRoleCreateDto createDto) {
        return mapper.map(createDto, SystemRole.class);
    }

    public static SystemRoleResponseDto entityToResponse (SystemRole entity) {
        return mapper.map(entity, SystemRoleResponseDto.class);
    }

    public static SystemRoleResponseDto projectionToResponse (SystemRoleProjection projection) {
        return mapper.map(projection, SystemRoleResponseDto.class);
    }
}
