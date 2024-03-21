package com.rently.rentlyAPI.dto;

import com.rently.rentlyAPI.entity.Building;
import com.rently.rentlyAPI.entity.Condo;
import com.rently.rentlyAPI.entity.Locker;
import com.rently.rentlyAPI.entity.Parking;
import com.rently.rentlyAPI.entity.enums.CondoStatus;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@SpringBootTest
public class CondoDtoTest {

    @Test
    public void testToEntity() {
        CondoDto condoDto = CondoDto.builder()
                .unitNumber(101)
                .description("Test Description")
                .status("AVAILABLE")
                .build();

        Building building = mock(Building.class);
        Parking parking = mock(Parking.class);
        Locker locker = mock(Locker.class);

        Condo condo = CondoDto.toEntity(condoDto);
        condo.setBuilding(building);
        condo.setParking(parking);
        condo.setLocker(locker);

        assertEquals(condoDto.getUnitNumber(), condo.getUnitNumber());
        assertEquals(condoDto.getDescription(), condo.getDescription());
        assertEquals(condoDto.getStatus(), condo.getStatus().toString());
    }

    @Test
    public void testFromEntity() {
        Condo condo = new Condo();
        condo.setId(1);
        condo.setAddress("456 Elm St");
        condo.setUnitNumber(202);
        condo.setDescription("Another Test Description");
        condo.setRegistrationKey("REG456");
        condo.setStatus(CondoStatus.AVAILABLE);

        Building building = new Building();
        building.setId(1);
        condo.setBuilding(building);

        Parking parking = new Parking();
        parking.setId(1);
        condo.setParking(parking);

        Locker locker = new Locker();
        locker.setId(1);
        condo.setLocker(locker);

        CondoDto condoDto = CondoDto.fromEntity(condo);

        assertEquals(condo.getId(), condoDto.getId());
        assertEquals(condo.getAddress(), condoDto.getAddress());
        assertEquals(condo.getUnitNumber(), condoDto.getUnitNumber());
        assertEquals(condo.getDescription(), condoDto.getDescription());
        assertEquals(condo.getRegistrationKey(), condoDto.getRegistrationKey());
        assertEquals(condo.getStatus().toString(), condoDto.getStatus());
        assertEquals(condo.getBuilding().getId(), condoDto.getBuildingId());
        assertEquals(condo.getParking().getId(), condoDto.getParkingId());
        assertEquals(condo.getLocker().getId(), condoDto.getLockerId());
    }
}
