package com.rently.rentlyAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/*
 * This class is a data transfer object that combines the RegistrationKeyRequestDto and HousingContractDto classes.
 */
@Data
@Builder
@AllArgsConstructor
public class CombinedRequestDto {
	private RegistrationKeyRequestDto registrationKeyRequestDto;
	private HousingContractDto housingContractDto;
}

