package com.rently.rentlyAPI.controller;

import com.rently.rentlyAPI.dto.BuildingDto;
import com.rently.rentlyAPI.services.CompanyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CompanyControllerTest {

    BuildingDto mockedBuildingDto = mock(BuildingDto.class);
    CompanyService mockedCompanyService = mock(CompanyService.class);
    int companyId = 1, buildingId = 2;

    @Test
    public void testCreateBuilding() {
        // Arrange
        when(mockedCompanyService.createBuildingByCompanyId(companyId, mockedBuildingDto)).thenReturn(mockedBuildingDto);
        CompanyController testController = new CompanyController(mockedCompanyService);


        // Act
        ResponseEntity<BuildingDto> testResponseEntity = testController.createBuilding(companyId, mockedBuildingDto);


        // Assert
        assertEquals(HttpStatus.OK, testResponseEntity.getStatusCode());
        assertEquals(ResponseEntity.ok(mockedBuildingDto), testResponseEntity);
        assertEquals(mockedBuildingDto, testResponseEntity.getBody()); // not sure if this is needed, leaving it in for now
    }

    @Test
    public void testGetBuildingCompanyIdAndBuildingId() {
        // Arrange
        when(mockedCompanyService.getBuildingByCompanyIdAndBuildingId(companyId, buildingId)).thenReturn(mockedBuildingDto);
        CompanyController testController = new CompanyController(mockedCompanyService);


        // Act
        ResponseEntity<BuildingDto> testResponseEntity = testController.getBuildingByCompanyIdAndBuildingId(companyId, buildingId);


        // Assert
        assertEquals(HttpStatus.OK, testResponseEntity.getStatusCode());
        assertEquals(ResponseEntity.ok(mockedBuildingDto), testResponseEntity);
        assertEquals(mockedBuildingDto, testResponseEntity.getBody()); // not sure if this is needed, leaving it in for now
    }
}
