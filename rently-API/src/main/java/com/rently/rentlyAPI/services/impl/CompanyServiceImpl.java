package com.rently.rentlyAPI.services.impl;

import com.rently.rentlyAPI.dto.BuildingDto;
import com.rently.rentlyAPI.dto.CondoDto;
import com.rently.rentlyAPI.dto.KeyDto;
import com.rently.rentlyAPI.entity.Building;
import com.rently.rentlyAPI.entity.Condo;
import com.rently.rentlyAPI.entity.Key;
import com.rently.rentlyAPI.entity.User;
import com.rently.rentlyAPI.exceptions.OperationNonPermittedException;
import com.rently.rentlyAPI.repository.CondoRepository;
import com.rently.rentlyAPI.repository.UserRepository;
import com.rently.rentlyAPI.security.Role;
import com.rently.rentlyAPI.services.BuildingService;
import com.rently.rentlyAPI.services.CompanyService;
import com.rently.rentlyAPI.services.CondoService;
import com.rently.rentlyAPI.services.KeyService;
import com.rently.rentlyAPI.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    
    private final BuildingService buildingService;
    private final CondoService condoService;
    private final UserRepository userRepository;
    private final ObjectsValidator<Object> validator;
    private final KeyService keyService;
    private final CondoRepository condoRepository; // unused
    
    // Create a building by company ID
    @Override
    public BuildingDto createBuildingByCompanyId(Integer companyId, BuildingDto buildingDto) {
        
        //Check if the user exists
        User company = userRepository.findById(companyId)
            .orElseThrow(() -> new EntityNotFoundException("User with ID " + companyId + " not found"));
        
        //TODO: Validate the buildingDto
        
        if(company.getRole() != Role.COMPANY){
            throw new OperationNonPermittedException("Only a User with role COMPANY can create a building.");
        }
        
        Building building = BuildingDto.toEntity(buildingDto);
        building.setCompany(company);
        Building savedBuilding = buildingService.save(building);
        return BuildingDto.fromEntity(savedBuilding);
    }
    
    // Get a building by company ID and building ID
    @Override
    public BuildingDto getBuildingByCompanyIdAndBuildingId(Integer companyId, Integer buildingId) {
          
          // Check if the user exists
          userRepository.findById(companyId)
              .orElseThrow(() -> new EntityNotFoundException("User with ID " + companyId + " not found"));
          
          // Check if the building exists
          if(!buildingService.exists(buildingId)){
              throw new EntityNotFoundException("Building with ID " + buildingId + " not found");
          }
          
          Building building = buildingService.findById(buildingId);
          return BuildingDto.fromEntity(building);
    }
    
    // Get all buildings by company ID
    @Override
    public List<BuildingDto> getAllBuildingsByCompanyId(Integer companyId) {
        
        // Check if the user exists
        userRepository.findById(companyId)
            .orElseThrow(() -> new EntityNotFoundException("User with ID " + companyId + " not found"));
        
        List<Building> buildings = buildingService.findAllByCompanyId(companyId);
        return buildings.stream().map(BuildingDto::fromEntity).collect(Collectors.toList());
    }
    
    // Count condos by building ID
    @Override
    public Integer countCondosById(Integer buildingId) {
        if(!buildingService.exists(buildingId)){
            throw new EntityNotFoundException("Building with ID " + buildingId + " not found");
        }
        return condoService.countCondosByBuildingId(buildingId);
    }
    
    // Get all buildings by company ID
    @Override
    public List<CondoDto> findAllCondosByBuildingId(Integer buildingId) {
        
        // Check if the building exists
        if(!buildingService.exists(buildingId)){
            throw new EntityNotFoundException("Building with ID " + buildingId + " not found");
        }
        
        List<Condo> condos = condoService.findAllCondosByBuildingId(buildingId);
        return condos.stream().map(CondoDto::fromEntity).collect(Collectors.toList());
    }
    
    // Create a condo by company ID and building ID (associate the condo with the building)
    @Override
    public CondoDto createCondoByCompanyId(Integer companyId, Integer buildingId, CondoDto condoDto) {
        // Check if the user exists
        User user = userRepository.findById(companyId)
            .orElseThrow(() -> new EntityNotFoundException("User with ID " + companyId + " not found"));
        
        // Check if the building exists
        if(!buildingService.exists(buildingId)){
            throw new EntityNotFoundException("Building with ID " + buildingId + " not found");
        }
        
        // Check if the user has the role COMPANY
        if(user.getRole() != Role.COMPANY){
            throw new OperationNonPermittedException("Only a User with role COMPANY can create a condo.");
        }
        
        // Validate the condoDto
        validator.validate(condoDto);
        
        // Find the building by its ID
        Building building = buildingService.findById(buildingId);
        
        // Convert the condoDto to entity and associate it with the building and user
        Condo condoEntity = CondoDto.toEntity(condoDto);
        condoEntity.setBuilding(building); // Assuming Condo entity has a setBuilding method to establish the relationship
        condoEntity.setUser(user); // Set the user in the condo entity if needed
        
        // Save the condo entity
        Condo savedCondo = condoService.save(condoEntity);
        
        return CondoDto.fromEntity(savedCondo);
    }


    public KeyDto createActivationKeyToBecomeRenter(String userEmail, Integer companyId) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new EntityNotFoundException("User with email " + userEmail + " not found"));

        User company = userRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company with ID " + companyId + " not found"));

        if (user.getRole() != Role.USER) {
            throw new OperationNonPermittedException("User with email " + userEmail + " is not allowed to become a RENTER");
        }

        if (company.getRole() != Role.COMPANY) {
            throw new OperationNonPermittedException("User with email " + userEmail + " is not allowed to attribute a key to a non company user");
        }

        //Build a keyDto with builder
        KeyDto keyDto = KeyDto.builder()
                .userId(user.getId())
                .key(activationKeyGenerator())
                .isActive(false)
                .revoked(false)
                .companyId(companyId)
                .role(Role.RENTER)
                .build();

        // save the key

        Key keyEntity = KeyDto.toEntity(keyDto);
        keyEntity.setUser(user);
        KeyDto key = KeyDto.fromEntity(keyService.save(keyEntity));
        return key;

    }

    public static String activationKeyGenerator() {
        int keyLength = 16;
        String characters = "0123456789";
        StringBuilder activationKey = new StringBuilder();

        SecureRandom secureRandom = new SecureRandom();

        for (int i = 0; i < keyLength; i++) {
            int randomIndex = secureRandom.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            activationKey.append(randomChar);
        }

        return activationKey.toString();
    }


    public KeyDto createActivationKeyToBecomeOwner(String userEmail, Integer companyId) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new EntityNotFoundException("User with email " + userEmail + " not found"));

        User company = userRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company with ID " + companyId + " not found"));

        if (user.getRole() != Role.USER) {
            throw new OperationNonPermittedException("User with email " + userEmail + " is not allowed to become a RENTER");
        }

        if (company.getRole() != Role.COMPANY) {
            throw new OperationNonPermittedException("User with email " + userEmail + " is not allowed to attribute a key to a non company user");
        }

        //Build a keyDto with builder
        KeyDto keyDto = KeyDto.builder()
                .userId(user.getId())
                .key(activationKeyGenerator())
                .isActive(false)
                .revoked(false)
                .companyId(companyId)
                .role(Role.OWNER)
                .build();

        // save the key

        Key keyEntity = KeyDto.toEntity(keyDto);
        keyEntity.setUser(user);
        KeyDto key = KeyDto.fromEntity(keyService.save(keyEntity));
        return key;
    }


}
