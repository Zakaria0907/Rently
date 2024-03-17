package com.rently.rentlyAPI.services.impl;

import com.rently.rentlyAPI.dto.BuildingDto;
import com.rently.rentlyAPI.dto.CompanyAdminDto;
import com.rently.rentlyAPI.dto.CompanyDto;
import com.rently.rentlyAPI.dto.SystemAdminDto;
import com.rently.rentlyAPI.entity.Building;
import com.rently.rentlyAPI.entity.Company;
import com.rently.rentlyAPI.entity.user.CompanyAdmin;
import com.rently.rentlyAPI.entity.user.SystemAdmin;
import com.rently.rentlyAPI.exceptions.AuthenticationException;
import com.rently.rentlyAPI.repository.BuildingRepository;
import com.rently.rentlyAPI.repository.CompanyAdminRepository;
import com.rently.rentlyAPI.services.BuildingService;
import com.rently.rentlyAPI.services.CompanyAdminService;
import com.rently.rentlyAPI.services.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CompanyAdminServiceImpl implements CompanyAdminService {

    private final BuildingService buildingService;
    private final CompanyService companyService;

    private final BuildingRepository buildingRepository;
    private final CompanyAdminRepository companyAdminRepository;


    @Override
    public CompanyAdminDto registerCompanyAdminAndLinkToCompany(CompanyAdminDto companyAdminDto, Integer companyId) {
        // Create company admin
        Optional<CompanyAdmin> companyAdmin = companyAdminRepository.findByEmail(companyAdminDto.getEmail());//.orElse(null);

        if (companyAdmin.isPresent()) {
            throw new AuthenticationException("This email is already associated with an account");
        }

        CompanyAdmin savedUser = companyAdmin.get();

        // Retreive company with the given ID
        CompanyDto companyDto = companyService.getCompanyById(companyId);
        Company company = CompanyDto.toEntity(companyDto);

        savedUser.setCompany(company);
        savedUser = companyAdminRepository.save(savedUser);
        return CompanyAdminDto.fromEntity(savedUser);
    }

    @Override
    public BuildingDto createBuildingAndLinkToCompany(BuildingDto buildingDto, Integer companyId) {
        // Retreive company with the given ID
        CompanyDto companyDto = companyService.getCompanyById(companyId);
        Company company = CompanyDto.toEntity(companyDto);

        // Create the building
        BuildingDto createdBuilding = buildingService.createBuilding(buildingDto);
        Building building = BuildingDto.toEntity(createdBuilding);

        // Link the building to the company
        building.setCompany(company);

        // Save the building
        buildingRepository.save(building);

        return BuildingDto.fromEntity(building);
    }
}
