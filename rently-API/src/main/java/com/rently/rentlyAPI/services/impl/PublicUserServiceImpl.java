package com.rently.rentlyAPI.services.impl;

import com.rently.rentlyAPI.dto.CompanyAdminDto;
import com.rently.rentlyAPI.dto.PublicUserDto;
import com.rently.rentlyAPI.entity.user.CompanyAdmin;
import com.rently.rentlyAPI.entity.user.PublicUser;
import com.rently.rentlyAPI.exceptions.AuthenticationException;
import com.rently.rentlyAPI.exceptions.OperationNonPermittedException;
import com.rently.rentlyAPI.repository.PublicUserRepository;
import com.rently.rentlyAPI.security.utils.JwtUtils;
import com.rently.rentlyAPI.services.PublicUserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PublicUserServiceImpl implements PublicUserService {

    private final PublicUserRepository publicUserRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils;

    @Override
    public Optional<PublicUser> findOptionalByEmail(String email) {
        return publicUserRepository.findByEmail(email);
    }

    @Override
    public PublicUser findPublicUserEntityByToken(String token) {
        String email = jwtUtils.extractUsername(token);
        return findPublicUserEntityByEmail(email);
    }

    @Override
    public PublicUserDto findPublicUserDtoByEmail(String email) {
        PublicUser publicUser = findPublicUserEntityByEmail(email);
        return PublicUserDto.fromEntity(publicUser);
    }

    @Override
    public PublicUser findPublicUserEntityByEmail(String email) {
        return publicUserRepository.findByEmail(email)
                .orElseThrow(() -> new AuthenticationException("PublicUser with email " + email + " not found"));
    }

    @Override
    public PublicUserDto findPublicUserDtoById(Integer publicUserId) {
        PublicUser publicUser = findPublicUserEntityById(publicUserId);
        return PublicUserDto.fromEntity(publicUser);
    }

    @Override
    public PublicUser findPublicUserEntityById(Integer publicUserId) {
        return publicUserRepository.findById(publicUserId)
                .orElseThrow(() -> new EntityNotFoundException("PublicUser with ID " + publicUserId + " not found"));
    }

    @Override
    public PublicUserDto registerPublicUser(PublicUserDto publicUserDto) {
        //check the user does not already exist
        Optional<PublicUser> existingPublicUser = publicUserRepository.findByEmail(publicUserDto.getEmail());

        if (existingPublicUser.isPresent()){
            throw new AuthenticationException("This email is already associated with an account");
        }

        if (publicUserDto.getPassword() != null) {
            // Encode the password if provided (null with google for example)
            String encodedPassword = passwordEncoder.encode(publicUserDto.getPassword());
            publicUserDto.setPassword(encodedPassword);
        }

        PublicUser publicUserToSave = PublicUserDto.toEntity(publicUserDto);

        PublicUser savedCompany = publicUserRepository.save(publicUserToSave);

        return PublicUserDto.fromEntity(savedCompany);
    }

    @Override
    public PublicUserDto updatePublicUser(PublicUserDto publicUserDto) {
        // Find the CompanyAdmin Entity by its ID
        PublicUser publicUserToUpdate = findPublicUserEntityById(publicUserDto.getId());

        // Update the CompanyAdmin details if present
        if (publicUserDto.getEmail() != null && !publicUserDto.getEmail().isEmpty()) {
            publicUserToUpdate.setEmail(publicUserDto.getEmail());
        }

        if (publicUserDto.getFirstName() != null && !publicUserDto.getFirstName().isEmpty()) {
            publicUserToUpdate.setFirstName(publicUserDto.getFirstName());
        }

        if (publicUserDto.getLastName() != null && !publicUserDto.getLastName().isEmpty()) {
            publicUserToUpdate.setLastName(publicUserDto.getLastName());
        }

        if (publicUserDto.getPhoneNumber() != null) {
            publicUserToUpdate.setPhoneNumber(publicUserDto.getPhoneNumber());
        }

        if (publicUserDto.getBio() != null) {
            publicUserToUpdate.setBio(publicUserDto.getBio());
        }

        // Save the updated CompanyAdmin
        PublicUser updatedPublicUser = publicUserRepository.save(publicUserToUpdate);

        return PublicUserDto.fromEntity(updatedPublicUser);
    }

    @Transactional
    public String deletePublicUserById(Integer publicUserId) {

        try {
            PublicUser publicUser = publicUserRepository.findById(publicUserId)
                    .orElseThrow(() -> new EntityNotFoundException("PublicUser with ID " + publicUserId + " not found"));

            publicUserRepository.delete(publicUser);
            return "PublicUser with ID " + publicUserId + " deleted successfully.";
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("PublicUser with ID " + publicUserId + " not found");
        }
    }

    @Override
    public List<PublicUserDto> getAllPublicUsers() {
        List<PublicUser> publicUsers = publicUserRepository.findAll();
        return publicUsers.stream()
                .map(PublicUserDto::fromEntity)
                .toList();
    }
}

