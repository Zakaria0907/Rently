package com.rently.rentlyAPI.services;

import com.rently.rentlyAPI.dto.CommonFacilityReservationDto;
import com.rently.rentlyAPI.dto.HousingContractDto;
import com.rently.rentlyAPI.entity.CommonFacility;
import com.rently.rentlyAPI.entity.Company;
import com.rently.rentlyAPI.entity.Condo;
import com.rently.rentlyAPI.entity.HousingContract;
import com.rently.rentlyAPI.entity.user.Occupant;

import java.util.List;
import java.util.Optional;

public interface HousingContractService {
	HousingContract createHousingContractWithoutOccupant(Company company, Condo condo, HousingContractDto housingContractDto);
	
	HousingContractDto setOccupantToHousingContract(HousingContract housingContract, Occupant occupant);
	
	HousingContract findHousingContractEntityByCondoId(Integer condoId);
	
	List<HousingContract> findHousingContractEntitiesByOccupantId(Integer occupantId);
	
	Optional<HousingContract> findHousingContractByCondoIdAndOccupantId(Integer condoId, Integer occupantId);
	
	void deleteHousingContract(Integer housingContractId);
}
