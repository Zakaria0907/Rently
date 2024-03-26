package com.rently.rentlyAPI.entity;

import com.rently.rentlyAPI.entity.user.Occupant;
import com.rently.rentlyAPI.entity.user.Owner;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

//@SpringBootTest
public class CommonFacilityReservationTest {

    @Test
    public void testCommonFacilityReservationEntity() {
        // Create a Company entity
        Company company = new Company();
        company.setId(1); // Set company ID

        // Create a CommonFacility entity
        CommonFacility commonFacility = new CommonFacility();
        commonFacility.setId(1); // Set common facility ID

        // Create an Occupant entity
        Occupant occupant = new Owner();
        occupant.setId(1); // Set occupant ID

        // Create a CommonFacilityReservation entity
        CommonFacilityReservation commonFacilityReservation = new CommonFacilityReservation();
        commonFacilityReservation.setId(1); // Set ID
        commonFacilityReservation.setCompany(company); // Set company
        commonFacilityReservation.setCommonFacility(commonFacility); // Set common facility
        commonFacilityReservation.setOccupant(occupant); // Set occupant
        commonFacilityReservation.setDuration(60); // Set duration
        commonFacilityReservation.setDate("2024-03-21"); // Set date

        // Assert that the CommonFacilityReservation entity is not null
        assertNotNull(commonFacilityReservation);

        // Assert that the properties are correctly set
        assertEquals(1, commonFacilityReservation.getId());
        assertEquals(company, commonFacilityReservation.getCompany());
        assertEquals(commonFacility, commonFacilityReservation.getCommonFacility());
        assertEquals(occupant, commonFacilityReservation.getOccupant());
        assertEquals(60, commonFacilityReservation.getDuration());
        assertEquals("2024-03-21", commonFacilityReservation.getDate());
    }
}
