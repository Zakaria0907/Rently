package com.rently.rentlyAPI.services.impl;

import com.rently.rentlyAPI.dto.BuildingDto;
import com.rently.rentlyAPI.dto.CommonFacilityDto;
import com.rently.rentlyAPI.entity.Building;
import com.rently.rentlyAPI.entity.CommonFacility;
import com.rently.rentlyAPI.entity.Company;
import com.rently.rentlyAPI.exceptions.AuthenticationException;
import com.rently.rentlyAPI.repository.BuildingRepository;
import com.rently.rentlyAPI.services.BuildingService;
import com.rently.rentlyAPI.services.CommonFacilityService;
import com.rently.rentlyAPI.services.CompanyService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BuildingServiceImpl implements BuildingService {

    private final BuildingRepository buildingRepository;
    private final CompanyService companyService;
    private final CommonFacilityService commonFacilityService;

    @Override
    public BuildingDto createBuilding(BuildingDto buildingDto) {
         Building building = BuildingDto.toEntity(buildingDto);
         Building savedBuilding = buildingRepository.save(building);
         return BuildingDto.fromEntity(savedBuilding);
    }

    @Override
    public BuildingDto findBuildingDtoById(Integer buildingId) {
        Building building = buildingRepository.findById(buildingId)
                .orElseThrow(() -> new EntityNotFoundException("Building with ID " + buildingId + " not found"));
        return BuildingDto.fromEntity(building);
    }

    @Override
    public Building findBuildingEntityById(Integer buildingId) {
        return buildingRepository.findById(buildingId)
                .orElseThrow(() -> new EntityNotFoundException("Building with ID " + buildingId + " not found"));
    }

    @Override
    public Building findBuildingEntityByName(String buildingName) {
        return buildingRepository.findByName(buildingName)
                .orElseThrow(() -> new EntityNotFoundException("Building with name " + buildingName + " not found"));
    }

    @Override
    public BuildingDto updateBuilding(BuildingDto buildingDto) {

        // Find the Building Entity by ID
        Building buildingToUpdate = findBuildingEntityById(buildingDto.getId());

        // Update Building details if present
        if (buildingDto.getAddress() != null && !buildingDto.getAddress().isEmpty()) {
            buildingToUpdate.setAddress(buildingDto.getAddress());
        }
        
        if (buildingDto.getDescription() != null && !buildingDto.getDescription().isEmpty()) {
            buildingToUpdate.setDescription(buildingDto.getDescription());
        }

        // Save the updated Building
        Building updatedBuilding = buildingRepository.save(buildingToUpdate);
        
        return BuildingDto.fromEntity(updatedBuilding);
    }

    @Override
    public void deleteBuilding(Integer buildingId) {
        // Attempt to retrieve the building by ID
        Building building = buildingRepository.findById(buildingId)
                .orElseThrow(() -> new EntityNotFoundException("Building with ID " + buildingId + " not found"));

        // If the building is found, delete it
        buildingRepository.delete(building);
    }

    @Override
    public List<BuildingDto> getAllBuildings() {
        List<Building> buildings = buildingRepository.findAll();
        return buildings.stream()
                .map(BuildingDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public BuildingDto createBuildingAndLinkToCompany(BuildingDto buildingDto) {
        Optional<Building> existingBuilding = buildingRepository.findByName(buildingDto.getName());

        // If the email is already associated with an account, throw an exception
        if (existingBuilding.isPresent()) {
            throw new AuthenticationException("This name is already associated with a building");
        }


        // Retrieve company with the given ID
        Company companyToLink = companyService.findCompanyEntityById(buildingDto.getCompanyId());

        // Create the company admin
        Building buildingToSave = BuildingDto.toEntity(buildingDto);

        // Link the company admin to the company
        buildingToSave.setCompany(companyToLink);

        // Save the company admin
        Building savedBuilding = buildingRepository.save(buildingToSave);

        // Return the company adminDto
        return BuildingDto.fromEntity(savedBuilding);
    }

    @Override
    public List<BuildingDto> getAllBuildingsByCompanyId(Integer companyId) {
        List<Building> buildings = buildingRepository.findAllByCompanyId(companyId);
        return buildings.stream()
                .map(BuildingDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CommonFacility> findCommonFacilityByName(Integer buildingId, String facilityName) {
        return commonFacilityService.findCommonFacilityByName(buildingId, facilityName);
    }

    @Override
    public CommonFacilityDto createCommonFacility(CommonFacilityDto commonFacilityDto) {
        Building buildingToLink = findBuildingEntityById(commonFacilityDto.getBuildingId());

        Optional<CommonFacility> existingCommonFacility = findCommonFacilityByName(buildingToLink.getId(), commonFacilityDto.getFacilityName());

        if (existingCommonFacility.isPresent()) {

            throw new AuthenticationException("Common Facility with name " + commonFacilityDto.getFacilityName() + " already exists in this building.");
        }
        return commonFacilityService.createCommonFacilityAndLinkToBuilding(commonFacilityDto, buildingToLink);
    }

    @Override
    public CommonFacilityDto getCommonFacilityById(Integer commonFacilityId) {
        return commonFacilityService.findCommonFacilityById(commonFacilityId);
    }

    @Override
    public List<CommonFacilityDto> getAllCommonFacilitiesByBuildingId(Integer buildingId) {
        findBuildingEntityById(buildingId);
        return commonFacilityService.getAllCommonFacilityByBuildingId(buildingId);
    }

    @Override
    public List<CommonFacilityDto> getAllCommonFacilities() {
        return commonFacilityService.getAllCommonFacilities();
    }

    @Override
    public void deleteCommonFacilityById(Integer commonFacilityId) {
        commonFacilityService.deleteCommonFacilityById(commonFacilityId);
    }

//	private final BuildingRepository buildingRepository;
//
//	@Override
//	public Building save(Building building) {
//		return buildingRepository.save(building);
//	}
//
//	@Override
//	public Building update(BuildingDto buildingDto, Building building) {
//		//update the building with the giving buildingDto and check for null
//		if (buildingDto.getName() != null) building.setName(buildingDto.getName());
//		if (buildingDto.getAddress() != null) building.setAddress(buildingDto.getAddress());
//		if (buildingDto.getDescription() != null) building.setDescription(buildingDto.getDescription());
//
//		return buildingRepository.save(building);
//	}
//
//	@Override
//	public void delete(Building building) {
//		buildingRepository.delete(building);
//	}
//
//	@Override
//
//	@Override
//	public boolean exists(Building building) {
//		return building.getId() != null && buildingRepository.existsById(building.getId());
//	}
//
//	public boolean exists(Integer buildingId) {
//		return buildingId != null && buildingRepository.existsById(buildingId);
//	}
//
//	@Override
//	public List<Building> findAllByCompanyId(Integer companyId) {
//		return buildingRepository.findAllByCompanyId(companyId);
//	}
//
//	@Override
//	public Building findById(Integer id) {
//		return buildingRepository.findById(id)
//				.orElseThrow(() -> new EntityNotFoundException("Building with id " + id + " not found"));
//	}
//
//	@Override
//	public Integer countCondosById(Integer buildingId) {
//		return buildingRepository.countCondosById(buildingId);
//	}
//
//	@Override
//	public List<Condo> findAllCondosById(Integer buildingId) {
//		return null;
//	}
}
