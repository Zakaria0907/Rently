package com.rently.rentlyAPI.services.impl;

import com.rently.rentlyAPI.dto.PublicUserDto;
import com.rently.rentlyAPI.entity.user.PublicUser;
import com.rently.rentlyAPI.exceptions.OperationNonPermittedException;
import com.rently.rentlyAPI.repository.PublicUserRepository;
import com.rently.rentlyAPI.services.PublicUserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PublicUserServiceImpl implements PublicUserService {

    private final PublicUserRepository publicUserRepository;

    @Override
    public PublicUserDto createPublicUser(PublicUserDto publicUserDto) {
        //check the user does not already exist
        Optional<PublicUser> existing = publicUserRepository.findByEmail(publicUserDto.getEmail());

        if (existing.isPresent())
            throw new OperationNonPermittedException("There is already a public user with this email: " + publicUserDto.getEmail());

        PublicUser publicUser = PublicUserDto.toEntity(publicUserDto);
        PublicUser savedCompany = publicUserRepository.save(publicUser);
        return PublicUserDto.fromEntity(savedCompany);
    }

    @Override
    public PublicUserDto updatePublicUser(PublicUserDto publicUserDto, Integer publicUserId) {
        return null;
    }

    @Override
    public PublicUserDto getPublicUserById(Integer publicUserId) {
        return null;
    }

    @Override
    public PublicUserDto getPublicUserByEmail(String publicUserEmail) {
        return null;
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
}
