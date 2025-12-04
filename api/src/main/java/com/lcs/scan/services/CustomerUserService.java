package com.lcs.scan.services;

import com.lcs.scan.dtos.customerUser.CustomerUserCreateDto;
import com.lcs.scan.dtos.customerUser.CustomerUserResponseDto;
import com.lcs.scan.dtos.customerUser.CustomerUserUpdateDto;
import com.lcs.scan.dtos.customerUser.CustomerUserUpdatePasswordDto;
import com.lcs.scan.enums.customerUser.CustomerUserStatus;
import com.lcs.scan.exceptions.customExceptions.EntityExistsException;
import com.lcs.scan.exceptions.customExceptions.EntityNotFoundException;
import com.lcs.scan.mappers.CustomerUserMapper;
import com.lcs.scan.models.CustomerUser;
import com.lcs.scan.repositorys.CustomerUserRepository;
import com.lcs.scan.repositorys.projections.CustomerUserProjection;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerUserService {
    private final CustomerUserRepository repository;
    private final CustomerService customerService;

    public CustomerUserService(
        CustomerUserRepository repository,
        @Lazy CustomerService customerService
    ) {
        this.repository = repository;
        this.customerService = customerService;
    }

    @Transactional(readOnly = true)
    public CustomerUser getByIdCustomerAndLoginAndStatus (Long idCustomer, String login, CustomerUserStatus status) {
        return repository.findByIdCustomerAndLoginAndStatus(idCustomer, login, status).orElse(null);
    }

    @Transactional
    public CustomerUserResponseDto create (Long idCustomer, CustomerUserCreateDto createDto) {
        CustomerUser exists = getByIdCustomerAndLoginAndStatus(idCustomer, createDto.getLogin(), CustomerUserStatus.ACTIVE);
        if (exists != null) throw new EntityExistsException(String.format("Usuário com login: %s ativo no sistema", createDto.getLogin()));

        customerService.getById(idCustomer);

        CustomerUser entity = CustomerUserMapper.createToEntity(idCustomer, createDto);
        CustomerUser saved = repository.save(entity);

        return CustomerUserMapper.entityToResponse(saved);
    }

//    @Transactional(readOnly = true)
//    public Page<CustomerUserResponseDto> getAllPageable (
//            Pageable pageable,
//            Long idCustomer,
//            CustomerUserStatus status
//    ) {
//        String filterStatus = status == null ? null : status.toString();
//
//        Page<CustomerUserProjection> entries = repository.findAllPageable(
//                pageable,
//                idCustomer,
//                filterStatus
//        );
//
//        return entries.map(CustomerUserMapper::projectionToResponse);
//    }

    @Transactional(readOnly = true)
    public List<CustomerUserResponseDto> getAllByIdCustomer (Long idCustomer, CustomerUserStatus status) {
        customerService.getById(idCustomer);
        List<CustomerUser> entries = status == null ? repository.findAllByIdCustomer(idCustomer) : repository.findAllByIdCustomerAndStatus(idCustomer, status);
        return entries.stream().map(CustomerUserMapper::entityToResponse).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CustomerUser getById (Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Usuário com ID: %s não encontrado no sistema", id))
        );
    }


    @Transactional(readOnly = true)
    public CustomerUser getByIdAndIdCustomer (Long id, Long idCustomer) {
        return repository.findByIdAndIdCustomer(id, idCustomer).orElseThrow(
                () -> new EntityNotFoundException(String.format("Usuário com ID: %s não encontrado no sistema pertencente ao cliente com ID: %s", id, idCustomer))
        );
    }

    @Transactional(readOnly = true)
    public CustomerUserResponseDto getByIdAndIdCustomerDto (Long id, Long idCustomer) {
        CustomerUser entity = getByIdAndIdCustomer(id, idCustomer);
        return CustomerUserMapper.entityToResponse(entity);
    }

    @Transactional
    public CustomerUserResponseDto update (Long id, Long idCustomer, CustomerUserUpdateDto updateDto) {
        CustomerUser entity = getByIdAndIdCustomer(id, idCustomer);

        if (updateDto.getLogin() != null && !updateDto.getLogin().equals(entity.getLogin())) {
            CustomerUser exists = getByIdCustomerAndLoginAndStatus(idCustomer, updateDto.getLogin(), CustomerUserStatus.ACTIVE);
            if (exists != null && !exists.getId().equals(id)) throw new EntityExistsException(String.format("Usuário com login: %s já ativo no sistema", updateDto.getLogin()));
            entity.setLogin(updateDto.getLogin());
        }

        CustomerUser saved = repository.save(entity);

        return CustomerUserMapper.entityToResponse(saved);
    }

    @Transactional
    public void updatePassword (Long id, Long idCustomer, CustomerUserUpdatePasswordDto updatePasswordDto) {
        CustomerUser entity = getByIdAndIdCustomer(id, idCustomer);

        String currentPassword = updatePasswordDto.getCurrentPassword();
        String newPassword = updatePasswordDto.getNewPassword();
        String confirmNewPassword = updatePasswordDto.getConfirmNewPassword();

        if (!currentPassword.equals(entity.getPassword())) throw new RuntimeException("Senha atual informada inválida");

        if (!newPassword.equals(confirmNewPassword)) throw new RuntimeException("A nova senha deve ser igual a confirmação da nova senha");

        entity.setPassword(newPassword);
        repository.save(entity);
    }

    @Transactional
    public CustomerUserResponseDto delete (Long id, Long idCustomer) {
        CustomerUser entity = getByIdAndIdCustomer(id, idCustomer);
        entity.setStatus(CustomerUserStatus.INACTIVE);
        CustomerUser saved = repository.save(entity);
        return CustomerUserMapper.entityToResponse(saved);
    }

    @Transactional
    public CustomerUserResponseDto restore (Long id, Long idCustomer) {
        CustomerUser entity = getByIdAndIdCustomer(id, idCustomer);
        entity.setStatus(CustomerUserStatus.ACTIVE);
        CustomerUser saved = repository.save(entity);
        return CustomerUserMapper.entityToResponse(saved);
    }
}
