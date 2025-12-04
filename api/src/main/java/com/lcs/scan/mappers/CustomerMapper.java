package com.lcs.scan.mappers;

import com.lcs.scan.dtos.customer.CustomerCreateDto;
import com.lcs.scan.dtos.customer.CustomerResponseDto;
import com.lcs.scan.enums.customer.CustomerStatus;
import com.lcs.scan.models.Customer;
import com.lcs.scan.repositorys.projections.CustomerProjection;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

public class CustomerMapper {
    private static final ModelMapper mapper = new ModelMapper();

    static {
        mapper.typeMap(CustomerCreateDto.class, Customer.class).addMappings(map -> {
            map.skip(Customer::setId);
            map.skip(Customer::setCreatedAt);
            map.skip(Customer::setStatus);
        });
    }

    public static Customer createToResponse (CustomerCreateDto createDto) {
        Customer entity = mapper.map(createDto, Customer.class);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setStatus(CustomerStatus.ACTIVE);
        return entity;
    }

    public static CustomerResponseDto entityToResponse (Customer entity) {
        return mapper.map(entity, CustomerResponseDto.class);
    }

    public static CustomerResponseDto projectionToResponse (CustomerProjection projection) {
        return mapper.map(projection, CustomerResponseDto.class);
    }
}
