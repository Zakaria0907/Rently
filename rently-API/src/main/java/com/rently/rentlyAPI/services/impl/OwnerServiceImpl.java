package com.rently.rentlyAPI.services.impl;

import com.rently.rentlyAPI.dto.EmployeeAssignmentDto;
import com.rently.rentlyAPI.dto.OwnerRequestDto;
import com.rently.rentlyAPI.entity.Building;
import com.rently.rentlyAPI.entity.OwnerRequest;
import com.rently.rentlyAPI.entity.user.Owner;
import com.rently.rentlyAPI.repository.OwnerRepository;
import com.rently.rentlyAPI.services.BuildingService;
import com.rently.rentlyAPI.services.OwnerRequestService;
import com.rently.rentlyAPI.services.OwnerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;
    private final BuildingService buildingService;
    private final OwnerRequestService ownerRequestService;

    @Override
    public Optional<Owner> findByEmail(String email) {
        return ownerRepository.findByEmail(email);
    }

    @Override
    public Optional<Owner> findOwnerEntityById(Integer occupantId) {
        return ownerRepository.findById(occupantId);
    }

    @Override
    public OwnerRequestDto createOwnerRequest(Owner owner, OwnerRequestDto ownerRequestDto) {
        Building building = buildingService.findBuildingEntityById(ownerRequestDto.getBuildingId());
        OwnerRequest ownerRequest = OwnerRequestDto.toEntity(ownerRequestDto);
        return ownerRequestService.createOwnerRequest(owner, building, ownerRequest);
    }

    @Override
    public void deleteOwnerRequest(Owner owner, Integer id) {
        OwnerRequest ownerRequest = ownerRequestService.findOwnerRequestEntityById(id);
        if (owner.equals(ownerRequest.getOwner()))
            ownerRequestService.deleteOwnerRequest(ownerRequest);

    }

    @Override
    public List<OwnerRequestDto> getAllOwnerRequests(Integer ownerId) {
        return ownerRequestService.getAllOwnerRequests(ownerId);
    }

    @Override
    public OwnerRequestDto getOwnerRequestById(Integer ownerId, Integer requestId) {
        return ownerRequestService.getOwnerRequestById(ownerId, requestId);
    }

    @Override
    public List<EmployeeAssignmentDto> getAssignmentStatuses(List<OwnerRequestDto> ownerRequests) {

        return ownerRequestService.getAssignmentStatuses(ownerRequests);
    }

    @Override
    public EmployeeAssignmentDto getAssignmentStatus(Integer requestId) {
        return ownerRequestService.getAssignmentStatusByRequestId(requestId);
    }
}
