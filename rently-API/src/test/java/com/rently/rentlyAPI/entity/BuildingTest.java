package com.rently.rentlyAPI.entity;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;

public class BuildingTest {

    List<Condo> mockedList = mock(List.class);
    User mockedUser = mock(User.class);
    @Test
    public void testAllArgsConstructor() {
        // Arrange


        // Act
        Building testBuilding = new Building("Concordia", "1455 Boul. De Maisonneuve O", "description", mockedList, mockedUser);

        // Assert
        assertEquals("Concordia", testBuilding.getName());
        assertEquals("1455 Boul. De Maisonneuve O", testBuilding.getAddress());
        assertEquals("description", testBuilding.getDescription());
        assertEquals(mockedList, testBuilding.getCondos());
        assertEquals(mockedUser, testBuilding.getCompany());
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
