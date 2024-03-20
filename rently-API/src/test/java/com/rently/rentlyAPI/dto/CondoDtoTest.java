package com.rently.rentlyAPI.dto;

import com.rently.rentlyAPI.entity.Building;
import com.rently.rentlyAPI.entity.Condo;
import com.rently.rentlyAPI.entity.user.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CondoDtoTest {

    CondoDto mockedCondoDto = mock(CondoDto.class);
    Condo mockedCondo = mock(Condo.class);
    User mockedUser = mock(User.class);
    Building mockedBuilding = mock(Building.class);

    @Test
    public void testFromEntity() {
        // Arrange
        when(mockedCondo.getUser()).thenReturn(mockedUser);
        when(mockedCondo.getUser()).thenReturn(mockedUser);
        when(mockedUser.getId()).thenReturn(1);
        when(mockedCondo.getBuilding()).thenReturn(mockedBuilding);

        // Act
        CondoDto testCondoDto = CondoDto.fromEntity(mockedCondo);

        // Assert
        assertEquals(mockedCondo.getId(), testCondoDto.getId());
        assertEquals(mockedCondo.getName(), testCondoDto.getName());
        assertEquals(mockedCondo.getAddress(), testCondoDto.getAddress());
        assertEquals(mockedCondo.getUnitNumber(), testCondoDto.getCondoNumber());
        assertEquals(mockedCondo.getCondoType(), testCondoDto.getCondoType());
        assertEquals(mockedCondo.getDescription(), testCondoDto.getDescription());
        assertEquals(mockedCondo.getStatus(), testCondoDto.getStatus());
        assertEquals(mockedCondo.getUser().getId(), testCondoDto.getUserId());
        assertEquals(mockedCondo.getBuilding().getId(), testCondoDto.getBuildingId());
    }

    @Test
    public void testToEntity() {
        // Arrange

        // Act
        Condo testCondo = CondoDto.toEntity(mockedCondoDto);


        // Assert
        assertEquals(mockedCondoDto.getId(), testCondo.getId());
        assertEquals(mockedCondoDto.getName(), testCondo.getName());
        assertEquals(mockedCondoDto.getAddress(), testCondo.getAddress());
        assertEquals(mockedCondoDto.getCondoNumber(), testCondo.getUnitNumber());
        assertEquals(mockedCondoDto.getCondoType(), testCondo.getCondoType());
        assertEquals(mockedCondoDto.getDescription(), testCondo.getDescription());
        assertEquals(mockedCondoDto.getStatus(), testCondo.getStatus());
        assertEquals(mockedCondoDto.getUserId(), testCondo.getUser().getId());
    }
    
}
