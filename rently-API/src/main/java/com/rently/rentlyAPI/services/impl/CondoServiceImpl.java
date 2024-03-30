package com.rently.rentlyAPI.services.impl;

import com.rently.rentlyAPI.dto.BuildingDto;
import com.rently.rentlyAPI.dto.CondoDto;
import com.rently.rentlyAPI.dto.SystemAdminDto;
import com.rently.rentlyAPI.entity.Building;
import com.rently.rentlyAPI.entity.Condo;
import com.rently.rentlyAPI.entity.enums.CondoStatus;
import com.rently.rentlyAPI.entity.user.SystemAdmin;
import com.rently.rentlyAPI.repository.CondoRepository;
import com.rently.rentlyAPI.services.BuildingService;
import com.rently.rentlyAPI.services.CondoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CondoServiceImpl implements CondoService {

    private final CondoRepository condoRepository;
    
    private final BuildingService buildingService;


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
    public Condo findByUnitNumberAndBuildingId(Integer unitNumber, Integer buildingId) {
        Condo condo = condoRepository.findByUnitNumberAndBuildingId(unitNumber, buildingId)
            .orElseThrow(() -> new EntityNotFoundException("Condo with unit number " + unitNumber +
                " not found in building with ID " + buildingId));
        return condo;
    }
    
    @Override
    public Condo findCondoEntityByRegistrationKey(String registrationKey) {
        Condo condo = condoRepository.findCondoByRegistrationKey(registrationKey)
                .orElseThrow(() -> new EntityNotFoundException("Condo with registration key " + registrationKey + " not found"));
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

        // update status if present it is a string in dto but a enum in entity
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
        // just to throw an exception if building does not exist
        Building building = buildingService.findBuildingEntityById(buildingId);
        
        List<Condo> condos = condoRepository.findAllByBuildingId(buildingId);
        return condos.stream()
                .map(CondoDto::fromEntity)
                .toList();
    }
    
    public CondoDto createCondoAndLinkToBuilding(CondoDto condoDto){
        Optional<Condo> existingCondo = condoRepository.findByUnitNumberAndBuildingId(condoDto.getUnitNumber(), condoDto.getBuildingId());
        
        if (existingCondo.isPresent()){
            throw new IllegalArgumentException("Condo with unit number " + condoDto.getUnitNumber() +
                " already exists in building with ID " + condoDto.getBuildingId());
        }
        
        Building buildingToLink = buildingService.findBuildingEntityById(condoDto.getBuildingId());
        String condoAddress = buildingToLink.getAddress() + " " + condoDto.getUnitNumber();
        
        Condo condo = CondoDto.toEntity(condoDto);
        
        condo.setBuilding(buildingToLink);
        condo.setAddress(condoAddress);
        condo.setStatus(CondoStatus.AVAILABLE);
        
        Condo savedCondo = condoRepository.save(condo);
        
        return CondoDto.fromEntity(savedCondo);
    }
    
    @Override
    public boolean keyExists(String registrationKey) {
        return condoRepository.existsByRegistrationKey(registrationKey);
    }
}
