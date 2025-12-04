package com.lcs.scan.controllers;

import com.lcs.scan.dtos.PageableDto;
import com.lcs.scan.dtos.systemUser.SystemUserCreateDto;
import com.lcs.scan.dtos.systemUser.SystemUserResponseDto;
import com.lcs.scan.dtos.systemUser.SystemUserUpdateDto;
import com.lcs.scan.dtos.systemUser.SystemUserUpdatePasswordDto;
import com.lcs.scan.enums.systemUser.SystemUserStatus;
import com.lcs.scan.mappers.PageableMapper;
import com.lcs.scan.services.SystemUserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.basepath}system_users")
public class SystemUserController {
    private final SystemUserService service;

    public SystemUserController(SystemUserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SystemUserResponseDto> createSystemUser (
            @RequestBody @Valid SystemUserCreateDto createDto
    ) {
        SystemUserResponseDto responseDto = service.create(createDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<PageableDto> getAllSystemUsersPageable (
            Pageable pageable,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) SystemUserStatus status
    ) {
        Page<SystemUserResponseDto> responseDto = service.getAllPageable(pageable, name, status);
        return ResponseEntity.status(HttpStatus.OK).body(PageableMapper.toDto(responseDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SystemUserResponseDto> getSystemUserById (
            @PathVariable Long id
    ) {
        SystemUserResponseDto responseDto = service.getByIdDto(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SystemUserResponseDto> updateSystemUser (
            @PathVariable Long id,
            @RequestBody @Valid SystemUserUpdateDto updateDto
    ) {
        SystemUserResponseDto responseDto = service.update(id, updateDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PostMapping("/{id}/updatePassword")
    public ResponseEntity<SystemUserResponseDto> updateSystemUserPassword (
            @PathVariable Long id,
            @RequestBody @Valid SystemUserUpdatePasswordDto updatePasswordDto
    ) {
        service.updatePassword(id, updatePasswordDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SystemUserResponseDto> deleteSystemUser (
            @PathVariable Long id
    ) {
        SystemUserResponseDto responseDto = service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PostMapping("/{id}/restore")
    public ResponseEntity<SystemUserResponseDto> restoreSystemUser (
            @PathVariable Long id
    ) {
        SystemUserResponseDto responseDto = service.restore(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
