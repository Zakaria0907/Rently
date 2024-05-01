package com.rently.rentlyAPI.dto;

import com.rently.rentlyAPI.entity.Building;
import com.rently.rentlyAPI.entity.OwnerRequest;
import com.rently.rentlyAPI.entity.enums.WorkType;
import com.rently.rentlyAPI.entity.user.Owner;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OwnerRequestDtoTest {

    @Test
    void testFromEntity() {
        Owner owner = new Owner();
        owner.setId(1);
        Building building = new Building();
        building.setId(2);
        OwnerRequest ownerRequest = new OwnerRequest();
        ownerRequest.setId(1);
        ownerRequest.setRequestDescription("Description");
        ownerRequest.setWorkType(WorkType.GENERAL);
        ownerRequest.setOwner(owner);
        ownerRequest.setBuilding(building);

        OwnerRequestDto ownerRequestDto = OwnerRequestDto.fromEntity(ownerRequest);

        assertNotNull(ownerRequestDto);
        assertEquals(1, ownerRequestDto.getId());
        assertEquals(1, ownerRequestDto.getOwnerId());
        assertEquals(2, ownerRequestDto.getBuildingId());
        assertEquals("Description", ownerRequestDto.getRequestDescription());
        assertEquals("GENERAL", ownerRequestDto.getWorkType());
    }

    @Test
    void testToEntity() {
        OwnerRequestDto ownerRequestDto = new OwnerRequestDto(1, 2, 3, "Description", "GENERAL");

        OwnerRequest ownerRequest = OwnerRequestDto.toEntity(ownerRequestDto);

        assertNotNull(ownerRequest);
        assertEquals(1, ownerRequest.getId());
        assertEquals(WorkType.GENERAL, ownerRequest.getWorkType());
        assertEquals("Description", ownerRequest.getRequestDescription());
    }
}
