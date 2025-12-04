package com.lcs.scan.mappers;

import com.lcs.scan.dtos.systemRolePermission.SystemRolePermissionCreateDto;
import com.lcs.scan.dtos.systemRolePermission.SystemRolePermissionResponseDto;
import com.lcs.scan.models.SystemRolePermission;
import org.modelmapper.ModelMapper;

public class SystemRolePermissionMapper {
    private static final ModelMapper mapper = new ModelMapper();

    static {
        mapper.typeMap(SystemRolePermissionCreateDto.class, SystemRolePermission.class).addMappings(map -> {
            map.skip(SystemRolePermission::setId);
        });
    }

    public static SystemRolePermission createToEntity (SystemRolePermissionCreateDto createDto) {
        return mapper.map(createDto, SystemRolePermission.class);
    }

    public static SystemRolePermissionResponseDto entityToResponse (SystemRolePermission entity) {
        return mapper.map(entity, SystemRolePermissionResponseDto.class);
    }
}
