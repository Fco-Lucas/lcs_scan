package com.lcs.scan.mappers;

import com.lcs.scan.dtos.customerUser.CustomerUserCreateDto;
import com.lcs.scan.dtos.customerUser.CustomerUserResponseDto;
import com.lcs.scan.enums.customerUser.CustomerUserStatus;
import com.lcs.scan.models.CustomerUser;
import com.lcs.scan.repositorys.projections.CustomerUserProjection;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

public class CustomerUserMapper {
    private static final ModelMapper mapper = new ModelMapper();

    static {
        mapper.typeMap(CustomerUserCreateDto.class, CustomerUser.class).addMappings(map -> {
            map.skip(CustomerUser::setId);
            map.skip(CustomerUser::setCreatedAt);
            map.skip(CustomerUser::setStatus);
        });
    }

    public static CustomerUser createToEntity (Long idCustomer, CustomerUserCreateDto createDto) {
        CustomerUser entity = mapper.map(createDto, CustomerUser.class);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setIdCustomer(idCustomer);
        entity.setStatus(CustomerUserStatus.ACTIVE);
        return entity;
    }

    public static CustomerUserResponseDto entityToResponse (CustomerUser entity) {
        return mapper.map(entity, CustomerUserResponseDto.class);
    }

    public static CustomerUserResponseDto projectionToResponse (CustomerUserProjection projection) {
        return mapper.map(projection, CustomerUserResponseDto.class);
    }
}
