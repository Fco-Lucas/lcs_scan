package com.lcs.scan.mappers;

import com.lcs.scan.dtos.systemUser.SystemUserCreateDto;
import com.lcs.scan.dtos.systemUser.SystemUserResponseDto;
import com.lcs.scan.enums.systemUser.SystemUserStatus;
import com.lcs.scan.models.SystemUser;
import com.lcs.scan.repositorys.projections.SystemUserProjection;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

public class SystemUserMapper {
    private static final ModelMapper mapper = new ModelMapper();

    static {
        mapper.typeMap(SystemUserCreateDto.class, SystemUser.class).addMappings(map -> {
            map.skip(SystemUser::setId);
            map.skip(SystemUser::setCreatedAt);
            map.skip(SystemUser::setStatus);
        });
    }

    public static SystemUser createToEntity (SystemUserCreateDto createDto) {
        SystemUser entity = mapper.map(createDto, SystemUser.class);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setStatus(SystemUserStatus.ACTIVE);
        return entity;
    }

    public static SystemUserResponseDto entityToResponse (SystemUser entity) {
        return mapper.map(entity, SystemUserResponseDto.class);
    }

    public static SystemUserResponseDto projectionToResponse (SystemUserProjection projection) {
        return mapper.map(projection, SystemUserResponseDto.class);
    }
}
