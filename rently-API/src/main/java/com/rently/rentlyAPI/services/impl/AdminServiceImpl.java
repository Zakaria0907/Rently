package com.rently.rentlyAPI.services.impl;

import com.rently.rentlyAPI.dto.CompanyDto;
import com.rently.rentlyAPI.entity.Company;
import com.rently.rentlyAPI.repository.CompanyRepository;
import com.rently.rentlyAPI.services.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final CompanyRepository companyRepository;

    @Override
    public CompanyDto createCompany(CompanyDto company) {
        Company authorEntity = CompanyDto.toEntity(company);
        Company savedCompany = companyRepository.save(authorEntity);
        return CompanyDto.fromEntity(savedCompany);
    }
}
