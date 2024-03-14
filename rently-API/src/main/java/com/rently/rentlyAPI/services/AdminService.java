package com.rently.rentlyAPI.services;

import com.rently.rentlyAPI.dto.CompanyDto;

import java.util.List;

public interface AdminService {

    CompanyDto createCompany(CompanyDto company);
    void deleteCompanyById(Integer companyId);
    void deleteCompanyByName(String name);

    CompanyDto getCompanyById(Integer companyId);
    CompanyDto getCompanyByName(String name);
    List<CompanyDto> getAllCompanies();
}
