package com.rently.rentlyAPI.controller;

import com.rently.rentlyAPI.dto.CondoDto;
import com.rently.rentlyAPI.services.CompanyService;
import com.rently.rentlyAPI.services.CondoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/company")
@Tag(name = "Company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping("/create-condo/{company-id}" )
    public ResponseEntity<CondoDto> createCondo(@PathVariable (value = "company-id") Integer companyId, @RequestBody CondoDto condoDto) {
        return ResponseEntity.ok(companyService.createCondoForCompanyById(companyId, condoDto));

    }
}
