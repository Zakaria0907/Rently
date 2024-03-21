package com.rently.rentlyAPI.services;

import com.rently.rentlyAPI.dto.BuildingDto;
import com.rently.rentlyAPI.dto.CommonFacilityDto;
import com.rently.rentlyAPI.dto.CommonFacilityReservationDto;
import com.rently.rentlyAPI.entity.Building;
import com.rently.rentlyAPI.entity.CommonFacility;
import com.rently.rentlyAPI.entity.user.Occupant;

import java.util.List;
import java.util.Optional;

public interface BuildingService {
    
    // Create a new Building
    BuildingDto createBuilding(BuildingDto buildingDto);

    // Find a Building Dto by ID
    BuildingDto findBuildingDtoById(Integer buildingId);
    
    // Find a Building Entity by ID
    Building findBuildingEntityById(Integer buildingId);

    // Find a Building Entity by Name
    Building findBuildingEntityByName(String buildingName);

    // Update an existing Building
    BuildingDto updateBuilding(BuildingDto buildingDto);

    // Delete a Building by ID
    void deleteBuilding(Integer buildingId);

    // Retrieve all Buildings
    List<BuildingDto> getAllBuildings();

    BuildingDto createBuildingAndLinkToCompany(BuildingDto buildingDto);

    List<BuildingDto> getAllBuildingsByCompanyId(Integer companyId);

    Optional<CommonFacility> findCommonFacilityByName(Integer buildingId, String facilityName);

    CommonFacilityDto createCommonFacility(CommonFacilityDto commonFacilityDto);

    CommonFacilityDto getCommonFacilityById(Integer commonFacilityId);

    List<CommonFacilityDto> getAllCommonFacilitiesByBuildingId(Integer buildingId);

    List<CommonFacilityDto> getAllCommonFacilities();

    void deleteCommonFacilityById(Integer commonFacilityId);

    CommonFacilityReservationDto createCommonFacilityReservation(Occupant occupant, CommonFacilityReservationDto commonFacilityReservationDto);

    void deleteCommonFacilityReservation(Integer occupantId, Integer id);

    CommonFacilityReservationDto getCommonFacilityReservation(Integer occupantId, Integer id);

    List<CommonFacilityReservationDto> getAllCommonFacilityReservations(Integer occupantId);

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
