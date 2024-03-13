package com.rently.rentlyAPI.services;

import com.rently.rentlyAPI.dto.CompanyDto;

public interface AdminService {

    CompanyDto createCompany(CompanyDto company);
    void deleteCompanyById(Integer companyId);
    void deleteCompanyByName(String name);
}
