package com.lcs.scan.services;

import com.lcs.scan.dtos.systemUserPermission.SystemUserPermissionCreateDto;
import com.lcs.scan.dtos.systemUserPermission.SystemUserPermissionResponseDto;
import com.lcs.scan.exceptions.customExceptions.EntityExistsException;
import com.lcs.scan.exceptions.customExceptions.EntityNotFoundException;
import com.lcs.scan.mappers.SystemUserPermissionMapper;
import com.lcs.scan.models.SystemUserPermission;
import com.lcs.scan.repositorys.SystemUserPermissionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SystemUserPermissionService {
    private final SystemUserPermissionRepository repository;

    public SystemUserPermissionService(SystemUserPermissionRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public SystemUserPermissionResponseDto create (SystemUserPermissionCreateDto createDto) {
        SystemUserPermission exists = getBySystemUserIdAndPermissionId(createDto.getSystemUserId(), createDto.getPermissionId());
        if (exists != null) throw new EntityExistsException(String.format("Relação com SYSTEM_USER_ID: %s e PERMISSION_ID: %s já cadastrada no sistema", createDto.getSystemUserId(), createDto.getPermissionId()));

        SystemUserPermission entity = SystemUserPermissionMapper.createToEntity(createDto);
        SystemUserPermission saved = repository.save(entity);

        return SystemUserPermissionMapper.entityToResponse(saved);
    }

    @Transactional(readOnly = true)
    public SystemUserPermission getById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Relação entre usuário e permissão com ID: %s não encontrada", id))
        );
    }

    @Transactional(readOnly = true)
    public SystemUserPermissionResponseDto getByIdDto (Long id) {
        SystemUserPermission entity = getById(id);
        return SystemUserPermissionMapper.entityToResponse(entity);
    }

    @Transactional(readOnly = true)
    public SystemUserPermission getBySystemUserIdAndPermissionId (Long systemUserId, Long permissionId) {
        return repository.findBySystemUserIdAndPermissionId(systemUserId, permissionId).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<SystemUserPermission> getAllBySystemUserId (Long systemUserId) {
        return repository.findAllBySystemUserId(systemUserId);
    }

    @Transactional(readOnly = true)
    public List<SystemUserPermission> getAllByPermissionId (Long permissionId) {
        return repository.findAllByPermissionId(permissionId);
    }

    @Transactional
    public void delete (Long id) {
        repository.delete(getById(id));
    }
}
