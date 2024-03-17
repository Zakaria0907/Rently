package com.rently.rentlyAPI.services;

import com.rently.rentlyAPI.dto.BuildingDto;
import com.rently.rentlyAPI.entity.Building;
import com.rently.rentlyAPI.entity.Condo;

import java.util.List;
import java.util.Optional;

public interface BuildingService {
    // Create a new Building
    BuildingDto createBuilding(BuildingDto buildingDto);

    // Retrieve a Building by ID
    Optional<BuildingDto> getBuildingById(Integer id);

    // Update an existing Building
    BuildingDto updateBuilding(Integer id, BuildingDto buildingDto);

    // Delete a Building by ID
    void deleteBuilding(Integer id);

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
