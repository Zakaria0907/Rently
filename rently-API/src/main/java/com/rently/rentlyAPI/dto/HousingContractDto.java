package com.rently.rentlyAPI.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rently.rentlyAPI.entity.Condo;
import com.rently.rentlyAPI.entity.HousingContract;
import com.rently.rentlyAPI.entity.enums.CondoStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class HousingContractDto {
	
	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("company_id")
	private Integer companyId;
	
	@JsonProperty("occupant_id")
	private Integer occupantId;
	
	@JsonProperty("condo_id")
	private Integer condoId;
	
	@JsonProperty("monthly_rent")
	@Min(value = 0, message = "Monthly rent must be greater than 0")
	private Integer monthlyRent;
	
	@JsonProperty("occupant_type")
	private String occupantType;
	
	public static HousingContract toEntity(HousingContractDto housingContractDto){
		return HousingContract.builder()
				.occupantType(housingContractDto.getOccupantType())
				.monthlyRent(housingContractDto.getMonthlyRent())
				// company is set in the business logic
				// occupant is set in the business logic
				// condo is set in the business logic
				.build();
	}
	
	public static HousingContractDto fromEntity(HousingContract housingContract) {
		return HousingContractDto.builder()
				.id(housingContract.getId())
				.monthlyRent(housingContract.getMonthlyRent())
				.occupantType(housingContract.getOccupantType())
				.companyId(housingContract.getCompany().getId())
				.occupantId(housingContract.getOccupant().getId())
				.condoId(housingContract.getCondo().getId())
				.build();
	}
}
