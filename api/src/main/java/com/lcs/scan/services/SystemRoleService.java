package com.lcs.scan.services;

import com.lcs.scan.dtos.systemRole.SystemRoleCreateDto;
import com.lcs.scan.dtos.systemRole.SystemRoleResponseDto;
import com.lcs.scan.dtos.systemRole.SystemRoleUpdateDto;
import com.lcs.scan.exceptions.customExceptions.EntityExistsException;
import com.lcs.scan.exceptions.customExceptions.EntityNotFoundException;
import com.lcs.scan.mappers.SystemRoleMapper;
import com.lcs.scan.models.SystemRole;
import com.lcs.scan.repositorys.SystemRoleRepository;
import com.lcs.scan.repositorys.projections.SystemRoleProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SystemRoleService {
    private final SystemRoleRepository repository;

    public SystemRoleService(SystemRoleRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public SystemRole getByName (String name) {
        return repository.findByName(name).orElse(null);
    }

    @Transactional
    public SystemRoleResponseDto create (SystemRoleCreateDto createDto) {
        SystemRole exits = getByName(createDto.getName());
        if (exits != null) throw new EntityExistsException(String.format("Cargo do sistema com nome: %s já cadastrado no sistema", createDto.getName()));

        SystemRole entity = SystemRoleMapper.createToEntity(createDto);
        SystemRole saved = repository.save(entity);

        return SystemRoleMapper.entityToResponse(saved);
    }

    @Transactional(readOnly = true)
    public Page<SystemRoleResponseDto> getAllPageable (
            Pageable pageable,
            String name
    ) {
        String filterName = name == null ? null : "%" + name + "%";

        Page<SystemRoleProjection> entries = repository.findAllPageable(
                pageable,
                filterName
        );

        return entries.map(SystemRoleMapper::projectionToResponse);
    }

    @Transactional(readOnly = true)
    public SystemRole getById (Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Cargo do sistema com ID: %s não encontrado no sistema", id))
        );
    }

    @Transactional(readOnly = true)
    public SystemRoleResponseDto getByIdDto (Long id) {
        SystemRole entity = getById(id);
        return SystemRoleMapper.entityToResponse(entity);
    }

    @Transactional
    public SystemRoleResponseDto update (Long id, SystemRoleUpdateDto updateDto) {
        SystemRole entity = getById(id);

        if (updateDto.getName() != null && !updateDto.getName().equals(entity.getName())) entity.setName(updateDto.getName());
        if (updateDto.getDescription() != null && !updateDto.getDescription().equals(entity.getDescription())) entity.setDescription(updateDto.getDescription());

        SystemRole saved = repository.save(entity);

        return SystemRoleMapper.entityToResponse(saved);
    }

    @Transactional
    public void delete (Long id) {
        repository.delete(getById(id));
    }
}
