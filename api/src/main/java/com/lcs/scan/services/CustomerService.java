package com.lcs.scan.services;

import com.lcs.scan.dtos.customer.CustomerCreateDto;
import com.lcs.scan.dtos.customer.CustomerResponseDto;
import com.lcs.scan.dtos.customer.CustomerUpdateDto;
import com.lcs.scan.enums.customer.CustomerStatus;
import com.lcs.scan.enums.plan.PlanStatus;
import com.lcs.scan.exceptions.customExceptions.EntityExistsException;
import com.lcs.scan.exceptions.customExceptions.EntityNotFoundException;
import com.lcs.scan.mappers.CustomerMapper;
import com.lcs.scan.models.Customer;
import com.lcs.scan.models.Plan;
import com.lcs.scan.repositorys.CustomerRepository;
import com.lcs.scan.repositorys.projections.CustomerProjection;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository repository;
    private final PlanService planService;

    public CustomerService(
            CustomerRepository repository,
            @Lazy PlanService planService
    ) {
        this.repository = repository;
        this.planService = planService;
    }

    @Transactional(readOnly = true)
    public Customer getByCpfCnpjAndStatus (String cpfCnpj, CustomerStatus status) {
        return repository.findByCpfCnpjAndStatus(cpfCnpj, status).orElse(null);
    }

    @Transactional
    public CustomerResponseDto create (CustomerCreateDto createDto) {
        String cpfCnpj = createDto.getCpfCnpj();

        Customer exists = getByCpfCnpjAndStatus(cpfCnpj, CustomerStatus.ACTIVE);
        if (exists != null) throw new EntityExistsException(String.format("Cliente com CPF/CNPJ: %s já cadastrado no sistema", cpfCnpj));

        Plan existsPlan = planService.getById(createDto.getIdPlan());
        if (!existsPlan.getStatus().equals(PlanStatus.ACTIVE)) throw new EntityNotFoundException("O plano informado está desativado");

        Customer entity = CustomerMapper.createToResponse(createDto);
        Customer saved = repository.save(entity);

        return CustomerMapper.entityToResponse(saved);
    }

    @Transactional(readOnly = true)
    public Page<CustomerResponseDto> getAllPageable(
            Pageable pageable,
            String cpfCnpj,
            String name,
            CustomerStatus status
    ) {
        String filterCpfCnpj = cpfCnpj == null ? null : "%" + cpfCnpj + "%";
        String filterName = name == null ? null : "%" + name + "%";
        String filterStatus = status == null ? null : status.toString();
        Page<CustomerProjection> entries = repository.findAllPageable(
                pageable,
                filterCpfCnpj,
                filterName,
                filterStatus
        );
        return entries.map(CustomerMapper::projectionToResponse);
    }

    @Transactional(readOnly = true)
    public List<Customer> getAllByIdPlanAndStatus (Long idPlan, CustomerStatus status) {
        return repository.findAllByIdPlanAndStatus(idPlan, status);
    }

    @Transactional(readOnly = true)
    public Customer getById (Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Cliente com ID: %s não encontrado", id))
        );
    }

    @Transactional(readOnly = true)
    public CustomerResponseDto getByIdDto (Long id) {
        Customer entity = getById(id);
        return CustomerMapper.entityToResponse(entity);
    }

    @Transactional
    public CustomerResponseDto update (Long id, CustomerUpdateDto updateDto) {
        Customer entity = getById(id);

        if (updateDto.getName() != null && !updateDto.getName().equals(entity.getName())) entity.setName(updateDto.getName());
        if (updateDto.getIdPlan() != null && !updateDto.getIdPlan().equals(entity.getIdPlan())) {
            Plan existsPlan = planService.getById(updateDto.getIdPlan());
            if (!existsPlan.getStatus().equals(PlanStatus.ACTIVE)) throw new EntityNotFoundException("O plano informado está desativado");
            entity.setIdPlan(updateDto.getIdPlan());
        }
        if (updateDto.getUrlToPost() != null && !updateDto.getUrlToPost().equals(entity.getUrlToPost())) entity.setUrlToPost(updateDto.getUrlToPost());

        Customer saved = repository.save(entity);
        return CustomerMapper.entityToResponse(saved);
    }

    @Transactional
    public CustomerResponseDto delete (Long id) {
        Customer entity = getById(id);
        entity.setStatus(CustomerStatus.INACTIVE);
        Customer saved = repository.save(entity);
        return CustomerMapper.entityToResponse(saved);
    }

    @Transactional
    public CustomerResponseDto restore (Long id) {
        Customer entity = getById(id);
        entity.setStatus(CustomerStatus.ACTIVE);
        Customer saved = repository.save(entity);
        return CustomerMapper.entityToResponse(saved);
    }
}
