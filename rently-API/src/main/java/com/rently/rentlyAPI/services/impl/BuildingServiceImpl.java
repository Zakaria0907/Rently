package com.rently.rentlyAPI.services.impl;

import com.rently.rentlyAPI.dto.BuildingDto;
import com.rently.rentlyAPI.entity.Building;
import com.rently.rentlyAPI.entity.Condo;
import com.rently.rentlyAPI.repository.BuildingRepository;
import com.rently.rentlyAPI.services.BuildingService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BuildingServiceImpl implements BuildingService {

    private final BuildingRepository buildingRepository;

    @Override
    public BuildingDto createBuilding(BuildingDto buildingDto) {
         Building building = BuildingDto.toEntity(buildingDto);
         Building savedBuilding = buildingRepository.save(building);
         return BuildingDto.fromEntity(savedBuilding);
    }

    @Override
    public BuildingDto getBuildingById(Integer buildingId) {
        Building building = buildingRepository.findById(buildingId)
                .orElseThrow(() -> new EntityNotFoundException("Building with ID " + buildingId + " not found"));
        return BuildingDto.fromEntity(building);
    }

    @Override
    public BuildingDto updateBuilding(Integer buildingId, BuildingDto buildingDto) {
        Optional<Building> buildingOptional = buildingRepository.findById(buildingId);

        if (buildingOptional.isPresent()) {
            Building buildingToUpdate = buildingOptional.get();

            // Update address if present
            if (buildingDto.getAddress() != null && !buildingDto.getAddress().isEmpty()) {
                buildingToUpdate.setAddress(buildingDto.getAddress());
            }

            // Update description if present
            if (buildingDto.getDescription() != null) {
                buildingToUpdate.setDescription(buildingDto.getDescription());
            }

            Building updatedBuilding = buildingRepository.save(buildingToUpdate);
            return BuildingDto.fromEntity(updatedBuilding);
        } else {
            // Building with the given ID not found
            throw new EntityNotFoundException("Building with ID " + buildingId + " not found");
        }
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
