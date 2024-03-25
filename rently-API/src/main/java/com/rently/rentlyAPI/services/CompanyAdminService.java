package com.rently.rentlyAPI.services;

import com.rently.rentlyAPI.dto.*;
import com.rently.rentlyAPI.entity.Condo;
import com.rently.rentlyAPI.entity.user.CompanyAdmin;
import com.rently.rentlyAPI.entity.user.Occupant;

import java.util.List;
import java.util.Optional;

public interface CompanyAdminService {

    //Find a CompanyAdminEntity by token
    CompanyAdmin findCompanyAdminEntityByToken(String token);

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
    BuildingDto createBuildingAndLinkToCompany(String token, BuildingDto buildingDto);

    BuildingDto getBuildingByName(String token, String buildingName);

    BuildingDto getBuildingById(String token, Integer buildingId);

    List<BuildingDto> getAllBuildings();

    EmploymentContractDto createEmploymentContract(EmploymentContractDto employmentContractDto);

    List<EmployeeDto> getAllEmployeesByCompanyId(Integer companyId);

    Optional<CompanyAdmin> findByEmail(String email);

    CommonFacilityDto createCommonFacilityAndLinkToBuilding(CommonFacilityDto commonFacilityDto);

    List<BuildingDto> getAllBuildingsByCompanyId(Integer companyId);

    CommonFacilityDto getCommonFacilityById(Integer commonFacilityId);

    List<CommonFacilityDto> getAllCommonFacilitiesByBuildingId(Integer buildingId);

    List<CommonFacilityDto> getAllCommonFacilities();

    void deleteCommonFacilityById(Integer commonFacilityId);

    CondoDto createCondoAndLinkToBuilding(CondoDto condoDto);

    Condo getCondoEntityByRegistrationKey(String registrationKey);

    String generateKeyForCondoAndCreateHousingContract(RegistrationKeyRequestDto registrationKeyRequestDto, HousingContractDto housingContractDto);

    String sendKeyAndHousingContractToFutureOccupant(EmailDto emailDto);

    HousingContractDto linkOccupantToHousingContract(Occupant occupant, Condo condo);

    void deleteCompanyAdmin(Integer id);
}
