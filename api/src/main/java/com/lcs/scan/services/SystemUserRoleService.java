package com.lcs.scan.services;

import com.lcs.scan.dtos.systemUserRole.SystemUserRoleCreateDto;
import com.lcs.scan.dtos.systemUserRole.SystemUserRoleResponseDto;
import com.lcs.scan.exceptions.customExceptions.EntityExistsException;
import com.lcs.scan.exceptions.customExceptions.EntityNotFoundException;
import com.lcs.scan.mappers.SystemUserRoleMapper;
import com.lcs.scan.models.SystemUserRole;
import com.lcs.scan.repositorys.SystemUserRoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SystemUserRoleService {
    private final SystemUserRoleRepository repository;

    public SystemUserRoleService(SystemUserRoleRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public SystemUserRoleResponseDto create (SystemUserRoleCreateDto createDto) {
        SystemUserRole exists = getBySystemUserIdAndRoleId(createDto.getSystemUserId(), createDto.getRoleId());
        if (exists != null) throw new EntityExistsException(String.format("Relação com SYSTEM_USER_ID: %s e ROLE_ID: %s já cadastrada no sistema", createDto.getSystemUserId(), createDto.getRoleId()));

        SystemUserRole entity = SystemUserRoleMapper.createToEntity(createDto);
        SystemUserRole saved = repository.save(entity);

        return SystemUserRoleMapper.entityToResponse(saved);
    }

    @Transactional(readOnly = true)
    public SystemUserRole getById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Relação entre usuário e cargo com ID: %s não encontrada", id))
        );
    }

    @Transactional(readOnly = true)
    public SystemUserRoleResponseDto getByIdDto (Long id) {
        SystemUserRole entity = getById(id);
        return SystemUserRoleMapper.entityToResponse(entity);
    }

    @Transactional(readOnly = true)
    public SystemUserRole getBySystemUserIdAndRoleId (Long systemUserId, Long roleId) {
        return repository.findBySystemUserIdAndRoleId(systemUserId, roleId).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<SystemUserRole> getAllBySystemUserId (Long systemUserId) {
        return repository.findAllBySystemUserId(systemUserId);
    }

    @Transactional(readOnly = true)
    public List<SystemUserRole> getAllByRoleId (Long roleId) {
        return repository.findAllByRoleId(roleId);
    }

    @Transactional
    public void delete (Long id) {
        repository.delete(getById(id));
    }
}
