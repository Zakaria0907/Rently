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

@Service
@AllArgsConstructor
public class BuildingServiceImpl implements BuildingService {
	
	private final BuildingRepository buildingRepository;
	
	@Override
	public Building save(Building building) {
		return buildingRepository.save(building);
	}
	
	@Override
	public Building update(BuildingDto buildingDto, Building building) {
		//update the building with the giving buildingDto and check for null
		if (buildingDto.getName() != null) building.setName(buildingDto.getName());
		if (buildingDto.getAddress() != null) building.setAddress(buildingDto.getAddress());
		if (buildingDto.getDescription() != null) building.setDescription(buildingDto.getDescription());
		
		return buildingRepository.save(building);
	}
	
	@Override
	public void delete(Building building) {
		buildingRepository.delete(building);
	}
	
	@Override

//	@Override
//	public boolean exists(Building building) {
//		return building.getId() != null && buildingRepository.existsById(building.getId());
//	}
	
	public boolean exists(Integer buildingId) {
		return buildingId != null && buildingRepository.existsById(buildingId);
	}
	
	@Override
	public List<Building> findAllByCompanyId(Integer companyId) {
		return buildingRepository.findAllByCompanyId(companyId);
	}
	
	@Override
	public Building findById(Integer id) {
		return buildingRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Building with id " + id + " not found"));
	}
	
//	@Override
//	public Integer countCondosById(Integer buildingId) {
//		return buildingRepository.countCondosById(buildingId);
//	}

//	@Override
//	public List<Condo> findAllCondosById(Integer buildingId) {
//		return null;
//	}
}
