package com.rently.rentlyAPI.services.impl;

import com.rently.rentlyAPI.dto.*;
import com.rently.rentlyAPI.entity.Condo;
import com.rently.rentlyAPI.entity.HousingContract;
import com.rently.rentlyAPI.dto.CommonFacilityReservationDto;
import com.rently.rentlyAPI.dto.EmployeeAssignmentDto;
import com.rently.rentlyAPI.dto.OwnerRequestDto;
import com.rently.rentlyAPI.entity.user.Occupant;
import com.rently.rentlyAPI.entity.user.Owner;
import com.rently.rentlyAPI.entity.user.Renter;
import com.rently.rentlyAPI.exceptions.AuthenticationException;
import com.rently.rentlyAPI.services.*;
import com.rently.rentlyAPI.utils.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OccupantServiceImpl implements OccupantService {
    private final BuildingService buildingService;
    private final CondoService condoService;
    private final HousingContractService housingContractService;
    private final RenterService renterService;
    private final OwnerService ownerService;
    private final JwtUtils jwtUtils;

    @Override
    public CommonFacilityReservationDto createCommonFacilityReservation(String token, CommonFacilityReservationDto commonFacilityReservationDto) {
        Occupant occupantToLink = findOccupantEntityByToken(token);
        return buildingService.createCommonFacilityReservation(occupantToLink, commonFacilityReservationDto);
    }

    public Occupant findOccupantEntityByToken(String token) {
        String tokenWithoutBearer = token.substring(7);
        String email = jwtUtils.extractUsername(tokenWithoutBearer);
        return findOccupantEntityByEmail(email);

    }

    @Override
    public OwnerRequestDto createOwnerRequest(String token, OwnerRequestDto ownerRequestDto) {
        Occupant occupantToLink = findOccupantEntityByToken(token);
        return ownerService.createOwnerRequest((Owner) occupantToLink, ownerRequestDto);
    }

    @Override
    public void deleteOwnerRequest(String token, Integer id) {
        Occupant occupantToLink = findOccupantEntityByToken(token);
        ownerService.deleteOwnerRequest((Owner) occupantToLink, id);
    }

    @Override
    public List<OwnerRequestDto> getAllOwnerRequests(String token) {
        Integer occupantId = findOccupantEntityByToken(token).getId();
        return ownerService.getAllOwnerRequests(occupantId);
    }

    @Override
    public OwnerRequestDto getOwnerRequestById(String token, Integer requestId) {
        Integer occupantId = findOccupantEntityByToken(token).getId();
        return ownerService.getOwnerRequestById(occupantId, requestId);
    }
    
    @Override
    public List<CondoDto> getMyCondos(String token) {
        // Step 1: Extract Occupant Id
        Integer occupantId = findOccupantEntityByToken(token).getId();
        
        // Step 2: Fetch All Housing Contracts of that occupant
        List<HousingContract> contracts = housingContractService.findHousingContractEntitiesByOccupantId(occupantId);
        
        // Step 3: Get the Condo Ids from the Housing Contracts, fetch them, convert to Dtos and add to the List
        List<CondoDto> condosDto = new ArrayList<>();
        for (HousingContract contract : contracts) {
            Condo condo = condoService.findCondoEntityById(contract.getCondo().getId());
            CondoDto condoDto = CondoDto.fromEntity(condo);
            condosDto.add(condoDto);
        }
        
        // Step 5: Return the List of Condo DTOs
        return condosDto;
    }
    
    @Override
    public HousingContractAndCondoDto getMyCondoInformationById(String token, Integer condoId) {
        // Step 1: Extract Occupant ID
        Integer occupantId = findOccupantEntityByToken(token).getId();
        
        // Step 2: For extra security, check if the occupant has a contract with the condo
        Optional<HousingContract> contractOptional = housingContractService.findHousingContractByCondoIdAndOccupantId(condoId, occupantId);
        
        if (contractOptional.isPresent()) {
            
            Condo condo = condoService.findCondoEntityById(condoId);
            HousingContract contract = contractOptional.get();
            
            CondoDto condoDto = CondoDto.fromEntity(condo);
            HousingContractDto housingContractDto = HousingContractDto.fromEntity(contract);
            
            return new HousingContractAndCondoDto(housingContractDto, condoDto);
        } else {
            throw new AuthenticationException("Occupant with ID " + occupantId + " does not have a contract with Condo with ID " + condoId);
        
        }
    }
    
    @Override
    public List<EmployeeAssignmentDto> getAllOwnerRequestsStatus(String token) {
        List<OwnerRequestDto> ownerRequests = getAllOwnerRequests(token);
        return ownerService.getAssignmentStatuses(ownerRequests);
    }

    @Override
    public EmployeeAssignmentDto getOwnerRequestStatusByRequestId(String token, Integer requestId) {
        OwnerRequestDto ownerRequest = getOwnerRequestById(token, requestId);
        return ownerService.getAssignmentStatus(ownerRequest.getId());
    }

    @Override
    public Occupant findOccupantEntityById(Integer occupantId) {
        Optional<Owner> owner = ownerService.findOwnerEntityById(occupantId);
        Optional<Renter> renter = renterService.findRenterEntityById(occupantId);
        if (owner.isPresent()) {
            return owner.get();
        }
        if (renter.isPresent()) {
            return renter.get();
        }
        throw new AuthenticationException("Occupant with ID " + occupantId + " not found");
    }

    @Override
    public Occupant findOccupantEntityByEmail(String email) {
        Optional<Owner> owner = ownerService.findByEmail(email);
        Optional<Renter> renter = renterService.findByEmail(email);
        if (owner.isPresent()) {
            return owner.get();
        }
        if (renter.isPresent()) {
            return renter.get();
        }
        throw new AuthenticationException("Occupant with ID " + email + " not found");
    }

    @Override
    public void deleteCommonFacilityReservation(String token, Integer id) {
        Integer occupantId = findOccupantEntityByToken(token).getId();
        buildingService.deleteCommonFacilityReservation(occupantId, id);
    }

    @Override
    public CommonFacilityReservationDto getCommonFacilityReservation(String token, Integer id) {
        Integer occupantId = findOccupantEntityByToken(token).getId();
        return buildingService.getCommonFacilityReservation(occupantId, id);
    }

    @Override
    public List<CommonFacilityReservationDto> getAllCommonFacilityReservations(String token) {
        Integer occupantId = findOccupantEntityByToken(token).getId();
        return buildingService.getAllCommonFacilityReservations(occupantId);
    }

}
