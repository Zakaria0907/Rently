package com.rently.rentlyAPI.services;

import com.rently.rentlyAPI.dto.OwnerRequestDto;
import com.rently.rentlyAPI.entity.Building;
import com.rently.rentlyAPI.entity.OwnerRequest;
import com.rently.rentlyAPI.entity.user.Owner;

public interface OwnerRequestService {
    OwnerRequestDto createOwnerRequest(Owner owner, Building building, OwnerRequest ownerRequest);

}
