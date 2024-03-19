package com.rently.rentlyAPI.services.impl;

import com.rently.rentlyAPI.dto.CondoDto;
import com.rently.rentlyAPI.dto.SystemAdminDto;
import com.rently.rentlyAPI.entity.Building;
import com.rently.rentlyAPI.entity.Condo;
import com.rently.rentlyAPI.entity.enums.CondoStatus;
import com.rently.rentlyAPI.entity.user.SystemAdmin;
import com.rently.rentlyAPI.repository.CondoRepository;
import com.rently.rentlyAPI.services.CondoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CondoServiceImpl implements CondoService {

    private final CondoRepository condoRepository;


    @Override
    public CondoDto findCondoDtoById(Integer condoId) {
        Condo condo = condoRepository.findById(condoId)
                .orElseThrow(() -> new EntityNotFoundException("Condo with ID " + condoId + " not found"));
        return CondoDto.fromEntity(condo);
    }

    @Override
    public Condo findCondoEntityById(Integer condoId) {
        Condo condo = condoRepository.findById(condoId)
                .orElseThrow(() -> new EntityNotFoundException("Condo with ID " + condoId + " not found"));
        return condo;
    }

    @Override
    public CondoDto createCondo(CondoDto condoDto) {
        Condo condo = CondoDto.toEntity(condoDto);
        Condo savedCondo = condoRepository.save(condo);
        return CondoDto.fromEntity(savedCondo);
    }

    @Override
    public CondoDto updateCondo(CondoDto condoDto) {
        // Find the SystemAdmin Entity by its ID
        Condo condoToUpdate = findCondoEntityById(condoDto.getId());

        if (condoDto.getDescription() != null && !condoDto.getDescription().isEmpty()){
            condoToUpdate.setDescription(condoDto.getDescription());
        }

        // update status if present it is a stirng in dto but a enum in entity
        if (condoDto.getStatus() != null && !condoDto.getStatus().isEmpty()){
            try {
                CondoStatus newStatus = CondoStatus.valueOf(condoDto.getStatus().toUpperCase());
                condoToUpdate.setStatus(newStatus);
            } catch (IllegalArgumentException e) {
                // Handle the case where the status string does not match any enum constant
                throw new IllegalArgumentException("Invalid status: " + condoDto.getStatus());
            }
        }

        if(condoDto.getRegistrationKey() != null && !condoDto.getRegistrationKey().isEmpty()){
            condoToUpdate.setRegistrationKey(condoDto.getRegistrationKey());
        }

        // TODO: 2021-10-14  update building, parking, and locker if present

        Condo savedCondo = condoRepository.save(condoToUpdate);
        return CondoDto.fromEntity(savedCondo);
    }

    @Override
    public void deleteCondoById(Integer condoId) {
        Condo condo = condoRepository.findById(condoId)
                .orElseThrow(() -> new EntityNotFoundException("Condo with ID " + condoId + " not found"));
        condoRepository.delete(condo);
    }

    @Override
    public List<CondoDto> getAllCondos() {
        List<Condo> condos = condoRepository.findAll();
        return condos.stream()
                .map(CondoDto::fromEntity)
                .toList();
    }

    @Override
    public List<CondoDto> getAllCondosByBuildingId(Integer buildingId) {
        List<Condo> condos = condoRepository.findAllByBuildingId(buildingId);
        return condos.stream()
                .map(CondoDto::fromEntity)
                .toList();
    }
}
