package com.rently.rentlyAPI.services.impl;

import com.rently.rentlyAPI.dto.CompanyDto;
import com.rently.rentlyAPI.entity.Company;
import com.rently.rentlyAPI.exceptions.OperationNonPermittedException;
import com.rently.rentlyAPI.repository.CompanyRepository;
import com.rently.rentlyAPI.services.CompanyService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    
    @Override
    public CompanyDto findCompanyDtoById(Integer companyId) {
        
        Company company = findCompanyEntityById(companyId);
        return CompanyDto.fromEntity(company);
    }
    
    @Override
    public Company findCompanyEntityById(Integer companyId) {
        
        return companyRepository.findById(companyId)
            .orElseThrow(() -> new EntityNotFoundException("Company with ID " + companyId + " not found"));
    }

    @Override
    public CompanyDto createCompany(CompanyDto companyDto) {
        // Check if the company already exists
        Optional<Company> company = companyRepository.findCompanyByName(companyDto.getName());
        
        // If the company is found, throw an exception
        if(company.isPresent()){
            throw new OperationNonPermittedException("There is already a company with name: "+companyDto.getName());
        }
        
        // Convert the CompanyDto to an entity and save it
        Company companyToSave = CompanyDto.toEntity(companyDto);
        Company savedCompany = companyRepository.save(companyToSave);
        
        return CompanyDto.fromEntity(savedCompany);
    }
    
    @Override
    public CompanyDto updateCompany(CompanyDto companyDto) {
        
        // Find the Company Entity by its ID
        Company companyToUpdate = findCompanyEntityById(companyDto.getId());

        // Update Company details if present
        if (companyDto.getName() != null && !companyDto.getName().isEmpty()) {
            companyToUpdate.setName(companyDto.getName());
        }

        // Save the updated Company
        Company updatedCompany = companyRepository.save(companyToUpdate);
        
        return CompanyDto.fromEntity(updatedCompany);
    }

    @Override
    public void deleteCompanyById(Integer companyId) {
        
        // Attempt to retrieve the company by ID
        Company companyToDelete = findCompanyEntityById(companyId);
        
        // If the company is found, delete it
        companyRepository.delete(companyToDelete);
    }

    @Override
    public List<CompanyDto> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        return companies.stream()
                .map(CompanyDto::fromEntity)
                .toList();
    }

}
