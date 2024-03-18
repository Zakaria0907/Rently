package com.rently.rentlyAPI.services;

import com.rently.rentlyAPI.dto.CompanyDto;
import com.rently.rentlyAPI.dto.SystemAdminDto;
import com.rently.rentlyAPI.entity.user.SystemAdmin;

import java.util.List;
import java.util.Optional;

public interface SystemAdminService {
    Optional<SystemAdmin> findByEmail(String email);

    // Find a SystemAdminDto by email
    SystemAdminDto findSystemAdminDtoByEmail(String email);

    // Find a SystemAdminEntity by email
    SystemAdmin findSystemAdminEntityByEmail(String email);

    // Find a SystemAdminDto by ID
    SystemAdminDto findSystemAdminDtoById(Integer systemAdminId);
    
    // Find a SystemAdminEntity by ID
    SystemAdmin findSystemAdminEntityById(Integer systemAdminId);
    
    // Register a new SystemAdmin
    SystemAdminDto registerSystemAdmin(SystemAdminDto systemAdminDto);
    
    // Update an existing SystemAdmin
    SystemAdminDto updateSystemAdmin(SystemAdminDto systemAdminDto);
    
    // Delete a SystemAdmin by ID
    void deleteSystemAdminById(Integer systemAdminId);
    
    // Retrieve all SystemAdmins
    List<SystemAdminDto> getAllSystemAdmins();
    
    /*
     * The following methods are calling other services
     */
    CompanyDto createCompany(CompanyDto company);
    
    CompanyDto updateCompany(CompanyDto company);

    //    void deleteCompanyById(Integer companyId);
//    void deleteCompanyByName(String name);
//
//    CompanyDto getCompanyById(Integer companyId);
//    CompanyDto getCompanyByName(String name);
//    List<CompanyDto> getAllCompanies();
}
