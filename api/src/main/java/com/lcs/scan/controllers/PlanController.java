package com.lcs.scan.controllers;

import com.lcs.scan.dtos.plan.PlanCreateDto;
import com.lcs.scan.dtos.plan.PlanResponseDto;
import com.lcs.scan.dtos.plan.PlanUpdateDto;
import com.lcs.scan.enums.plan.PlanStatus;
import com.lcs.scan.services.PlanService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.basepath}plans")
public class PlanController {
    private final PlanService service;

    public PlanController(PlanService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PlanResponseDto> createPlan (
        @RequestBody @Valid PlanCreateDto createDto
    ) {
        System.out.println(createDto);
        PlanResponseDto responseDto = service.create(createDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<PlanResponseDto>> getAllPlans (
        @RequestParam(required = false) PlanStatus status
    ) {
        List<PlanResponseDto> responseDto = service.getAll(status);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanResponseDto> getPlanById (
        @PathVariable Long id
    ) {
        PlanResponseDto responseDto = service.getByIdDto(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PlanResponseDto> updatePlan (
        @PathVariable Long id,
        @RequestBody @Valid PlanUpdateDto updateDto
    ) {
        PlanResponseDto responseDto = service.update(id, updateDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PlanResponseDto> deletePlan (
        @PathVariable Long id
    ) {
        PlanResponseDto responseDto = service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PostMapping("/{id}/restore")
    public ResponseEntity<PlanResponseDto> restorePlan (
            @PathVariable Long id
    ) {
        PlanResponseDto responseDto = service.restore(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
