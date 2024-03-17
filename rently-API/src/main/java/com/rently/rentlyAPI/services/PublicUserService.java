package com.rently.rentlyAPI.services;

import com.rently.rentlyAPI.dto.PublicUserDto;

public interface PublicUserService {

     PublicUserDto createPublicUser(PublicUserDto publicUserDto);

     PublicUserDto updatePublicUser(PublicUserDto publicUserDto, Integer publicUserId);

     PublicUserDto getPublicUserById(Integer publicUserId);

     PublicUserDto getPublicUserByEmail(String publicUserEmail);

     String deletePublicUserById(Integer id);

}
