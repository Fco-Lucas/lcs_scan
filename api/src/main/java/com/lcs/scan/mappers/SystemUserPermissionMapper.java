package com.lcs.scan.mappers;

import com.lcs.scan.dtos.systemUserPermission.SystemUserPermissionCreateDto;
import com.lcs.scan.dtos.systemUserPermission.SystemUserPermissionResponseDto;
import com.lcs.scan.models.SystemUserPermission;
import org.modelmapper.ModelMapper;

public class SystemUserPermissionMapper {
    private static final ModelMapper mapper = new ModelMapper();

    static {
        mapper.typeMap(SystemUserPermissionCreateDto.class, SystemUserPermission.class).addMappings(map -> {
            map.skip(SystemUserPermission::setId);
        });
    }

    public static SystemUserPermission createToEntity (SystemUserPermissionCreateDto createDto) {
        return mapper.map(createDto, SystemUserPermission.class);
    }

    public static SystemUserPermissionResponseDto entityToResponse (SystemUserPermission entity) {
        return mapper.map(entity, SystemUserPermissionResponseDto.class);
    }
}
