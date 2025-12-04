package com.lcs.scan.mappers;

import com.lcs.scan.dtos.systemUserRole.SystemUserRoleCreateDto;
import com.lcs.scan.dtos.systemUserRole.SystemUserRoleResponseDto;
import com.lcs.scan.models.SystemUserRole;
import org.modelmapper.ModelMapper;

public class SystemUserRoleMapper {
    private static final ModelMapper mapper = new ModelMapper();

    static {
        mapper.typeMap(SystemUserRoleCreateDto.class, SystemUserRole.class).addMappings(map -> {
            map.skip(SystemUserRole::setId);
        });
    }

    public static SystemUserRole createToEntity (SystemUserRoleCreateDto createDto) {
        return mapper.map(createDto, SystemUserRole.class);
    }

    public static SystemUserRoleResponseDto entityToResponse (SystemUserRole entity) {
        return mapper.map(entity, SystemUserRoleResponseDto.class);
    }
}
