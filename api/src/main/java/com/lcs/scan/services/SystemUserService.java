package com.lcs.scan.services;

import com.lcs.scan.dtos.systemUser.SystemUserCreateDto;
import com.lcs.scan.dtos.systemUser.SystemUserResponseDto;
import com.lcs.scan.dtos.systemUser.SystemUserUpdateDto;
import com.lcs.scan.dtos.systemUser.SystemUserUpdatePasswordDto;
import com.lcs.scan.enums.systemUser.SystemUserStatus;
import com.lcs.scan.exceptions.customExceptions.EntityExistsException;
import com.lcs.scan.exceptions.customExceptions.EntityNotFoundException;
import com.lcs.scan.mappers.SystemUserMapper;
import com.lcs.scan.models.SystemUser;
import com.lcs.scan.repositorys.SystemUserRepository;
import com.lcs.scan.repositorys.projections.SystemUserProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SystemUserService {
    private final SystemUserRepository repository;

    public SystemUserService(SystemUserRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public SystemUser getByEmailAndStatus (String email, SystemUserStatus status) {
        return repository.findByEmailAndStatus(email, status).orElse(null);
    }

    @Transactional
    public SystemUserResponseDto create (SystemUserCreateDto createDto) {
        SystemUser exists = getByEmailAndStatus(createDto.getEmail(), SystemUserStatus.ACTIVE);
        if (exists != null) throw new EntityExistsException(String.format("Usuário do sistema com email: %s já ativo no sistema", createDto.getEmail()));

        SystemUser entity = SystemUserMapper.createToEntity(createDto);
        SystemUser saved = repository.save(entity);

        return SystemUserMapper.entityToResponse(saved);
    }

    @Transactional(readOnly = true)
    public Page<SystemUserResponseDto> getAllPageable (
            Pageable pageable,
            String name,
            SystemUserStatus status
    ) {
        String filterStatus = status == null ? null : status.toString();
        String filterName = name == null ? null : "%" + name + "%";

        Page<SystemUserProjection> entries = repository.findAllPageable(
                pageable,
                filterName,
                filterStatus
        );

        return entries.map(SystemUserMapper::projectionToResponse);
    }

    @Transactional(readOnly = true)
    public SystemUser getById (Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Usuário do sistema com ID: %s não encontrado no sistema", id))
        );
    }

    @Transactional(readOnly = true)
    public SystemUserResponseDto getByIdDto (Long id) {
        SystemUser entity = getById(id);
        return SystemUserMapper.entityToResponse(entity);
    }

    @Transactional
    public SystemUserResponseDto update (Long id, SystemUserUpdateDto updateDto) {
        SystemUser entity = getById(id);

        if (updateDto.getName() != null && !updateDto.getName().equals(entity.getName())) entity.setName(updateDto.getName());
        if (updateDto.getEmail() != null && !updateDto.getEmail().equals(entity.getEmail())) {
            SystemUser exists = getByEmailAndStatus(updateDto.getEmail(), SystemUserStatus.ACTIVE);
            if (exists != null && !exists.getId().equals(id)) throw new EntityExistsException(String.format("Usuário do sistema com email: %s já ativo no sistema", updateDto.getEmail()));
            entity.setEmail(updateDto.getEmail());
        }

        SystemUser saved = repository.save(entity);

        return SystemUserMapper.entityToResponse(saved);
    }

    @Transactional
    public void updatePassword (Long id, SystemUserUpdatePasswordDto updatePasswordDto) {
        SystemUser entity = getById(id);

        String currentPassword = updatePasswordDto.getCurrentPassword();
        String newPassword = updatePasswordDto.getNewPassword();
        String confirmNewPassword = updatePasswordDto.getConfirmNewPassword();

        if (!currentPassword.equals(entity.getPassword())) throw new RuntimeException("Senha atual informada inválida");

        if (!newPassword.equals(confirmNewPassword)) throw new RuntimeException("A nova senha deve ser igual a confirmação da nova senha");

        entity.setPassword(newPassword);
        repository.save(entity);
    }

    @Transactional
    public SystemUserResponseDto delete (Long id) {
        SystemUser entity = getById(id);
        entity.setStatus(SystemUserStatus.INACTIVE);
        SystemUser saved = repository.save(entity);
        return SystemUserMapper.entityToResponse(saved);
    }

    @Transactional
    public SystemUserResponseDto restore (Long id) {
        SystemUser entity = getById(id);
        entity.setStatus(SystemUserStatus.ACTIVE);
        SystemUser saved = repository.save(entity);
        return SystemUserMapper.entityToResponse(saved);
    }
}
