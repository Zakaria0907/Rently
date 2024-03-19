package com.rently.rentlyAPI.services;

import com.rently.rentlyAPI.entity.user.Renter;

import java.util.Optional;

public interface RenterService {
    Optional<Renter> findByEmail(String email);
}
