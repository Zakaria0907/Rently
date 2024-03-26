package com.rently.rentlyAPI.services.impl;

import com.rently.rentlyAPI.dto.*;
import com.rently.rentlyAPI.entity.*;
import com.rently.rentlyAPI.entity.user.CompanyAdmin;
import com.rently.rentlyAPI.entity.user.Employee;
import com.rently.rentlyAPI.entity.user.Occupant;
import com.rently.rentlyAPI.exceptions.AuthenticationException;
import com.rently.rentlyAPI.repository.CompanyAdminRepository;
import com.rently.rentlyAPI.repository.EmploymentContractRepository;
import com.rently.rentlyAPI.services.*;
import com.rently.rentlyAPI.utils.JwtUtils;
import com.rently.rentlyAPI.utils.RegistrationKeyUtils;
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
    private final HousingContractService housingContractService;
    private final CondoService condoService;

    private final CompanyAdminRepository companyAdminRepository;
    private final EmploymentContractRepository employmentContractRepository;

    private final JwtUtils jwtUtils;
    private final RegistrationKeyUtils registrationKeyUtils;
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
    public Optional<CompanyAdmin> findByEmail(String email) {
        return companyAdminRepository.findByEmail(email);
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

        if (companyAdminDto.getPassword() != null) {
            // Encode the password if provided (null with Google for example)
            String encodedPassword = passwordEncoder.encode(companyAdminDto.getPassword());
            companyAdminDto.setPassword(encodedPassword);
        }

        // Retrieve company with the given ID
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
    
    
    /*
     * Building logic
     */
    @Override
    public BuildingDto createBuildingAndLinkToCompany(String token, BuildingDto buildingDto) {
        // this adds an extra layer of security, the company admin can only create a building for his company
        Integer adminCompanyId = findCompanyAdminEntityByToken(token).getCompany().getId();
        if (!buildingDto.getCompanyId().equals(adminCompanyId)) {
            throw new AuthenticationException("You are not authorized to create a building for another company");
        }

        return buildingService.createBuildingAndLinkToCompany(buildingDto);
    }

    @Override
    public BuildingDto getBuildingByName(String token, String buildingName) {
        Integer adminCompanyId = findCompanyAdminEntityByToken(token).getCompany().getId();
        if (buildingService.findBuildingEntityByName(buildingName).getCompany().getId().equals(adminCompanyId)) {
            throw new AuthenticationException("You are not authorized to access a building in another company");
        }

        Building building = buildingService.findBuildingEntityByName(buildingName);
        return BuildingDto.fromEntity(building);
    }

    @Override
    public BuildingDto getBuildingById(String token, Integer buildingId) {
        Integer adminCompanyId = findCompanyAdminEntityByToken(token).getCompany().getId();
        if (!buildingService.findBuildingEntityById(buildingId).getCompany().getId().equals(adminCompanyId)) {
            throw new AuthenticationException("You are not authorized to access a building in another company");
        }
        return BuildingDto.fromEntity(buildingService.findBuildingEntityById(buildingId));
    }

    @Override
    public List<BuildingDto> getAllBuildings() {
        return buildingService.getAllBuildings();
    }
    
    @Override
    public List<BuildingDto> getAllBuildingsByCompanyId(Integer companyId) {
        return buildingService.getAllBuildingsByCompanyId(companyId);
    }
    
    
    /*
     * Condo logic
     */
    @Override
    public Condo getCondoEntityByRegistrationKey(String registrationKey) {
        return condoService.findCondoEntityByRegistrationKey(registrationKey);
    }
    @Override
    public CondoDto createCondoAndLinkToBuilding(CondoDto condoDto) {
        return condoService.createCondoAndLinkToBuilding(condoDto);
    }
    
    @Override
    public String generateKeyForCondoAndCreateHousingContract(RegistrationKeyRequestDto registrationKeyRequestDto, HousingContractDto housingContractDto) {
	    Condo condo = condoService.findCondoEntityById(registrationKeyRequestDto.getCondoId());
	    Company company = companyService.findCompanyEntityById(condo.getBuilding().getCompany().getId());
	    String registrationKey;
	    
      // This might be bit strange, but it's to ensure that the key is unique by generating a new key until it's unique
      do {
		    registrationKey = registrationKeyUtils.generateRegistrationKey(registrationKeyRequestDto.getRole());
	    } while (condoService.keyExists(registrationKey));
      
      condo.setRegistrationKey(registrationKey);
      condoService.updateCondo(CondoDto.fromEntity(condo));
      
      // Create a minimal housing contract (no occupant, no company, no condo)
      HousingContract minimalHousingContract = housingContractService.createHousingContractWithoutOccupant(company, condo, housingContractDto);
      
      return registrationKey;
    }
    
    @Override
    public String sendKeyAndHousingContractToFutureOccupant(EmailDto emailDto) {
        // TODO: Someone needs to implement the email sending logic.
        //  For this case insert a toString of the HousingContractDto and the Key in the emailDto body
        
        return "Key sent to future occupant";
    }

    @Override
    public HousingContractDto linkOccupantToHousingContract(Occupant occupant, Condo condo) {
        HousingContract housingContract = housingContractService.findHousingContractEntityByCondoId(condo.getId());
        //TODO: RENTER and OWNER have a field that count the number of condos they have,
        // this field should be updated. Also the condo status need to be updated
//        condoService.updateStatus(condo.getId(), "OCCUPIED");
//        occupantService.updateOccupationCount(occupant.getId());
        return housingContractService.setOccupantToHousingContract(housingContract, occupant);
    }

    @Override
    public void deleteCompanyAdmin(Integer id) {
        findCompanyAdminEntityById(id);
        companyAdminRepository.deleteById(id);
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
        return employeeService.updateEmployee(employeeDto);
    }


    /*
     * Employee logic
     */
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
    public List<EmployeeDto> getAllEmployeesByCompanyId(Integer companyId) {
        return employeeService.getAllEmployeesByCompanyId(companyId);
    }
    
    
    /*
     * Common Facility logic
     */
    @Override
    public CommonFacilityDto createCommonFacilityAndLinkToBuilding(CommonFacilityDto commonFacilityDto) {
        return buildingService.createCommonFacility(commonFacilityDto);
    }
    
    @Override
    public CommonFacilityDto getCommonFacilityById(Integer commonFacilityId) {
        return buildingService.getCommonFacilityById(commonFacilityId);
    }

    @Override
    public List<CommonFacilityDto> getAllCommonFacilitiesByBuildingId(Integer buildingId) {

        return buildingService.getAllCommonFacilitiesByBuildingId(buildingId);
    }

    @Override
    public List<CommonFacilityDto> getAllCommonFacilities() {
        return buildingService.getAllCommonFacilities();
    }

    @Override
    public void deleteCommonFacilityById(Integer commonFacilityId) {
        buildingService.deleteCommonFacilityById(commonFacilityId);
    }
    
}
