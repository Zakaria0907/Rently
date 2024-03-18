package com.rently.rentlyAPI.services;

import com.rently.rentlyAPI.dto.BuildingDto;
import com.rently.rentlyAPI.entity.Building;

import java.util.List;

public interface BuildingService {
    
    // Create a new Building
    BuildingDto createBuilding(BuildingDto buildingDto);

    // Find a Building Dto by ID
    BuildingDto findBuildingDtoById(Integer buildingId);
    
    // Find a Building Entity by ID
    Building findBuildingEntityById(Integer buildingId);

    // Update an existing Building
    BuildingDto updateBuilding(BuildingDto buildingDto);

    // Delete a Building by ID
    void deleteBuilding(Integer buildingId);

    // Retrieve all Buildings
    List<BuildingDto> getAllBuildings();

//
//	public Building save(Building building);
//
//	public Building update(BuildingDto buildingDto, Building building);
//
//	public void delete(Building building);
//
////	public boolean exists(Building building);
//
//	public boolean exists(Integer buildingId);
//
//	// Returns all buildings by company id
//	public List<Building> findAllByCompanyId(Integer companyId);
//
//	// Returns a building by id
//	public Building findById(Integer id);


}
