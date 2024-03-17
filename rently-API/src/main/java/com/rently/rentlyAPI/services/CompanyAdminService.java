package com.rently.rentlyAPI.services;

import com.rently.rentlyAPI.dto.BuildingDto;
import com.rently.rentlyAPI.dto.CompanyAdminDto;
import com.rently.rentlyAPI.dto.SystemAdminDto;

public interface CompanyAdminService {

    CompanyAdminDto registerCompanyAdminAndLinkToCompany(CompanyAdminDto companyAdminDto, Integer companyId);

    BuildingDto createBuildingAndLinkToCompany(BuildingDto buildingDto, Integer companyId);

}
