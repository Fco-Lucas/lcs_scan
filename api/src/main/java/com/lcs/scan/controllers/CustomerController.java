package com.lcs.scan.controllers;

import com.lcs.scan.dtos.PageableDto;
import com.lcs.scan.dtos.customer.CustomerCreateDto;
import com.lcs.scan.dtos.customer.CustomerResponseDto;
import com.lcs.scan.dtos.customer.CustomerUpdateDto;
import com.lcs.scan.enums.customer.CustomerStatus;
import com.lcs.scan.mappers.PageableMapper;
import com.lcs.scan.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.basepath}customers")
public class CustomerController {
    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDto> createCustomer (
            @RequestBody @Valid CustomerCreateDto createDto
    ) {
        CustomerResponseDto responseDto = service.create(createDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<PageableDto> getAllCustomersPageable (
        Pageable pageable,
        @RequestParam(required = false) String cpfCnpj,
        @RequestParam(required = false) String name,
        @RequestParam(required = false) CustomerStatus status
    ) {
        Page<CustomerResponseDto> entries = service.getAllPageable(
                pageable,
                cpfCnpj,
                name,
                status
        );
        return ResponseEntity.status(HttpStatus.OK).body(PageableMapper.toDto(entries));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> getCustomerById (
            @PathVariable Long id
    ) {
        CustomerResponseDto responseDto = service.getByIdDto(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> updateCustomer (
            @PathVariable Long id,
            @RequestBody @Valid CustomerUpdateDto updateDto
    ) {
        CustomerResponseDto responseDto = service.update(id, updateDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> deleteCustomer (
            @PathVariable Long id
    ) {
        CustomerResponseDto responseDto = service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PostMapping("/{id}/restore")
    public ResponseEntity<CustomerResponseDto> restoreCustomer (
            @PathVariable Long id
    ) {
        CustomerResponseDto responseDto = service.restore(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
