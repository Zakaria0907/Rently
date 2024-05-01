package com.rently.rentlyAPI.services.impl;

import com.rently.rentlyAPI.dto.CompanyDto;
import com.rently.rentlyAPI.dto.SystemAdminDto;
import com.rently.rentlyAPI.entity.user.SystemAdmin;
import com.rently.rentlyAPI.exceptions.AuthenticationException;
import com.rently.rentlyAPI.repository.SystemAdminRepository;
import com.rently.rentlyAPI.services.CompanyService;
import com.rently.rentlyAPI.services.SystemAdminService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class SystemAdminServiceImpl implements SystemAdminService {

    private final CompanyService companyService;

    private final SystemAdminRepository systemAdminRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<SystemAdmin> findByEmail(String email) {
        return systemAdminRepository.findByEmail(email);
    }

    @Override
    public SystemAdminDto findSystemAdminDtoByEmail(String email) {
        SystemAdmin systemAdmin = findSystemAdminEntityByEmail(email);
        return SystemAdminDto.fromEntity(systemAdmin);
    }

    @Override
    public SystemAdmin findSystemAdminEntityByEmail(String email) {
        return systemAdminRepository.findByEmail(email).orElseThrow(() -> new AuthenticationException("SystemAdmin with email " + email + " not found"));
    }

    @Override
    public SystemAdminDto findSystemAdminDtoById(Integer systemAdminId) {
        SystemAdmin systemAdmin = findSystemAdminEntityById(systemAdminId);
        return SystemAdminDto.fromEntity(systemAdmin);
    }

    @Override
    public SystemAdmin findSystemAdminEntityById(Integer systemAdminId) {

        return systemAdminRepository.findById(systemAdminId).orElseThrow(() -> new EntityNotFoundException("SystemAdmin with ID " + systemAdminId + " not found"));
    }

    @Override
    public SystemAdminDto registerSystemAdmin(SystemAdminDto systemAdminDto) {
        // Encode the password
        if (systemAdminDto.getPassword() != null) {
            String encodedPassword = passwordEncoder.encode(systemAdminDto.getPassword());
            systemAdminDto.setPassword(encodedPassword);
        }

        // Convert the SystemAdminDto to a SystemAdmin entity and save it
        SystemAdmin systemAdminToSave = SystemAdminDto.toEntity(systemAdminDto);
        systemAdminToSave = systemAdminRepository.save(systemAdminToSave);

        return SystemAdminDto.fromEntity(systemAdminToSave);
    }

    @Override
    public SystemAdminDto updateSystemAdmin(SystemAdminDto systemAdminDto) {
        // Find the SystemAdmin Entity by its ID
        SystemAdmin systemAdminToUpdate = findSystemAdminEntityById(systemAdminDto.getId());

        // Update SystemAdmin details if present
        if (systemAdminDto.getNumberCreatedCompanies() != null && systemAdminDto.getNumberCreatedCompanies() >= 0) {
            systemAdminToUpdate.setEmail(systemAdminDto.getEmail());
        }

        if (systemAdminDto.getFirstName() != null && !systemAdminDto.getFirstName().isEmpty()) {
            systemAdminToUpdate.setFirstName(systemAdminDto.getFirstName());
        }

        if (systemAdminDto.getLastName() != null && !systemAdminDto.getLastName().isEmpty()) {
            systemAdminToUpdate.setLastName(systemAdminDto.getLastName());
        }

        if (systemAdminDto.getPhoneNumber() != null) {
            systemAdminToUpdate.setPhoneNumber(systemAdminDto.getPhoneNumber());
        }

        if (systemAdminDto.getBio() != null) {
            systemAdminToUpdate.setBio(systemAdminDto.getBio());
        }

        // Save the updated SystemAdmin
        SystemAdmin updatedSystemAdmin = systemAdminRepository.save(systemAdminToUpdate);

        return SystemAdminDto.fromEntity(updatedSystemAdmin);
    }

    @Override
    public void deleteSystemAdminById(Integer systemAdminId) {

        // Find the SystemAdmin Entity by its ID
        SystemAdmin systemAdminToDelete = findSystemAdminEntityById(systemAdminId);

        // If the SystemAdmin is found, delete it
        systemAdminRepository.delete(systemAdminToDelete);
    }

    @Override
    public List<SystemAdminDto> getAllSystemAdmins() {
        List<SystemAdmin> systemAdmins = systemAdminRepository.findAll();
        return systemAdmins.stream().map(SystemAdminDto::fromEntity).toList();
    }

    @Override
    public CompanyDto createCompany(CompanyDto companyDto) {
        return companyService.createCompany(companyDto);
    }

    @Override
    public CompanyDto updateCompany(CompanyDto companyDto) {
        return companyService.updateCompany(companyDto);
    }


}
