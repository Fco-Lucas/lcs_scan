package com.lcs.scan.controllers;

import com.lcs.scan.dtos.PageableDto;
import com.lcs.scan.dtos.customerUser.CustomerUserCreateDto;
import com.lcs.scan.dtos.customerUser.CustomerUserResponseDto;
import com.lcs.scan.dtos.customerUser.CustomerUserUpdateDto;
import com.lcs.scan.dtos.customerUser.CustomerUserUpdatePasswordDto;
import com.lcs.scan.enums.customerUser.CustomerUserStatus;
import com.lcs.scan.mappers.PageableMapper;
import com.lcs.scan.services.CustomerUserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.basepath}customers/{idCustomer}/users")
public class CustomerUserController {
    private final CustomerUserService service;

    public CustomerUserController(CustomerUserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CustomerUserResponseDto> createCustomerUser (
            @PathVariable Long idCustomer,
            @RequestBody @Valid CustomerUserCreateDto createDto
    ) {
        CustomerUserResponseDto responseDto = service.create(idCustomer, createDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<CustomerUserResponseDto>> getAllCustomerUsers (
            @PathVariable Long idCustomer,
            @RequestParam(required = false) CustomerUserStatus status
    ) {
        List<CustomerUserResponseDto> responseDto = service.getAllByIdCustomer(idCustomer, status);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerUserResponseDto> getCustomerUserById (
            @PathVariable Long id
    ) {
        CustomerUserResponseDto responseDto = service.getByIdDto(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerUserResponseDto> updateCustomerUser (
            @PathVariable Long idCustomer,
            @PathVariable Long id,
            @RequestBody @Valid CustomerUserUpdateDto updateDto
    ) {
        CustomerUserResponseDto responseDto = service.update(idCustomer, id, updateDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PatchMapping("/{id}/updatePassword")
    public ResponseEntity<CustomerUserResponseDto> updatePasswordCustomerUser (
            @PathVariable Long id,
            @RequestBody @Valid CustomerUserUpdatePasswordDto updatePasswordDto
    ) {
        service.updatePassword(id, updatePasswordDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerUserResponseDto> deleteCustomerUser (
            @PathVariable Long id
    ) {
        CustomerUserResponseDto responseDto = service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PostMapping("/{id}/restore")
    public ResponseEntity<CustomerUserResponseDto> restoreCustomerUser (
            @PathVariable Long id
    ) {
        CustomerUserResponseDto responseDto = service.restore(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
