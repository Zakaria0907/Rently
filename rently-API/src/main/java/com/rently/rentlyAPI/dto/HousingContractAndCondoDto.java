package com.rently.rentlyAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class HousingContractAndCondoDto {
	private HousingContractDto housingContractDto;
	private CondoDto condoDto;
}
