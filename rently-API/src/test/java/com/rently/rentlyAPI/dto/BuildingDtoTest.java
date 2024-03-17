package com.rently.rentlyAPI.dto;

import com.rently.rentlyAPI.entity.Building;
import com.rently.rentlyAPI.entity.User.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BuildingDtoTest {

    Building mockedBuilding = mock(Building.class);
    BuildingDto mockedBuildingDto = mock(BuildingDto.class);
    User mockedUser = mock(User.class);

    @Test
    public void testFromEntity() {
        // Arrange
        when(mockedBuilding.getCompany()).thenReturn(mockedUser);
        when(mockedBuilding.getCompany()).thenReturn(mockedUser);
        when(mockedUser.getId()).thenReturn(1);


        // Act
        BuildingDto testBuildingDto = BuildingDto.fromEntity(mockedBuilding);

        // Assert
        assertEquals(mockedBuilding.getId(), testBuildingDto.getId());
        assertEquals(mockedBuilding.getName(), testBuildingDto.getName());
        assertEquals(mockedBuilding.getAddress(), testBuildingDto.getAddress());
        assertEquals(mockedBuilding.getDescription(), testBuildingDto.getDescription());
        assertEquals(mockedBuilding.getCompany().getId(), testBuildingDto.getUserId());
    }

    @Test
    public void testToEntity() {
        // Arrange

        // Act
        Building testBuilding = BuildingDto.toEntity(mockedBuildingDto);

        // Assert
        assertEquals(mockedBuildingDto.getId(), testBuilding.getId());
        assertEquals(mockedBuildingDto.getName(), testBuilding.getName());
        assertEquals(mockedBuildingDto.getAddress(), testBuilding.getAddress());
        assertEquals(mockedBuildingDto.getDescription(), testBuilding.getDescription());
    }
}
