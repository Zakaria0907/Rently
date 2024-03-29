package com.rently.rentlyAPI.services;

import com.rently.rentlyAPI.dto.EmployeeAssignmentDto;
import com.rently.rentlyAPI.dto.OwnerRequestDto;
import com.rently.rentlyAPI.entity.Building;
import com.rently.rentlyAPI.entity.OwnerRequest;
import com.rently.rentlyAPI.entity.user.Owner;

import java.util.List;

public interface OwnerRequestService {
    OwnerRequestDto createOwnerRequest(Owner owner, Building building, OwnerRequest ownerRequest);

    OwnerRequest findOwnerRequestEntityById(Integer id);

    void deleteOwnerRequest(OwnerRequest ownerRequest);

    List<OwnerRequestDto> getAllOwnerRequests(Integer occupantId);

    OwnerRequestDto getOwnerRequestById(Integer ownerId, Integer requestId);

    List<EmployeeAssignmentDto> getAssignmentStatuses(List<OwnerRequestDto> ownerRequests);
}
