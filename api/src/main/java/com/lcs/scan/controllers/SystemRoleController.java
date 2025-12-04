package com.lcs.scan.controllers;

import com.lcs.scan.dtos.PageableDto;
import com.lcs.scan.dtos.systemRole.SystemRoleCreateDto;
import com.lcs.scan.dtos.systemRole.SystemRoleResponseDto;
import com.lcs.scan.dtos.systemRole.SystemRoleUpdateDto;
import com.lcs.scan.mappers.PageableMapper;
import com.lcs.scan.services.SystemRoleService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.basepath}system_roles")
public class SystemRoleController {
    private final SystemRoleService service;

    public SystemRoleController(SystemRoleService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SystemRoleResponseDto> createSystemRole (
            @RequestBody @Valid SystemRoleCreateDto createDto
    ) {
        SystemRoleResponseDto responseDto = service.create(createDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<PageableDto> getAllSystemRolesPageable (
            Pageable pageable,
            @RequestParam(required = false) String name
    ) {
        Page<SystemRoleResponseDto> responseDto = service.getAllPageable(pageable, name);
        return ResponseEntity.status(HttpStatus.OK).body(PageableMapper.toDto(responseDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SystemRoleResponseDto> getSystemRoleById (
            @PathVariable Long id
    ) {
        SystemRoleResponseDto responseDto = service.getByIdDto(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SystemRoleResponseDto> updateSystemRole (
            @PathVariable Long id,
            @RequestBody @Valid SystemRoleUpdateDto updateDto
    ) {
        SystemRoleResponseDto responseDto = service.update(id, updateDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSystemRole (
            @PathVariable Long id
    ) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
