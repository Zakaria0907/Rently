package com.rently.rentlyAPI.services;

import com.rently.rentlyAPI.entity.user.Owner;

import java.util.Optional;


public interface OwnerService {
    Optional<Owner> findByEmail(String email);
}
