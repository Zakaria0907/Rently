//package com.rently.rentlyAPI.services;
//
//import com.rently.rentlyAPI.dto.BuildingDto;
//import com.rently.rentlyAPI.dto.CompanyDto;
//import com.rently.rentlyAPI.dto.CondoDto;
//import com.rently.rentlyAPI.dto.KeyDto;
//import com.rently.rentlyAPI.entity.Company;
//
//import java.util.List;
//
//public interface CompanyService {
//
//  CompanyDto createCompany(CompanyDto companyDto);
//
//  CompanyDto getCompanyByName(String name);
//  CompanyDto getCompanyById(Integer id);
//
//  CompanyDto updateCompany(String name, CompanyDto companyDto);
//  CompanyDto updateCompanyById(Integer id, CompanyDto companyDto);
//
//  CompanyDto deleteCompany(String name);
//  CompanyDto deleteCompanyById(Integer id);
//
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
//}
