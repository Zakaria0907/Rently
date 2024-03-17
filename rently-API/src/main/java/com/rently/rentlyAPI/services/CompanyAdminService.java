package com.rently.rentlyAPI.services;

import com.rently.rentlyAPI.dto.BuildingDto;

public interface CompanyAdminService {

    BuildingDto createBuildingAndLinkToCompany(BuildingDto buildingDto, Integer companyId);

}
