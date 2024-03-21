package com.rently.rentlyAPI.dto;

import com.rently.rentlyAPI.entity.Building;
import com.rently.rentlyAPI.entity.Company;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BuildingDtoTest {

    @Test
    public void testFromEntity() {
        // Create a Building entity for testing
        Building building = Building.builder()
                .id(1)
                .name("Test Building")
                .address("123 Test Street")
                .description("Test Description")
                .numberOfFloors(5)
                .build();
        Company company = new Company();
        building.setCompany(company);

        // Convert Building entity to BuildingDto
        BuildingDto buildingDto = BuildingDto.fromEntity(building);

        // Verify the conversion
        assertNotNull(buildingDto);
        assertEquals(building.getId(), buildingDto.getId());
        assertEquals(building.getName(), buildingDto.getName());
        assertEquals(building.getAddress(), buildingDto.getAddress());
        assertEquals(building.getDescription(), buildingDto.getDescription());
        assertEquals(building.getNumberOfFloors(), buildingDto.getNumberOfFloors());
    }

    @Test
    public void testToEntity() {
        // Create a BuildingDto for testing
        BuildingDto buildingDto = BuildingDto.builder()
                .name("Test Building")
                .address("123 Test Street")
                .description("Test Description")
                .numberOfFloors(5)
                .build();


        // Convert BuildingDto to Building entity
        Building building = BuildingDto.toEntity(buildingDto);

        // Verify the conversion
        assertNotNull(building);
        assertEquals(buildingDto.getName(), building.getName());
        assertEquals(buildingDto.getAddress(), building.getAddress());
        assertEquals(buildingDto.getDescription(), building.getDescription());
        assertEquals(buildingDto.getNumberOfFloors(), building.getNumberOfFloors());
    }
}
