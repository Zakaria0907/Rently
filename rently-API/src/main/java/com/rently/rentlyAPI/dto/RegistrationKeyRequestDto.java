package com.rently.rentlyAPI.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RegistrationKeyRequestDto {
	
	@JsonProperty("building_id")
	@Min(value = 1, message = "building ID number must be greater than 0")
	private Integer buildingId;
	
	@JsonProperty("condo_id")
	@Min(value = 1, message = "condo ID number must be greater than 0")
	private Integer condoId;
	
	@JsonProperty("role")
	private String role;
}
