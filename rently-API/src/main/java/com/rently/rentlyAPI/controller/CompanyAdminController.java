package com.rently.rentlyAPI.controller;

import com.rently.rentlyAPI.dto.BuildingDto;
import com.rently.rentlyAPI.dto.EmployeeDto;
import com.rently.rentlyAPI.services.CompanyAdminService;
import com.rently.rentlyAPI.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company-admin")
@RequiredArgsConstructor
public class CompanyAdminController {
    private final UserService userService;
    private final CompanyAdminService companyAdminService;

    @PostMapping(path = "/create/building")
    public ResponseEntity<BuildingDto> createBuilding(@RequestBody BuildingDto buildingDto) {
        return ResponseEntity.ok(companyAdminService.createBuildingAndLinkToCompany(buildingDto));
    }

    @GetMapping(path = "/buildings/id={buildingId}")
    public ResponseEntity<BuildingDto> getBuildingById(@PathVariable(name = "buildingId") Integer buildingId) {
        return ResponseEntity.ok(companyAdminService.getBuildingById(buildingId));
    }
    @GetMapping(path = "/buildings/name={buildingName}")
    public ResponseEntity<BuildingDto> getBuildingByName(@PathVariable(name = "buildingName") String buildingName) {
        return ResponseEntity.ok(companyAdminService.getBuildingByName(buildingName));
    }
    @GetMapping(path = "/buildings")
    public ResponseEntity<List<BuildingDto>> getAllBuildings() {
        return ResponseEntity.ok(companyAdminService.getAllBuildings());
    }

    @PostMapping(path = "/create/employee")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        return ResponseEntity.ok(userService.registerEmployee(employeeDto));
    }}
