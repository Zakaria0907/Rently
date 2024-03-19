package com.rently.rentlyAPI.services.impl;

import com.rently.rentlyAPI.entity.user.Renter;
import com.rently.rentlyAPI.repository.RenterRepository;
import com.rently.rentlyAPI.services.RenterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RenterServiceImpl implements RenterService {
    private final RenterRepository renterRepository;

    @Override
    public Optional<Renter> findByEmail(String email) {
        return renterRepository.findByEmail(email);
    }
}
