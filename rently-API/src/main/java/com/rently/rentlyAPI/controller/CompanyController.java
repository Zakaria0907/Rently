package com.rently.rentlyAPI.controller;

import com.rently.rentlyAPI.dto.BuildingDto;
import com.rently.rentlyAPI.dto.CondoDto;
import com.rently.rentlyAPI.dto.KeyDto;
import com.rently.rentlyAPI.services.CompanyService;
import com.rently.rentlyAPI.services.CondoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/company")
@Tag(name = "Company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;
    
    // Endpoint to get a building by companyId and buildingId
    @GetMapping("/{companyId}/building/{buildingId}")
    public ResponseEntity<BuildingDto> getBuildingByCompanyIdAndBuildingId(@PathVariable("companyId") Integer companyId, @PathVariable("buildingId") Integer buildingId) {
        BuildingDto building = companyService.getBuildingByCompanyIdAndBuildingId(companyId, buildingId);
        return ResponseEntity.ok(building);
    }
    
    // Endpoint to get all buildings by companyId
    @GetMapping("/building/all/{companyId}")
    public ResponseEntity<List<BuildingDto>> getAllBuildingsByCompanyId(@PathVariable("companyId") Integer companyId) {
        List<BuildingDto> buildings = companyService.getAllBuildingsByCompanyId(companyId);
        return ResponseEntity.ok(buildings);
    }
    
    // Endpoint to create a building by companyId
    @PostMapping("/create-building/{company-id}" )
    public ResponseEntity<BuildingDto> createBuilding(@PathVariable (value = "company-id") Integer companyId, @RequestBody BuildingDto buildingDto) {
        return ResponseEntity.ok(companyService.createBuildingByCompanyId(companyId, buildingDto));
    }
    
    //TODO: modify the endpoint to associate the condo with the building
    @PostMapping("/create-condo/{company-id}" )
    public ResponseEntity<CondoDto> createCondo(@PathVariable (value = "company-id") Integer companyId, @RequestBody CondoDto condoDto) {
        return ResponseEntity.ok(companyService.createCondoByCompanyId(companyId, condoDto));
    }
    
    @PostMapping("/generate-renter-key/{company-id}")
    public ResponseEntity<KeyDto> createActivationKeyToBecomeRenter (@PathVariable (value = "company-id") Integer companyId,@RequestParam String userEmail) {
        return ResponseEntity.ok(companyService.createActivationKeyToBecomeRenter(userEmail, companyId));
    }
    //sample url : http://localhost:8080/api/v1/company/generate-owner-key/23123?userEmail=abc
    @PostMapping("/generate-owner-key/{company-id}")
    public ResponseEntity<KeyDto> createActivationKeyToBecomeOwner(@PathVariable(value = "company-id") Integer companyId, @RequestParam String userEmail) {
        return ResponseEntity.ok(companyService.createActivationKeyToBecomeOwner(userEmail, companyId));
    }

}
