package com.rently.rentlyAPI.dto;

import com.rently.rentlyAPI.entity.Building;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class BuildingDtoTest {


    @Test
    public void testFromEntity() {
        // Arrange
        Building mockedBuilding = mock(Building.class);

        // Act
        BuildingDto testBuildingDto = BuildingDto.fromEntity(mockedBuilding);

        // Assert
        assertEquals(mockedBuilding.getId(), testBuildingDto.getId());
        assertEquals(mockedBuilding.getName(), testBuildingDto.getName());
        assertEquals(mockedBuilding.getAddress(), testBuildingDto.getAddress());
        assertEquals(mockedBuilding.getDescription(), testBuildingDto.getDescription());
    }

    @Test
    public void testToEntity() {
        // Arrange
        BuildingDto mockedBuildingDto = mock(BuildingDto.class);

        // Act
        Building testBuilding = BuildingDto.toEntity(mockedBuildingDto);

        // Assert
        assertEquals(mockedBuildingDto.getId(), testBuilding.getId());
        assertEquals(mockedBuildingDto.getName(), testBuilding.getName());
        assertEquals(mockedBuildingDto.getAddress(), testBuilding.getAddress());
        assertEquals(mockedBuildingDto.getDescription(), testBuilding.getDescription());
    }
}
