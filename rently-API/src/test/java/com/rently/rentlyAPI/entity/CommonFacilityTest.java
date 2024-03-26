package com.rently.rentlyAPI.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

//@SpringBootTest
public class CommonFacilityTest {

    @Test
    public void testCommonFacilityEntity() {
        // Create a Building entity
        Building building = new Building();
        building.setId(1); // Set building ID

        // Create a CommonFacility entity
        CommonFacility commonFacility = new CommonFacility();
        commonFacility.setId(1); // Set ID
        commonFacility.setName("Swimming Pool"); // Set name
        commonFacility.setDescription("Outdoor swimming pool"); // Set description
        commonFacility.setBuilding(building); // Set building

        // Assert that the CommonFacility entity is not null
        assertNotNull(commonFacility);

        // Assert that the properties are correctly set
        assertEquals(1, commonFacility.getId());
        assertEquals("Swimming Pool", commonFacility.getName());
        assertEquals("Outdoor swimming pool", commonFacility.getDescription());
        assertEquals(building, commonFacility.getBuilding());
    }
}
