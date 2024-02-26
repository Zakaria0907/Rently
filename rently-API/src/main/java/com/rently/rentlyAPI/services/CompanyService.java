package com.rently.rentlyAPI.services;

import com.rently.rentlyAPI.dto.BuildingDto;
import com.rently.rentlyAPI.dto.CondoDto;
import com.rently.rentlyAPI.dto.KeyDto;

import java.util.List;

public interface CompanyService {
   
   BuildingDto createBuildingByCompanyId(Integer companyId, BuildingDto buildingDto);
   
    BuildingDto getBuildingByCompanyIdAndBuildingId(Integer companyId, Integer buildingId);
    
    List<BuildingDto> getAllBuildingsByCompanyId(Integer companyId);
    CondoDto createCondoByCompanyId(Integer companyId, CondoDto condoDto);

   KeyDto createActivationKeyToBecomeRenter(String userEmail, Integer companyId);
   KeyDto createActivationKeyToBecomeOwner(String userEmail, Integer companyId);
}
