package com.rently.rentlyAPI.entity;

import com.rently.rentlyAPI.entity.enums.WorkType;
import com.rently.rentlyAPI.entity.user.Owner;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class OwnerRequestTest {
    Company mockedCompany = mock(Company.class);
    Owner mockedOwner = mock(Owner.class);
    Building mockedBuilding = mock(Building.class);

    @Test
    public void testAllArgsConstructor() {
        OwnerRequest testOwnerRequest = new OwnerRequest(mockedCompany, mockedOwner, mockedBuilding, WorkType.GENERAL, "testDescription");
        assertEquals(mockedCompany, testOwnerRequest.getCompany());
        assertEquals(mockedOwner, testOwnerRequest.getOwner());
        assertEquals(mockedBuilding, testOwnerRequest.getBuilding());
    }

    @Test
    public void testNoArgsConstructor() {
        OwnerRequest testOwnerRequest = new OwnerRequest();
        assertEquals(null, testOwnerRequest.getCompany());
        assertEquals(null, testOwnerRequest.getOwner());
        assertEquals(null, testOwnerRequest.getBuilding());
        assertEquals(null, testOwnerRequest.getWorkType());
        assertEquals(null, testOwnerRequest.getRequestDescription());
    }
}
