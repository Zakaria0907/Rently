package com.rently.rentlyAPI.entity;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;

public class BuildingTest {

    List<Condo> mockedList = mock(List.class);
    List<Parking> mockedParking = mock(List.class);
    List<Locker> mockedLocker = mock(List.class);
    List<CommonFacility> mockedCommonFacility = mock(List.class);
    Company mockedCompany = mock(Company.class);

    @Test
    public void testAllArgsConstructor() {
        // Arrange


        // Act
        Building testBuilding = new Building("Concordia", "address", "description", 5, mockedList, mockedLocker, mockedParking, mockedCommonFacility, mockedCompany);//new Building("Concordia", "1455 Boul. De Maisonneuve O", "description", mockedList, mockedUser);

        // Assert
        assertEquals("Concordia", testBuilding.getName());
        assertEquals("address", testBuilding.getAddress());
        assertEquals("description", testBuilding.getDescription());
        assertEquals(mockedList, testBuilding.getCondos());
        assertEquals(mockedCompany, testBuilding.getCompany());
    }

    @Test
    public void testNoArgsConstructor() {
        // Arrange


        // Act
        Building testBuilding = new Building();

        // Assert
        assertNull(testBuilding.getName());
        assertNull(testBuilding.getAddress());
        assertNull(testBuilding.getDescription());
        assertNull(testBuilding.getCondos());
        assertNull(testBuilding.getCompany());
    }

    @Test
    public void testBuilder() {
        // Arrange


        // Act
        Building testBuilding = Building.builder().build();

        // Assert
        assertNull(testBuilding.getName());
        assertNull(testBuilding.getAddress());
        assertNull(testBuilding.getDescription());
        assertNull(testBuilding.getCondos());
        assertNull(testBuilding.getCompany());
    }

}
