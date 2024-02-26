package com.rently.rentlyAPI.services;

import com.rently.rentlyAPI.dto.UpdateProfileRequestDto;
import com.rently.rentlyAPI.dto.UserProfileDto;
import com.rently.rentlyAPI.entity.User;

import java.security.Principal;
import java.util.Optional;

public interface UserService {
    Optional<User> findByEmail(String email);
    
    public User updateProfile(UpdateProfileRequestDto request, Integer userId);
    
    public UserProfileDto viewProfile(Integer userId);

}
