package com.rently.rentlyAPI.controller;

import com.rently.rentlyAPI.dto.CompanyDto;
import com.rently.rentlyAPI.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping
    public String get() {
        return "GET:: admin controller";
    }

    @PostMapping

    public String post() {
        return "POST:: admin controller";
    }

    @PutMapping

    public String put() {
        return "PUT:: admin controller";
    }

    @DeleteMapping
    public String delete() {
        return "DELETE:: admin controller";
    }

    @PostMapping(path = "/create-company")
    public ResponseEntity<CompanyDto> createCompany(@RequestBody CompanyDto company) {
        return ResponseEntity.ok(adminService.createCompany(company));
    }

}
