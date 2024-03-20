package com.rently.rentlyAPI.services;

import com.rently.rentlyAPI.dto.CommonFacilityReservationDto;
import com.rently.rentlyAPI.dto.HousingContractDto;
import com.rently.rentlyAPI.entity.CommonFacility;
import com.rently.rentlyAPI.entity.Company;
import com.rently.rentlyAPI.entity.Condo;
import com.rently.rentlyAPI.entity.HousingContract;
import com.rently.rentlyAPI.entity.user.Occupant;

import java.util.List;

public interface HousingContractService {
	HousingContract createHousingContractWithoutOccupant(Company company, Condo condo, HousingContractDto housingContractDto);
	
	HousingContractDto setOccupantToHousingContract(HousingContract housingContract, Occupant occupant);
	
	HousingContract findHousingContractEntityByCondoId(Integer condoId);
	
	void deleteHousingContract(Integer housingContractId);
}
