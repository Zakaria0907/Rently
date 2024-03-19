package com.rently.rentlyAPI.controller;

import com.rently.rentlyAPI.dto.BuildingDto;
import com.rently.rentlyAPI.dto.CommonFacilityDto;
import com.rently.rentlyAPI.dto.EmployeeDto;
import com.rently.rentlyAPI.dto.EmploymentContractDto;
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

    //company admin can see all his buildings
    @GetMapping(path = "/buildings")
    public ResponseEntity<List<BuildingDto>> getAllBuildings(@RequestHeader("Authorization") String token) {
        // token.substring(7) To remove the Bearer prefix from the token
        Integer companyId = companyAdminService.findCompanyAdminEntityByToken(token).getCompany().getId();
        return ResponseEntity.ok(companyAdminService.getAllBuildingsByCompanyId(companyId));
    }


    //common facilities
    @PostMapping(path = "/create/common-facility")
    public ResponseEntity<CommonFacilityDto> createCommonFacility(@RequestBody CommonFacilityDto commonFacilityDto) {
        return ResponseEntity.ok(companyAdminService.createCommonFacilityAndLinkToBuilding(commonFacilityDto));
    }

    //    @GetMapping(path = "/common-facilities/id={commonFacilityId}")
//    public ResponseEntity<CommonFacilityDto> getCommonFacilityById(@PathVariable(name = "commonFacilityId") Integer commonFacilityId) {
//        return ResponseEntity.ok(companyAdminService.getCommonFacilityById(commonFacilityId));
//    }
//
//    @GetMapping(path = "/common-facilities/name={commonFacilityName}")
//    public ResponseEntity<BuildingDto> getCommonFacilityByName(@PathVariable(name = "commonFacilityName") String commonFacilityName) {
//        return ResponseEntity.ok(companyAdminService.getCommonFacilityByName(commonFacilityName));
//    }
//
    @GetMapping(path = "/common-facilities/building={buildingId}")
    public ResponseEntity<List<CommonFacilityDto>> getAllCommonFacilities(@RequestHeader("Authorization") String token, @PathVariable(name = "buildingId") Integer buildingId) {
        // token.substring(7) To remove the Bearer prefix from the token
        Integer companyId = companyAdminService.findCompanyAdminEntityByToken(token).getCompany().getId();
        return ResponseEntity.ok(companyAdminService.getAllCommonFacilitiesByBuildingId(buildingId));
    }


    // Employees
    @PostMapping(path = "/create/employee")
    public ResponseEntity<EmployeeDto> registerEmployee(@RequestBody EmployeeDto employeeDto) {
        return ResponseEntity.ok(userService.registerEmployee(employeeDto));
    }

    @GetMapping(path = "/employees")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(@RequestHeader("Authorization") String token) {
        // token.substring(7) To remove the Bearer prefix from the token
        Integer companyId = companyAdminService.findCompanyAdminEntityByToken(token).getCompany().getId();
        return ResponseEntity.ok(companyAdminService.getAllEmployeesByCompanyId(companyId));
    }

    @PostMapping(path = "/create/employment-contract")
    public ResponseEntity<EmploymentContractDto> registerEmployee(@RequestBody EmploymentContractDto employmentContractDto) {
        return ResponseEntity.ok(companyAdminService.createEmploymentContract(employmentContractDto));
    }

}
