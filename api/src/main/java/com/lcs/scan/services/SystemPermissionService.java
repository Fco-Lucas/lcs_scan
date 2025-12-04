package com.lcs.scan.services;

import com.lcs.scan.dtos.systemPermission.SystemPermissionResponseDto;
import com.lcs.scan.exceptions.customExceptions.EntityNotFoundException;
import com.lcs.scan.mappers.SystemPermissionMapper;
import com.lcs.scan.models.SystemPermission;
import com.lcs.scan.repositorys.SystemPermissionRepository;
import com.lcs.scan.repositorys.projections.SystemPermissionProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SystemPermissionService {
    private final SystemPermissionRepository repository;

    public SystemPermissionService(SystemPermissionRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Page<SystemPermissionResponseDto> getAllPageable (
            Pageable pageable,
            String name
    ) {
        String filterName = name == null ? null : "%" + name + "%";

        Page<SystemPermissionProjection> entries = repository.findAllPageable(
                pageable,
                filterName
        );

        return entries.map(SystemPermissionMapper::projectionToResponse);
    }

    @Transactional(readOnly = true)
    public SystemPermission getById (Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Permissão do sistema com ID: %s não encontrado no sistema", id))
        );
    }

    @Transactional(readOnly = true)
    public SystemPermissionResponseDto getByIdDto (Long id) {
        SystemPermission entity = getById(id);
        return SystemPermissionMapper.entityToResponse(entity);
    }
}
