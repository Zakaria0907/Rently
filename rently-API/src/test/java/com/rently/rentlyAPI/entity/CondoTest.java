package com.rently.rentlyAPI.entity;

import com.rently.rentlyAPI.entity.enums.CondoStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;

public class CondoTest {

    CondoStatus mockedCondoStatus = mock(CondoStatus.class);
    Building mockedBuilding = mock(Building.class);
    User mockedUser = mock(User.class);
    @Test
    public void testAllArgsConstructor() {
        // Arrange


        // Act
        Condo testCondo = new Condo("Concordia", "1455 Boul. De Maisonneuve O", "123", "type1", "description", mockedCondoStatus, mockedBuilding, mockedUser);

        // Assert
        assertEquals("Concordia", testCondo.getName());
        assertEquals("1455 Boul. De Maisonneuve O", testCondo.getAddress());
        assertEquals("123", testCondo.getCondoNumber());
        assertEquals("type1", testCondo.getCondoType());
        assertEquals("description", testCondo.getDescription());
        assertEquals(mockedCondoStatus, testCondo.getStatus());
        assertEquals(mockedBuilding, testCondo.getBuilding());
        assertEquals(mockedUser, testCondo.getUser());
    }

    @Test
    public void testNoArgsConstructor() {
        // Arrange


        // Act
        Condo testCondo = new Condo();

        // Assert
        assertNull(testCondo.getName());
        assertNull(testCondo.getAddress());
        assertNull(testCondo.getCondoNumber());
        assertNull(testCondo.getCondoType());
        assertNull(testCondo.getDescription());
        assertNull(testCondo.getStatus());
        assertNull(testCondo.getBuilding());
        assertNull(testCondo.getUser());
    }

    @Test
    public void testBuilder() {
        // Arrange


        // Act
        Condo testCondo = Condo.builder().build();

        // Assert
        assertNull(testCondo.getName());
        assertNull(testCondo.getAddress());
        assertNull(testCondo.getCondoNumber());
        assertNull(testCondo.getCondoType());
        assertNull(testCondo.getDescription());
        assertNull(testCondo.getStatus());
        assertNull(testCondo.getBuilding());
        assertNull(testCondo.getUser());
    }

}
