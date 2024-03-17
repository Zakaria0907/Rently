package com.rently.rentlyAPI.services;

import com.rently.rentlyAPI.dto.BuildingDto;
import com.rently.rentlyAPI.dto.CompanyDto;
import com.rently.rentlyAPI.entity.Company;

import java.util.List;

public interface CompanyService {

  CompanyDto createCompany(CompanyDto companyDto);

  CompanyDto getCompanyById(Integer companyId);

  CompanyDto updateCompanyById(Integer companyId, CompanyDto companyDto);

  void deleteCompanyById(Integer companyId);

  List<CompanyDto> getAllCompanies();

//  Company save(Company company);
//  BuildingDto createBuildingByCompanyId(Integer companyId, BuildingDto buildingDto);
//  BuildingDto getBuildingByCompanyIdAndBuildingId(Integer companyId, Integer buildingId);
//  BuildingDto updateBuildingByCompanyIdAndBuildingId(Integer companyId, Integer buildingId, BuildingDto buildingDto);
//  void deleteBuildingByCompanyIdAndBuildingId(Integer companyId, Integer buildingId);
//  List<BuildingDto> getAllBuildingsByCompanyId(Integer companyId);
//  Integer countCondosById(Integer buildingId);
//  List<CondoDto> findAllCondosByBuildingId(Integer buildingId);
//
//  CondoDto createCondoByCompanyId(Integer companyId, Integer buildingId, CondoDto condoDto);
//  CondoDto getCondoByBuildingIdAndCondoId(Integer buildingId,Integer condoId);
//  CondoDto updateCondoByBuildingIdAndCondoId(Integer buildingId, Integer condoId, CondoDto condoDto);
//
//  void deleteCondoByBuildingIdAndCondoId(Integer buildingId, Integer condoId);
//  KeyDto createActivationKeyToBecomeRenter(String userEmail, Integer companyId);
//  KeyDto createActivationKeyToBecomeOwner(String userEmail, Integer companyId);
}
