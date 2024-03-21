package com.rently.rentlyAPI.services;

import com.rently.rentlyAPI.dto.BuildingDto;
import com.rently.rentlyAPI.dto.CompanyDto;
import com.rently.rentlyAPI.dto.CondoDto;
import com.rently.rentlyAPI.entity.Building;
import com.rently.rentlyAPI.entity.Company;
import com.rently.rentlyAPI.entity.Condo;

import java.util.List;

public interface CondoService {

    // Find a Condo Dto by ID
    CondoDto findCondoDtoById(Integer condoId);

    // Find a Condo Entity by ID
    Condo findCondoEntityById(Integer condoId);
    
    Condo findByUnitNumberAndBuildingId(Integer unitNumber, Integer buildingId);
    
    Condo findCondoEntityByRegistrationKey(String registrationKey);

    // Create a new Condo
    CondoDto createCondo(CondoDto condoDto);

    // Update an existing Company
    CondoDto updateCondo(CondoDto condoDto);

    // Delete a Company by ID
    void deleteCondoById(Integer condoId);

    // Retrieve all Condos
    List<CondoDto> getAllCondos();

    // Retrieve all Condos by Building ID
    List<CondoDto> getAllCondosByBuildingId(Integer buildingId);
    
    
    CondoDto createCondoAndLinkToBuilding(CondoDto condoDto);
    
    boolean keyExists(String registrationKey);
}
