//package com.rently.rentlyAPI.services.impl;
//
//import com.rently.rentlyAPI.dto.CompanyDto;
//import com.rently.rentlyAPI.entity.Company;
//import com.rently.rentlyAPI.exceptions.OperationNonPermittedException;
//import com.rently.rentlyAPI.repository.CompanyRepository;
//import com.rently.rentlyAPI.services.AdminService;
//import jakarta.persistence.EntityNotFoundException;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//
//@Service
//@AllArgsConstructor
//public class AdminServiceImpl implements AdminService {
//    private final CompanyRepository companyRepository;
//
//    @Override
//    public CompanyDto createCompany(CompanyDto companyDto) {
//        Optional<Company> existing = companyRepository.findCompanyByName(companyDto.getName());
//        if(existing.isPresent())
//            throw new OperationNonPermittedException("There is already a company with the name: "+companyDto.getName());
//        Company company = CompanyDto.toEntity(companyDto);
//        Company savedCompany = companyRepository.save(company);
//        return CompanyDto.fromEntity(savedCompany);
//    }
//
//    @Transactional
//    public void deleteCompanyById(Integer companyId) {
//        companyRepository.findById(companyId)
//                .orElseThrow(() -> new EntityNotFoundException("Company with ID " + companyId + " not found"));
//        companyRepository.deleteCompanyById(companyId);
//    }
//
//    @Override
//    @Transactional
//    public void deleteCompanyByName(String name) {
//        companyRepository.findCompanyByName(name)
//                .orElseThrow(() -> new EntityNotFoundException("Company with name: " + name + " not found"));
//        companyRepository.deleteCompanyByName(name);
//    }
//
//    @Override
//    public CompanyDto getCompanyById(Integer companyId) {
//        Company company = companyRepository.findById(companyId)
//                .orElseThrow(() -> new EntityNotFoundException("Company with id: " + companyId + " not found"));
//        return CompanyDto.fromEntity(company);
//    }
//
//    @Override
//    public CompanyDto getCompanyByName(String name) {
//        Company company = companyRepository.findCompanyByName(name)
//                .orElseThrow(() -> new EntityNotFoundException("Company with name: " + name + " not found"));
//        return CompanyDto.fromEntity(company);
//    }
//
//    @Override
//    public List<CompanyDto> getAllCompanies() {
//        List<Company> companies = companyRepository.findAll();
//        return companies.stream().map(CompanyDto::fromEntity).collect(Collectors.toList());
//    }
//}
