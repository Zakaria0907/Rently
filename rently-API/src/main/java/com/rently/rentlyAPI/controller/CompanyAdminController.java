package com.rently.rentlyAPI.controller;

import com.rently.rentlyAPI.dto.BuildingDto;
import com.rently.rentlyAPI.dto.CompanyDto;
import com.rently.rentlyAPI.services.CompanyAdminService;
import com.rently.rentlyAPI.services.SystemAdminService;
import com.rently.rentlyAPI.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
