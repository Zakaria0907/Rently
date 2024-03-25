package com.rently.rentlyAPI.services.impl;

import com.rently.rentlyAPI.dto.OwnerRequestDto;
import com.rently.rentlyAPI.entity.Building;
import com.rently.rentlyAPI.entity.Company;
import com.rently.rentlyAPI.entity.OwnerRequest;
import com.rently.rentlyAPI.entity.user.Owner;
import com.rently.rentlyAPI.exceptions.AuthenticationException;
import com.rently.rentlyAPI.repository.OwnerRequestRepository;
import com.rently.rentlyAPI.services.OwnerRequestService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public List<OwnerRequestDto> getAllOwnerRequests(Integer ownerId) {
        return ownerRequestRepository.findAllByOwnerId(ownerId).stream()
                .map(OwnerRequestDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public OwnerRequestDto getOwnerRequestById(Integer ownerId, Integer requestId) {
        Optional<OwnerRequest> request = ownerRequestRepository.findById(requestId);
        if (request.isEmpty()) throw new EntityNotFoundException("Request with ID " + requestId + " not found");
        if (request.get().getOwner().getId().equals(ownerId)) {
            return OwnerRequestDto.fromEntity(request.get());
        }
        throw new AuthenticationException("You do not have access to other people's requests");
    }
}
