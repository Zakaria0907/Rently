package com.rently.rentlyAPI.controller;

import com.rently.rentlyAPI.dto.CompanyAdminDto;
import com.rently.rentlyAPI.dto.SystemAdminDto;
import com.rently.rentlyAPI.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/system-admin")
@RequiredArgsConstructor
public class SystemAdminController {
//
    private final UserService userService;

    @PostMapping(path = "/create/sytem-admin")
    public ResponseEntity<SystemAdminDto> createSystemAdmin(@RequestBody SystemAdminDto systemAdminDto) {
        return ResponseEntity.ok(userService.registerSystemAdmin(systemAdminDto));
    }

    @PostMapping(path = "/create/company-admin/company={companyId}")
    public ResponseEntity<CompanyAdminDto> createCompanyAdmin(@RequestBody CompanyAdminDto companyAdminDto, @PathVariable("companyId") Integer companyId) {
        return ResponseEntity.ok(userService.registerCompanyAdmin(companyAdminDto,companyId));
    }

//
//    // super admin actions on companies
//    @GetMapping("/companies/all")
//    public ResponseEntity<List<CompanyDto>> getAllCompanies() {
//        List<CompanyDto> companies = adminService.getAllCompanies();
//        return ResponseEntity.ok(companies);
//    }
//
//    @GetMapping("/companies/id={id}")
//    public ResponseEntity<CompanyDto> getCompanyById( @PathVariable("id") Integer companyId) {
//        CompanyDto company = adminService.getCompanyById(companyId);
//        return ResponseEntity.ok(company);
//    }
//    @GetMapping("/companies/name={name}")
//    public ResponseEntity<CompanyDto> getCompanyByName( @PathVariable("name") String name) {
//        CompanyDto company = adminService.getCompanyByName(name);
//        return ResponseEntity.ok(company);
//    }
//
//    @PostMapping(path = "/companies/create")
//    public ResponseEntity<CompanyDto> createCompany(@RequestBody CompanyDto company) {
//        return ResponseEntity.ok(adminService.createCompany(company));
//    }
//
//    @DeleteMapping("/companies/delete/id={companyId}")
//    public ResponseEntity<String> deleteCompanyById(@PathVariable("companyId") Integer companyId) {
//        adminService.deleteCompanyById(companyId);
//        return ResponseEntity.ok("Company deleted successfully");
//    }
//    @DeleteMapping("/companies/delete/name={companyName}")
//    public ResponseEntity<String> deleteCompanyByName(@PathVariable("companyName") String companyName) {
//        adminService.deleteCompanyByName(companyName);
//        return ResponseEntity.ok("Company deleted successfully");
    }
//
//}
