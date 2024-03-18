package com.rently.rentlyAPI.services;

import com.rently.rentlyAPI.dto.BuildingDto;
import com.rently.rentlyAPI.dto.CompanyAdminDto;
import com.rently.rentlyAPI.dto.CompanyDto;
import com.rently.rentlyAPI.dto.SystemAdminDto;
import com.rently.rentlyAPI.entity.user.CompanyAdmin;

import java.util.List;

public interface CompanyAdminService {
    
    // Find a CompanyAdminDto by email
    CompanyAdminDto findCompanyAdminDtoByEmail(String email);
    
    // Find a CompanyAdminEntity by email
    CompanyAdmin findCompanyAdminEntityByEmail(String email);
    
    // Find a CompanyAdminDto by ID
    CompanyAdminDto findCompanyAdminDtoById(Integer companyAdminId);
    
    // Find a CompanyAdminEntity by ID
    CompanyAdmin findCompanyAdminEntityById(Integer companyAdminId);

    // Register a new CompanyAdmin and link to a Company
    CompanyAdminDto registerCompanyAdminAndLinkToCompany(CompanyAdminDto companyAdminDto);
    
    // Update an existing CompanyAdmin
    CompanyAdminDto updateCompanyAdmin(CompanyAdminDto companyAdminDto);
    
    // Delete a CompanyAdmin by ID
    void deleteCompanyAdminById(Integer companyAdminId);
    
    // Retrieve all CompanyAdmins
    List<CompanyAdminDto> getAllCompanyAdmins();
    
    // Retrieve all CompanyAdmins by company name
    List<CompanyAdminDto> getAllCompanyAdminsByCompanyName(String companyName);
    
    // Retrieve all CompanyAdmins by company id
    List<CompanyAdminDto> getAllCompanyAdminsByCompanyId(Integer companyId);
    
    /*
     * The following methods are calling other services
     */
    BuildingDto createBuildingAndLinkToCompany(BuildingDto buildingDto);

}
