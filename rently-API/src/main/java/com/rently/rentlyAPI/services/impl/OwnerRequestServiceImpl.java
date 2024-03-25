package com.rently.rentlyAPI.services.impl;

import com.rently.rentlyAPI.dto.OwnerRequestDto;
import com.rently.rentlyAPI.entity.Building;
import com.rently.rentlyAPI.entity.Company;
import com.rently.rentlyAPI.entity.OwnerRequest;
import com.rently.rentlyAPI.entity.user.Owner;
import com.rently.rentlyAPI.repository.OwnerRequestRepository;
import com.rently.rentlyAPI.services.OwnerRequestService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OwnerRequestServiceImpl implements OwnerRequestService {
    private final OwnerRequestRepository ownerRequestRepository;

    @Override
    public OwnerRequestDto createOwnerRequest(Owner owner, Building building, OwnerRequest ownerRequest) {
        Company company = building.getCompany();
        ownerRequest.setOwner(owner);
        ownerRequest.setBuilding(building);
        ownerRequest.setCompany(company);

        OwnerRequest savedOwnerRequest = ownerRequestRepository.save(ownerRequest);

        return OwnerRequestDto.fromEntity(savedOwnerRequest);
    }

    @Override
    public OwnerRequest findOwnerRequestEntityById(Integer id) {
        return ownerRequestRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Owner request with ID " + id + " not found"));
    }

    @Override
    public void deleteOwnerRequest(OwnerRequest ownerRequest) {
        ownerRequestRepository.delete(ownerRequest);
    }
}
