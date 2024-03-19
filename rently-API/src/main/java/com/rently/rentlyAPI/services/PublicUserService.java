package com.rently.rentlyAPI.services;

import com.rently.rentlyAPI.dto.PublicUserDto;
import com.rently.rentlyAPI.entity.user.PublicUser;

import java.util.List;
import java.util.Optional;

public interface PublicUserService {

     // Find a PublicUserOptional by email
     Optional<PublicUser> findOptionalByEmail(String email);

     // Find a PublicUser entity by token
     PublicUser findPublicUserEntityByToken(String token);

     // Find a PublicUserDto by email
     PublicUserDto findPublicUserDtoByEmail(String email);

     // Find a PublicUser entity by email
     PublicUser findPublicUserEntityByEmail(String email);

     // Find a PublicUserDto by ID
     PublicUserDto findPublicUserDtoById(Integer publicUserId);

     // Find a PublicUser entity by ID
     PublicUser findPublicUserEntityById(Integer publicUserId);

     // Register a new PublicUser
        PublicUserDto registerPublicUser(PublicUserDto publicUserDto);

     // Update an existing PublicUser
     PublicUserDto updatePublicUser(PublicUserDto publicUserDto);

     // Delete a PublicUser by ID
     String deletePublicUserById(Integer publicUserId);

     // Retrieve all PublicUsers
     List<PublicUserDto> getAllPublicUsers();
     
     String requestKeyToChangeRole(String token, String role);
     
     String activateKeyToChangeRole(String token, String key);
}
