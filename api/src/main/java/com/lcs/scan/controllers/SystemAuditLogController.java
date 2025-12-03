package com.lcs.scan.controllers;

import com.lcs.scan.dtos.PageableDto;
import com.lcs.scan.dtos.systemAuditLog.SystemAuditLogCreateDto;
import com.lcs.scan.dtos.systemAuditLog.SystemAuditLogResponseDto;
import com.lcs.scan.enums.systemAuditLog.ActionSystemAuditLog;
import com.lcs.scan.mappers.PageableMapper;
import com.lcs.scan.services.SystemAuditLogService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.basepath}systemAuditLog")
public class SystemAuditLogController {
    private final SystemAuditLogService service;

    public SystemAuditLogController(SystemAuditLogService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SystemAuditLogResponseDto> createSystemAuditLog (
        @RequestBody @Valid SystemAuditLogCreateDto createDto
    ) {
        SystemAuditLogResponseDto responseDto = service.create(createDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<PageableDto> getAllSystemAuditLogPageable (
        Pageable pageable,
        @RequestParam(required = false) ActionSystemAuditLog action,
        @RequestParam(required = false) String startDate,
        @RequestParam(required = false) String endDate
    ) {
        Page<SystemAuditLogResponseDto> entries = service.getAllPageable(
            pageable,
            action,
            startDate,
            endDate
        );
        return ResponseEntity.status(HttpStatus.OK).body(PageableMapper.toDto(entries));
    }
}
