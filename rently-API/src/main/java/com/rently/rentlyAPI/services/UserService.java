package com.rently.rentlyAPI.services;

import com.rently.rentlyAPI.entity.Key;
import com.rently.rentlyAPI.entity.User;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserService {
    Optional<User> findByEmail(String email);

    User activateKeyToChangeRole(String key);



}
