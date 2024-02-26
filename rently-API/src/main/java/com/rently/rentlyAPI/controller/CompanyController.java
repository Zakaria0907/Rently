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
    
    // ==== BUILDING ENDPOINTS ====
    // Endpoint to create a building by companyId
    @PostMapping("{company-id}/create-building" )
    public ResponseEntity<BuildingDto> createBuilding(
        @PathVariable (value = "company-id") Integer companyId, @RequestBody BuildingDto buildingDto) {
        return ResponseEntity.ok(companyService.createBuildingByCompanyId(companyId, buildingDto));
    }
    
    // Endpoint to get a building by companyId and buildingId
    @GetMapping("/{companyId}/building/{buildingId}")
    public ResponseEntity<BuildingDto> getBuildingByCompanyIdAndBuildingId(@PathVariable("companyId") Integer companyId, @PathVariable("buildingId") Integer buildingId) {
        BuildingDto building = companyService.getBuildingByCompanyIdAndBuildingId(companyId, buildingId);
        return ResponseEntity.ok(building);
    }
    
    //TODO: Endpoint to update a building by companyId and buildingId
    @PutMapping("/{companyId}/building/{buildingId}/update")
    public ResponseEntity<BuildingDto> updateBuildingByCompanyIdAndBuildingId(@PathVariable("companyId") Integer companyId, @PathVariable("buildingId") Integer buildingId, @RequestBody BuildingDto buildingDto) {
//        return ResponseEntity.ok(companyService.updateBuildingByCompanyIdAndBuildingId(companyId, buildingId, buildingDto));
        return null;
    }
    
    // Endpoint to get all buildings by companyId
    @GetMapping("{companyId}/building/all")
    public ResponseEntity<List<BuildingDto>> getAllBuildingsByCompanyId(@PathVariable("companyId") Integer companyId) {
        List<BuildingDto> buildings = companyService.getAllBuildingsByCompanyId(companyId);
        return ResponseEntity.ok(buildings);
    }
    
    
    // ==== CONDOS ENDPOINTS ====
    // Endpoint to create a condo by companyId and buildingId
    @PostMapping("{company-id}/building/{building-id}/create-condo" )
    public ResponseEntity<CondoDto> createCondo(
        @PathVariable (value = "company-id") Integer companyId,
        @PathVariable (value = "building-id") Integer buildingId,
        @RequestBody CondoDto condoDto)
    {
        return ResponseEntity.ok(companyService.createCondoByCompanyId(companyId, buildingId, condoDto));
    }
    
    // TODO: Endpoint to get a condo by buildingId and condoId
    @GetMapping("{buildingId}/condo/{condoId}")
    public ResponseEntity<CondoDto> getCondoByBuildingIdAndCondoId(@PathVariable("buildingId") Integer buildingId, @PathVariable("condoId") Integer condoId) {
//        CondoDto condo = companyService.getCondoByBuildingIdAndCondoId(buildingId, condoId);
//        return ResponseEntity.ok(condo);
        return null;
    }
    
    // TODO: Endpoint to update a condo by buildingId and condoId
    @PutMapping("{buildingId}/condo/{condoId}/update")
    public ResponseEntity<CondoDto> updateCondoByBuildingIdAndCondoId(@PathVariable("buildingId") Integer buildingId, @PathVariable("condoId") Integer condoId, @RequestBody CondoDto condoDto) {
//        return ResponseEntity.ok(companyService.updateCondoByBuildingIdAndCondoId(buildingId, condoId, condoDto));
        return null;
    }
    
    // Endpoint to get the count of condos by buildingId
    @GetMapping("{buildingId}/count-condos")
    public ResponseEntity<Integer> countCondosById(@PathVariable("buildingId") Integer buildingId) {
        return ResponseEntity.ok(companyService.countCondosById(buildingId));
    }
    
    // Endpoint to get all condos by buildingId
    @GetMapping("{buildingId}/condos/all")
    public ResponseEntity<List<CondoDto>> findAllCondosByBuildingId(@PathVariable("buildingId") Integer buildingId) {
        List<CondoDto> condos = companyService.findAllCondosByBuildingId(buildingId);
        return ResponseEntity.ok(condos);
    }
    
    
    // ==== ACTIVATION KEY ENDPOINTS ====
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
