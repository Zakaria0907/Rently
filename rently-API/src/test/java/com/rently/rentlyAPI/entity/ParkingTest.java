package com.rently.rentlyAPI.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParkingTest {

    @Test
    void testNoArgsConstructor() {
        Parking parking = new Parking();

        assertNotNull(parking);
        assertEquals(0L, parking.getParkingFee());
    }

    @Test
    void testAllArgsConstructor() {
        Building building = new Building();
        Condo condo = new Condo();

        Parking parking = new Parking(building, 100L, condo);

        assertEquals(building, parking.getBuilding());
        assertEquals(100L, parking.getParkingFee());
        assertEquals(condo, parking.getCondo());
    }

    @Test
    void testValidation() {
        Parking parking = Parking.builder()
                .building(null)
                .parkingFee(0L)
                .condo(null)
                .build();

        assertNotNull(parking.getParkingFee());
    }
}
