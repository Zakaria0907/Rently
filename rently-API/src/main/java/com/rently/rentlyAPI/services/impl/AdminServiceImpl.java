package com.rently.rentlyAPI.services.impl;

import com.rently.rentlyAPI.dto.CompanyDto;
import com.rently.rentlyAPI.entity.Company;
import com.rently.rentlyAPI.repository.CompanyRepository;
import com.rently.rentlyAPI.services.AdminService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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

    @Transactional
    public void deleteCompanyById(Integer companyId){
        companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company with ID " + companyId + " not found"));
        companyRepository.deleteCompanyById(companyId);
    }

    @Override
    @Transactional
    public void deleteCompanyByName(String name) {
        companyRepository.findCompanyByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Company with name: " + name + " not found"));
        companyRepository.deleteCompanyByName(name);
    }
}
