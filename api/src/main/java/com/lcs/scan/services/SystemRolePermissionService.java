package com.lcs.scan.services;

import com.lcs.scan.dtos.systemRolePermission.SystemRolePermissionCreateDto;
import com.lcs.scan.dtos.systemRolePermission.SystemRolePermissionResponseDto;
import com.lcs.scan.exceptions.customExceptions.EntityExistsException;
import com.lcs.scan.exceptions.customExceptions.EntityNotFoundException;
import com.lcs.scan.mappers.SystemRolePermissionMapper;
import com.lcs.scan.models.SystemRolePermission;
import com.lcs.scan.repositorys.SystemRolePermissionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SystemRolePermissionService {
    private final SystemRolePermissionRepository repository;

    public SystemRolePermissionService(SystemRolePermissionRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public SystemRolePermissionResponseDto create (SystemRolePermissionCreateDto createDto) {
        SystemRolePermission exists = getByRoleIdAndPermissionId(createDto.getRoleId(), createDto.getPermissionId());
        if (exists != null) throw new EntityExistsException(String.format("Permissão com ROLE_ID: %s e PERMISSION_ID: %s já cadastrada no sistema", createDto.getRoleId(), createDto.getPermissionId()));

        SystemRolePermission entity = SystemRolePermissionMapper.createToEntity(createDto);
        SystemRolePermission saved = repository.save(entity);

        return SystemRolePermissionMapper.entityToResponse(saved);
    }

    @Transactional(readOnly = true)
    public SystemRolePermission getById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Relação entre cargo e permissão com ID: %s não encontrada", id))
        );
    }

    @Transactional(readOnly = true)
    public SystemRolePermissionResponseDto getByIdDto (Long id) {
        SystemRolePermission entity = getById(id);
        return SystemRolePermissionMapper.entityToResponse(entity);
    }

    @Transactional(readOnly = true)
    public SystemRolePermission getByRoleIdAndPermissionId (Long roleId, Long permissionId) {
        return repository.findByRoleIdAndPermissionId(roleId, permissionId).orElse(null);
    }

    @Transactional
    public void delete (Long id) {
        repository.delete(getById(id));
    }
}
