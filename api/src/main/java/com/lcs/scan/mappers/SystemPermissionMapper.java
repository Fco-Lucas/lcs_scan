package com.lcs.scan.mappers;

import com.lcs.scan.dtos.systemPermission.SystemPermissionResponseDto;
import com.lcs.scan.models.SystemPermission;
import com.lcs.scan.repositorys.projections.SystemPermissionProjection;
import org.modelmapper.ModelMapper;

public class SystemPermissionMapper {
    private static final ModelMapper mapper = new ModelMapper();

    public static SystemPermissionResponseDto entityToResponse (SystemPermission entity) {
        return mapper.map(entity, SystemPermissionResponseDto.class);
    }

    public static SystemPermissionResponseDto projectionToResponse (SystemPermissionProjection projection) {
        return mapper.map(projection, SystemPermissionResponseDto.class);
    }
}
