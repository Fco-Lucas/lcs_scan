package com.lcs.scan.controllers;

import com.lcs.scan.dtos.PageableDto;
import com.lcs.scan.dtos.systemPermission.SystemPermissionResponseDto;
import com.lcs.scan.mappers.PageableMapper;
import com.lcs.scan.services.SystemPermissionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.basepath}system_permissions")
public class SystemPermissionController {
    private final SystemPermissionService service;

    public SystemPermissionController(SystemPermissionService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<PageableDto> getAllSystemPermissionsPageable (
            Pageable pageable,
            @RequestParam(required = false) String name
    ) {
        Page<SystemPermissionResponseDto> responseDto = service.getAllPageable(pageable, name);
        return ResponseEntity.status(HttpStatus.OK).body(PageableMapper.toDto(responseDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SystemPermissionResponseDto> getSystemPermissionById (
            @PathVariable Long id
    ) {
        SystemPermissionResponseDto responseDto = service.getByIdDto(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
