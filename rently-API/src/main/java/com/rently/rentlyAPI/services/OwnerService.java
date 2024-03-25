package com.rently.rentlyAPI.services;

import com.rently.rentlyAPI.dto.OwnerRequestDto;
import com.rently.rentlyAPI.entity.user.Owner;

import java.util.List;
import java.util.Optional;


public interface OwnerService {
    Optional<Owner> findByEmail(String email);

    Optional<Owner> findOwnerEntityById(Integer occupantId);

    OwnerRequestDto createOwnerRequest(Owner owner, OwnerRequestDto ownerRequestDto);

    void deleteOwnerRequest(Owner owner, Integer id);

    List<OwnerRequestDto> getAllOwnerRequests(Integer occupantId);
}
