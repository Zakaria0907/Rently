package com.rently.rentlyAPI.dto;

import com.rently.rentlyAPI.entity.Building;
import com.rently.rentlyAPI.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.annotation.Nullable;

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
	
//	private Integer unitCount;
	
	private String description;
	
	private Integer userId;
	
	// Converts a Building entity to a BuildingDto
	public static BuildingDto fromEntity(Building building) {
		return BuildingDto.builder()
				.id(building.getId())
				.name(building.getName())
				.address(building.getAddress())
//				.unitCount(building.getUnitCount())
				.description(building.getDescription())
				.userId(building.getCompany() != null ? building.getCompany().getId() : null)
				.build();
	}
	
	// Converts a BuildingDto to a Building entity
	public static Building toEntity(BuildingDto buildingDto) {
		Building building = Building.builder()
				.id(buildingDto.getId())
				.name(buildingDto.getName())
				.address(buildingDto.getAddress())
//				.unitCount(buildingDto.getUnitCount() != null ? buildingDto.getUnitCount() : 0)
				.description(buildingDto.getDescription())
				// Note: The company (User) is set in the service
				.build();
		
		// Intentionally left blank: .company(User.builder().id(buildingDto.getUserId()).build())
		// Setting the company is handled in the company service
		
		return building;
	}
}
