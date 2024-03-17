package com.rently.rentlyAPI.services.impl;

import com.rently.rentlyAPI.dto.CompanyDto;
import com.rently.rentlyAPI.dto.SystemAdminDto;
import com.rently.rentlyAPI.entity.user.SystemAdmin;
import com.rently.rentlyAPI.exceptions.AuthenticationException;
import com.rently.rentlyAPI.repository.SystemAdminRepository;
import com.rently.rentlyAPI.repository.UserRepository;
import com.rently.rentlyAPI.services.CompanyService;
import com.rently.rentlyAPI.services.SystemAdminService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class SystemAdminServiceImpl implements SystemAdminService {

    SystemAdminRepository systemAdminRepository;

    CompanyService companyService;

    public SystemAdminDto registerSystemAdmin(SystemAdminDto systemAdminDto) {
        // check the system admin does not already exist
        Optional<SystemAdmin> systemAdmin = systemAdminRepository.findByEmail(systemAdminDto.getEmail());//.orElse(null);

        if (systemAdmin.isPresent()) {
            throw new AuthenticationException("This email is already associated with an account");
        }

        //
        SystemAdmin savedUser = systemAdmin.get();
        savedUser = systemAdminRepository.save(savedUser);
        return SystemAdminDto.fromEntity(savedUser);
    }

    @Override
    public CompanyDto createCompany(CompanyDto company) {
        return companyService.createCompany(company);
    }

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
}
