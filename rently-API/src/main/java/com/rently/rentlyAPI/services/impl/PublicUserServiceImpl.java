package com.rently.rentlyAPI.services.impl;

import com.rently.rentlyAPI.dto.OwnerDto;
import com.rently.rentlyAPI.dto.PublicUserDto;
import com.rently.rentlyAPI.dto.RenterDto;
import com.rently.rentlyAPI.entity.user.Occupant;
import com.rently.rentlyAPI.entity.user.Owner;
import com.rently.rentlyAPI.entity.user.PublicUser;
import com.rently.rentlyAPI.entity.user.Renter;
import com.rently.rentlyAPI.exceptions.AuthenticationException;
import com.rently.rentlyAPI.repository.OwnerRepository;
import com.rently.rentlyAPI.repository.PublicUserRepository;
import com.rently.rentlyAPI.repository.RenterRepository;
import com.rently.rentlyAPI.services.PublicUserService;
import com.rently.rentlyAPI.utils.JwtUtils;
import com.rently.rentlyAPI.utils.RegistrationKeyUtils;
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
    private final OwnerRepository ownerRepository;
    private final RenterRepository renterRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils;
    private final RegistrationKeyUtils registrationKeyUtils;

    @Override
    public Optional<PublicUser> findByEmail(String email) {
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
        
        PublicUser publicUser = findPublicUserEntityById(publicUserId);
        publicUserRepository.delete(publicUser);
        
        return "Public User deleted successfully";
    }

    @Override
    public List<PublicUserDto> getAllPublicUsers() {
        List<PublicUser> publicUsers = publicUserRepository.findAll();
        return publicUsers.stream()
                .map(PublicUserDto::fromEntity)
                .toList();
    }
    
    @Override
    @Transactional
    public Occupant transformToOccupant(String email, String key) {
        PublicUser publicUser = findPublicUserEntityByEmail(email);
        
//        // Check if the key matches
//        if (!publicUser.getRegistrationKey().equals(key)) {
//            return "Invalid key";
//        }
        
        if (key.startsWith("OW")){
            Owner owner = OwnerDto.fromPublicUser(publicUser);
            deletePublicUserById(publicUser.getId());
            Owner savedOwner = ownerRepository.save(owner);
            return (Occupant) savedOwner;
        }
        
        if (key.startsWith("RE")){
            Renter renter = RenterDto.fromPublicUser(publicUser);
            deletePublicUserById(publicUser.getId());
            Renter savedRenter = renterRepository.save(renter);
            return (Renter) savedRenter;
        }
        
        return null;
    }
}

