package com.lcs.scan.services;

import com.lcs.scan.dtos.plan.PlanCreateDto;
import com.lcs.scan.dtos.plan.PlanResponseDto;
import com.lcs.scan.dtos.plan.PlanUpdateDto;
import com.lcs.scan.enums.customer.CustomerStatus;
import com.lcs.scan.enums.plan.PlanStatus;
import com.lcs.scan.exceptions.customExceptions.EntityExistsException;
import com.lcs.scan.exceptions.customExceptions.EntityNotFoundException;
import com.lcs.scan.mappers.PlanMapper;
import com.lcs.scan.models.Customer;
import com.lcs.scan.models.Plan;
import com.lcs.scan.repositorys.PlanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanService {
    private final PlanRepository repository;
    private final CustomerService customerService;

    public PlanService(PlanRepository repository, CustomerService customerService) {
        this.repository = repository;
        this.customerService = customerService;
    }

    @Transactional(readOnly = true)
    public Plan getByNameAndStatus (String name, PlanStatus status) {
        return repository.findByNameAndStatus(name, status).orElse(null);
    }

    @Transactional
    public PlanResponseDto create (PlanCreateDto createDto) {
        Plan exists = getByNameAndStatus(createDto.getName(), PlanStatus.ACTIVE);
        if (exists != null) throw new EntityExistsException(String.format("Plano com nome %s ativo no sistema", createDto.getName()));

        Plan entity = PlanMapper.createToEntity(createDto);
        Plan saved = repository.save(entity);
        return PlanMapper.planToResponse(saved);
    }

    @Transactional(readOnly = true)
    public List<PlanResponseDto> getAll (PlanStatus status) {
        List<Plan> plans = status != null ? repository.findAllByStatus(status) : repository.findAll();
        if (plans.isEmpty()) return new ArrayList<>();

        return plans.stream().map(PlanMapper::planToResponse).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Plan getById (Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Plano com ID: %s não encontrado", id))
        );
    }

    @Transactional(readOnly = true)
    public PlanResponseDto getByIdDto (Long id) {
        Plan entity = getById(id);
        return PlanMapper.planToResponse(entity);
    }

    @Transactional
    public PlanResponseDto update (Long id, PlanUpdateDto updateDto) {
        Plan entity = getById(id);

        if (updateDto.getName() != null && !updateDto.getName().equals(entity.getName())) {
            // Verifica se o nome já está em uso
            Plan exists = getByNameAndStatus(updateDto.getName(), PlanStatus.ACTIVE);
            if (exists != null && !exists.getId().equals(entity.getId())) throw new EntityExistsException(String.format("Plano com nome: %s ativo no sistema", updateDto.getName()));
            entity.setName(updateDto.getName());
        }
        if (updateDto.getImagesAvailable() != null && !updateDto.getImagesAvailable().equals(entity.getImagesAvailable())) entity.setImagesAvailable(updateDto.getImagesAvailable());
        if (updateDto.getObservations() != null && !updateDto.getObservations().equals(entity.getObservations())) entity.setObservations(updateDto.getObservations());

        Plan saved = repository.save(entity);
        return PlanMapper.planToResponse(saved);
    }

    @Transactional
    public PlanResponseDto delete (Long id) {
        Plan entity = getById(id);

        // Verifica se tem algum cliente usando esse plano
        List<Customer> customersUsingThisPlan = customerService.getAllByIdPlanAndStatus(id, CustomerStatus.ACTIVE);
        if (!customersUsingThisPlan.isEmpty()) throw new RuntimeException("O plano informado possui clientes ativos utilizando-o, para remove-lo, substitua o plano destes clientes");

        entity.setStatus(PlanStatus.INACTIVE);
        Plan saved = repository.save(entity);
        return PlanMapper.planToResponse(saved);
    }

    @Transactional
    public PlanResponseDto restore (Long id) {
        Plan entity = getById(id);
        entity.setStatus(PlanStatus.ACTIVE);
        Plan saved = repository.save(entity);
        return PlanMapper.planToResponse(saved);
    }
}
