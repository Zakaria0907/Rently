package com.rently.rentlyAPI.services.impl;

import com.rently.rentlyAPI.dto.*;
import com.rently.rentlyAPI.entity.Building;
import com.rently.rentlyAPI.entity.Company;
import com.rently.rentlyAPI.entity.EmploymentContract;
import com.rently.rentlyAPI.entity.user.CompanyAdmin;
import com.rently.rentlyAPI.entity.user.Employee;
import com.rently.rentlyAPI.exceptions.AuthenticationException;
import com.rently.rentlyAPI.repository.CompanyAdminRepository;
import com.rently.rentlyAPI.repository.EmploymentContractRepository;
import com.rently.rentlyAPI.security.utils.JwtUtils;
import com.rently.rentlyAPI.services.*;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CompanyAdminServiceImpl implements CompanyAdminService {

    private final CompanyService companyService;
    private final BuildingService buildingService;
    private final EmployeeService employeeService;
    private final CommonFacilityService commonFacilityService;

    private final CompanyAdminRepository companyAdminRepository;
    private final EmploymentContractRepository employmentContractRepository;

    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;


    @Override
    public CompanyAdmin findCompanyAdminEntityByToken(String token) {
        String tokenWithoutBearer = token.substring(7);
        String email = jwtUtils.extractUsername(tokenWithoutBearer);
        return findCompanyAdminEntityByEmail(email);
    }

    @Override
    public CompanyAdminDto findCompanyAdminDtoByEmail(String email) {
        CompanyAdmin companyAdmin = findCompanyAdminEntityByEmail(email);
        return CompanyAdminDto.fromEntity(companyAdmin);
    }

    @Override
    public CompanyAdmin findCompanyAdminEntityByEmail(String email) {
        return companyAdminRepository.findByEmail(email)
                .orElseThrow(() -> new AuthenticationException("CompanyAdmin with email " + email + " not found"));
    }
    
    @Override
    public CompanyAdminDto findCompanyAdminDtoById(Integer companyAdminId) {
        CompanyAdmin companyAdmin = findCompanyAdminEntityById(companyAdminId);
        return CompanyAdminDto.fromEntity(companyAdmin);
    }
    
    @Override
    public CompanyAdmin findCompanyAdminEntityById(Integer companyAdminId) {
        return companyAdminRepository.findById(companyAdminId)
                .orElseThrow(() -> new AuthenticationException("CompanyAdmin with ID " + companyAdminId + " not found"));
    }

    @Override
    @Transactional
    public CompanyAdminDto registerCompanyAdminAndLinkToCompany(CompanyAdminDto companyAdminDto) {
        // Check if the email is already associated with an account
        Optional<CompanyAdmin> existingCompanyAdmin = companyAdminRepository.findByEmail(companyAdminDto.getEmail());

        // If the email is already associated with an account, throw an exception
        if (existingCompanyAdmin.isPresent()) {
            throw new AuthenticationException("This email is already associated with an account");
        }

        if (companyAdminDto.getPassword() != null) {
            // Encode the password if provided (null with google for example)
            String encodedPassword = passwordEncoder.encode(companyAdminDto.getPassword());
            companyAdminDto.setPassword(encodedPassword);
        }

        // Retreive company with the given ID
        Company companyToLink = companyService.findCompanyEntityById(companyAdminDto.getCompanyId());

        // Create the company admin
        CompanyAdmin companyAdminToSave = CompanyAdminDto.toEntity(companyAdminDto);

        // Link the company admin to the company
        companyAdminToSave.setCompany(companyToLink);
        
        // Save the company admin
        CompanyAdmin savedCompanyAdmin = companyAdminRepository.save(companyAdminToSave);
        
        // Return the company adminDto
        return CompanyAdminDto.fromEntity(savedCompanyAdmin);
    }
    
    @Override
    public CompanyAdminDto updateCompanyAdmin(CompanyAdminDto companyAdminDto) {
        // Find the CompanyAdmin Entity by its ID
        CompanyAdmin companyAdminToUpdate = findCompanyAdminEntityById(companyAdminDto.getId());
        
        // Update the CompanyAdmin details if present
        if (companyAdminDto.getEmail() != null && !companyAdminDto.getEmail().isEmpty()) {
            companyAdminToUpdate.setEmail(companyAdminDto.getEmail());
        }
        
        if (companyAdminDto.getFirstName() != null && !companyAdminDto.getFirstName().isEmpty()) {
            companyAdminToUpdate.setFirstName(companyAdminDto.getFirstName());
        }
        
        if (companyAdminDto.getLastName() != null && !companyAdminDto.getLastName().isEmpty()) {
            companyAdminToUpdate.setLastName(companyAdminDto.getLastName());
        }
        
        if (companyAdminDto.getPhoneNumber() != null) {
            companyAdminToUpdate.setPhoneNumber(companyAdminDto.getPhoneNumber());
        }
        
        if (companyAdminDto.getBio() != null) {
            companyAdminToUpdate.setBio(companyAdminDto.getBio());
        }
        
        // Save the updated CompanyAdmin
        CompanyAdmin updatedCompanyAdmin = companyAdminRepository.save(companyAdminToUpdate);
        
        return CompanyAdminDto.fromEntity(updatedCompanyAdmin);
    }
    
    @Override
    public void deleteCompanyAdminById(Integer companyAdminId) {
        
        // Find the CompanyAdmin Entity by its ID
        CompanyAdmin companyAdminToDelete = findCompanyAdminEntityById(companyAdminId);
        
        // If the CompanyAdmin is found, delete it
        companyAdminRepository.delete(companyAdminToDelete);
    }
    
    @Override
    public List<CompanyAdminDto> getAllCompanyAdmins() {
        List<CompanyAdmin> companyAdmins = companyAdminRepository.findAll();
        return companyAdmins.stream()
                .map(CompanyAdminDto::fromEntity)
                .toList();
    }
    
    @Override
    public List<CompanyAdminDto> getAllCompanyAdminsByCompanyName(String companyName) {
        List<CompanyAdmin> companyAdmins = companyAdminRepository.findAllByCompanyName(companyName);
        return companyAdmins.stream()
                .map(CompanyAdminDto::fromEntity)
                .toList();
    }
    
    @Override
    public List<CompanyAdminDto> getAllCompanyAdminsByCompanyId(Integer companyId) {
        List<CompanyAdmin> companyAdmins = companyAdminRepository.findAllByCompanyId(companyId);
        return companyAdmins.stream()
                .map(CompanyAdminDto::fromEntity)
                .toList();
    }
    
    @Override
    public BuildingDto createBuildingAndLinkToCompany(BuildingDto buildingDto) {
        return buildingService.createBuildingAndLinkToCompany(buildingDto);

    }

    @Override
    public BuildingDto getBuildingByName(String buildingName) {
        Building building = buildingService.findBuildingEntityByName(buildingName);
        return BuildingDto.fromEntity(building);
    }

    @Override
    public BuildingDto getBuildingById(Integer buildingId) {
        return BuildingDto.fromEntity(buildingService.findBuildingEntityById(buildingId));
    }

    @Override
    public List<BuildingDto> getAllBuildings() {
        return buildingService.getAllBuildings(); //buildingRepository.findAll().stream().map(BuildingDto::fromEntity).toList();
    }

    @Override
    public EmploymentContractDto createEmploymentContract(EmploymentContractDto employmentContractDto) {
        // no need to check that the company exists, we know it exists because the company admin exists
        // Check that the building exists
        Building building = buildingService.findBuildingEntityById(employmentContractDto.getBuildingId());
        // check that the employee exists
        Employee employee = employeeService.findById(employmentContractDto.getEmployeeId());

        Company company = companyService.findCompanyEntityById(employmentContractDto.getCompanyId());

        // Create the employment contract
        EmploymentContract employmentContractToSave = EmploymentContractDto.toEntity(employmentContractDto);
        employmentContractToSave.setCompany(company);
        employmentContractToSave.setBuilding(building);
        employmentContractToSave.setEmployee(employee);

        // Save the employment contract
        EmploymentContract savedEmploymentContract = employmentContractRepository.save(employmentContractToSave);

        return EmploymentContractDto.fromEntity(savedEmploymentContract);

    }

    @Override
    public Optional<CompanyAdmin> findByEmail(String email) {
        return companyAdminRepository.findByEmail(email);
    }

    @Override
    public CommonFacilityDto createCommonFacilityAndLinkToBuilding(CommonFacilityDto commonFacilityDto) {
        return commonFacilityService.createCommonFacilityAndLinkToBuilding(commonFacilityDto);
    }

    @Override
    public List<BuildingDto> getAllBuildingsByCompanyId(Integer companyId) {
        return buildingService.getAllBuildingsByCompanyId(companyId);
    }

    @Override
    public List<EmployeeDto> getAllEmployeesByCompanyId(Integer companyId) {
        return employeeService.getAllEmployeesByCompanyId(companyId);
    }
}
