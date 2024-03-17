package com.rently.rentlyAPI.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rently.rentlyAPI.entity.Building;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
public class BuildingDto {

	@JsonProperty("id")
	private Integer id;

	@JsonProperty("address")
	@NotBlank(message = "The address is required")
	private String address;

	@JsonProperty("description")
	private String description;

	@JsonProperty("company_id")
	private int companyId;

	// Converts a Building entity to a BuildingDto
	public static BuildingDto fromEntity(Building building) {
		return BuildingDto.builder()
				.id(building.getId())
				.address(building.getAddress())
				.description(building.getDescription())
				.companyId(building.getCompany().getId())
				.build();

	}

	public static Building toEntity(BuildingDto buildingDto) {
		return Building.builder()
				.address(buildingDto.getAddress())
				.description(buildingDto.getDescription())
				.build();
	}

}
