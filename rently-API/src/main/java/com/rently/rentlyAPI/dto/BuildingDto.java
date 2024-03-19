package com.rently.rentlyAPI.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rently.rentlyAPI.entity.Building;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BuildingDto {

	@JsonProperty("id")
	private Integer id;

	@JsonProperty("name")
	@NotBlank(message = "The name is required")
	private String name;

	@JsonProperty("number_of_floors")
	private Integer numberOfFloors;

	@JsonProperty("address")
	@NotBlank(message = "The address is required")
	private String address;

	@JsonProperty("description")
	private String description;

	@JsonProperty("company_id")
	private Integer companyId;

	// Converts a Building entity to a BuildingDto
	public static BuildingDto fromEntity(Building building) {
		return BuildingDto.builder()
				.id(building.getId())
				.name(building.getName())
				.address(building.getAddress())
				.description(building.getDescription())
				.numberOfFloors(building.getNumberOfFloors())
				.companyId(building.getCompany().getId())
				.build();

	}

	public static Building toEntity(BuildingDto buildingDto) {
		return Building.builder()
				.name(buildingDto.getName())
				.address(buildingDto.getAddress())
				.description(buildingDto.getDescription())
				.numberOfFloors(buildingDto.getNumberOfFloors())
				.build();
	}

}
