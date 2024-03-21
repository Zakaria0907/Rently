package com.rently.rentlyAPI.entity;

import com.rently.rentlyAPI.entity.enums.CondoStatus;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;

@SpringBootTest
public class CondoTest {

    Building mockedBuilding = mock(Building.class);
    Parking mockedParking = mock(Parking.class);
    Locker mockedLocker = mock(Locker.class);

    @Test
    public void testAllArgsConstructor() {

        Condo testCondo = new Condo("Concordia", 4, "123", "key", CondoStatus.AVAILABLE, mockedBuilding, mockedParking, mockedLocker);

        assertEquals("Concordia", testCondo.getAddress());
        assertEquals(4, testCondo.getUnitNumber());
        assertEquals("key", testCondo.getRegistrationKey());
        assertEquals("123", testCondo.getDescription());
        assertEquals(CondoStatus.AVAILABLE, testCondo.getStatus());
        assertEquals(mockedBuilding, testCondo.getBuilding());
        assertEquals(mockedParking, testCondo.getParking());
        assertEquals(mockedLocker, testCondo.getLocker());
    }

    @Test
    public void testNoArgsConstructor() {

        Condo testCondo = new Condo();

        assertNull(testCondo.getAddress());
        assertNull(testCondo.getUnitNumber());
        assertNull(testCondo.getDescription());
        assertNull(testCondo.getStatus());
        assertNull(testCondo.getBuilding());
        assertNull(testCondo.getLocker());
        assertNull(testCondo.getParking());

    }

    @Test
    public void testBuilder() {

        Condo testCondo = Condo.builder().build();

        assertNull(testCondo.getAddress());
        assertNull(testCondo.getUnitNumber());
        assertNull(testCondo.getDescription());
        assertNull(testCondo.getStatus());
        assertNull(testCondo.getBuilding());
    }

}
