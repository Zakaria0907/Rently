package com.rently.rentlyAPI.services.impl;

import com.rently.rentlyAPI.entity.user.Owner;
import com.rently.rentlyAPI.repository.OwnerRepository;
import com.rently.rentlyAPI.services.OwnerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;

    @Override
    public Optional<Owner> findByEmail(String email) {
        return ownerRepository.findByEmail(email);
    }

    @Override
    public Optional<Owner> findOwnerEntityById(Integer occupantId) {
        return ownerRepository.findById(occupantId);
    }
}
