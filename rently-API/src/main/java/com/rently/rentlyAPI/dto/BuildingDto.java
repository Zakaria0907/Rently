package com.rently.rentlyAPI.dto;

import com.rently.rentlyAPI.entity.Building;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class BuildingDto {
	
	@Nullable
	private Integer id;
	
	@NotBlank(message = "Name is required")
	private String name;
	
	@NotBlank(message = "Address is required")
	private String address;

	private String description;
	
	private CompanyDto company;
	
	// Converts a Building entity to a BuildingDto
	public static BuildingDto fromEntity(Building building) {
		return BuildingDto.builder()
				.id(building.getId())
				.name(building.getName())
				.address(building.getAddress())
				.description(building.getDescription())
				.company(CompanyDto.fromEntity(building.getCompany()))
				.build();
	}
	
	// Converts a BuildingDto to a Building entity
	public static Building toEntity(BuildingDto buildingDto) {
		return Building.builder()
				.id(buildingDto.getId())
				.name(buildingDto.getName())
				.address(buildingDto.getAddress())
				.description(buildingDto.getDescription())
				.company(CompanyDto.toEntity(buildingDto.getCompany()))
				.build();

	}
}
