package com.rently.rentlyAPI.services.impl;

import com.rently.rentlyAPI.dto.PublicUserDto;
import com.rently.rentlyAPI.entity.user.PublicUser;
import com.rently.rentlyAPI.exceptions.OperationNonPermittedException;
import com.rently.rentlyAPI.repository.PublicUserRepository;
import com.rently.rentlyAPI.services.PublicUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PublicUserServiceImpl implements PublicUserService {

    private final PublicUserRepository publicUserRepository;

    @Override
    public PublicUserDto createPublicUser(PublicUserDto publicUserDto) {
        PublicUser existing = publicUserRepository.findByEmail(publicUserDto.getEmail());
        if(publicUserRepository.isPresent(existing.getEmail()))
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
    public PublicUserDto getPublicUser(Integer publicUserId) {
        return null;
    }

    @Override
    public String deletePublicUser(Integer id) {
        return null;
    }
}
