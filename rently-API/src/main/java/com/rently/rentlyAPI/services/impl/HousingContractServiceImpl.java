package com.rently.rentlyAPI.services.impl;

import com.rently.rentlyAPI.dto.HousingContractDto;
import com.rently.rentlyAPI.entity.Company;
import com.rently.rentlyAPI.entity.Condo;
import com.rently.rentlyAPI.entity.HousingContract;
import com.rently.rentlyAPI.entity.user.Occupant;
import com.rently.rentlyAPI.repository.HousingContractRepository;
import com.rently.rentlyAPI.services.HousingContractService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class HousingContractServiceImpl implements HousingContractService {
	
	private final HousingContractRepository housingContractRepository;
	
	@Override
	public HousingContract createHousingContractWithoutOccupant(Company company, Condo condo, HousingContractDto housingContractDto) {
		// Check if there is already a housing contract for the given condo
		Optional<HousingContract> existingHousingContract = housingContractRepository.findByCondoId(condo.getId());
		
		if(existingHousingContract.isPresent()) {
			throw new IllegalArgumentException("There is already a housing contract for the given condo");
		}
		
		HousingContract housingContract = HousingContractDto.toEntity(housingContractDto);
		housingContract.setCompany(company);
		housingContract.setCondo(condo);
		
		HousingContract savedHousingContract = housingContractRepository.save(housingContract);
		
		return savedHousingContract;
	}
	
	@Override
	public HousingContractDto setOccupantToHousingContract(HousingContract housingContract, Occupant occupant) {
		housingContract.setOccupant(occupant);
		HousingContract savedHousingContract = housingContractRepository.save(housingContract);
		return HousingContractDto.fromEntity(savedHousingContract);
	}
	
	@Override
	public HousingContract findHousingContractEntityByCondoId(Integer condoId) {
		Optional<HousingContract> housingContract = housingContractRepository.findByCondoId(condoId);
		if(housingContract.isEmpty()) {
			throw new IllegalArgumentException("Housing contract for condo with ID " + condoId + " not found");
		}
		return housingContract.get();
	}
	
	@Override
	public void deleteHousingContract(Integer housingContractId) {
	
	}
}
