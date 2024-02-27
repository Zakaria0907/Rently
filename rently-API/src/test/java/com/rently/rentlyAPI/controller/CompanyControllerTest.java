package com.rently.rentlyAPI.controller;

import com.rently.rentlyAPI.dto.BuildingDto;
import com.rently.rentlyAPI.dto.CondoDto;
import com.rently.rentlyAPI.services.CompanyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CompanyControllerTest {

    BuildingDto mockedBuildingDto = mock(BuildingDto.class);
    CompanyService mockedCompanyService = mock(CompanyService.class);
    int companyId = 1, buildingId = 2;

    CompanyController testController = new CompanyController(mockedCompanyService);

    @Test
    public void testCreateBuilding() {
        // Arrange
        when(mockedCompanyService.createBuildingByCompanyId(companyId, mockedBuildingDto)).thenReturn(mockedBuildingDto);


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


        // Act
        ResponseEntity<BuildingDto> testResponseEntity = testController.getBuildingByCompanyIdAndBuildingId(companyId, buildingId);


        // Assert
        assertEquals(HttpStatus.OK, testResponseEntity.getStatusCode());
        assertEquals(ResponseEntity.ok(mockedBuildingDto), testResponseEntity);
        assertEquals(mockedBuildingDto, testResponseEntity.getBody()); // not sure if this is needed, leaving it in for now
    }

//    @Test
//    public void testUpdateBuildingByCompanyIdAndBuildingId() {
//        // Arrange
//        when(mockedCompanyService.getBuildingByCompanyIdAndBuildingId(companyId, buildingId)).thenReturn(mockedBuildingDto);
//        CompanyController testController = new CompanyController(mockedCompanyService);
//
//
//        // Act
//        ResponseEntity<BuildingDto> testResponseEntity = testController.getBuildingByCompanyIdAndBuildingId(companyId, buildingId);
//
//
//        // Assert
//        assertEquals(HttpStatus.OK, testResponseEntity.getStatusCode());
//        assertEquals(ResponseEntity.ok(mockedBuildingDto), testResponseEntity);
//        assertEquals(mockedBuildingDto, testResponseEntity.getBody()); // not sure if this is needed, leaving it in for now
//    }

    @Test
    public void testGetAllBuildingsByCompanyId() {
        // Arrange

        //mock
        List<BuildingDto> mockedList = mock(List.class);

        when(mockedCompanyService.getAllBuildingsByCompanyId(companyId)).thenReturn(mockedList);


        // Act
        ResponseEntity<List<BuildingDto>> testResponseEntity = testController.getAllBuildingsByCompanyId(companyId);


        // Assert
        assertEquals(HttpStatus.OK, testResponseEntity.getStatusCode());
        assertEquals(ResponseEntity.ok(mockedList), testResponseEntity);
    }

    @Test
    public void testCreateCondo() {
        // Arrange

        //mock
        CondoDto mockedCondoDto = mock(CondoDto.class);

        when(mockedCompanyService.createCondoByCompanyId(companyId, buildingId, mockedCondoDto)).thenReturn(mockedCondoDto);


        // Act
        ResponseEntity<CondoDto> testResponseEntity = testController.createCondo(companyId, buildingId, mockedCondoDto);


        // Assert
        assertEquals(HttpStatus.OK, testResponseEntity.getStatusCode());
        assertEquals(ResponseEntity.ok(mockedCondoDto), testResponseEntity);
    }
}
