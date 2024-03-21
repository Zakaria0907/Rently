package com.rently.rentlyAPI.dto;

import com.rently.rentlyAPI.entity.Building;
import com.rently.rentlyAPI.entity.CommonFacility;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@SpringBootTest
public class CommonFacilityDtoTest {

    @Test
    public void testFromEntity() {
        CommonFacility commonFacility = new CommonFacility();
        commonFacility.setId(1);
        commonFacility.setName("Test Facility");
        commonFacility.setDescription("Test Description");

        Building building = new Building();
        building.setId(1);
        commonFacility.setBuilding(building);

        CommonFacilityDto commonFacilityDto = CommonFacilityDto.fromEntity(commonFacility);

        assertEquals(commonFacility.getId(), commonFacilityDto.getId());
        assertEquals(commonFacility.getName(), commonFacilityDto.getFacilityName());
        assertEquals(commonFacility.getDescription(), commonFacilityDto.getDescription());
        assertEquals(commonFacility.getBuilding().getId(), commonFacilityDto.getBuildingId());
    }

    @Test
    public void testToEntity() {
        CommonFacilityDto commonFacilityDto = CommonFacilityDto.builder()
                .facilityName("Test Facility")
                .description("Test Description")
                .buildingId(1)
                .build();

        Building building = mock(Building.class);

        CommonFacility commonFacility = CommonFacilityDto.toEntity(commonFacilityDto);
        commonFacility.setBuilding(building);

        assertEquals(commonFacilityDto.getFacilityName(), commonFacility.getName());
        assertEquals(commonFacilityDto.getDescription(), commonFacility.getDescription());
    }
}
