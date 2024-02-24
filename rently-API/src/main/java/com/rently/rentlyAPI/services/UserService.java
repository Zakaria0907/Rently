package com.rently.rentlyAPI.services;

import com.rently.rentlyAPI.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByEmail(String email);

}
