package com.rently.rentlyAPI.services;

import com.rently.rentlyAPI.dto.PublicUserDto;
import com.rently.rentlyAPI.entity.user.PublicUser;

public interface PublicUserService {

     PublicUserDto createPublicUser(PublicUserDto publicUserDto);


     PublicUserDto updatePublicUser(PublicUserDto publicUserDto, Integer publicUserId);


     PublicUserDto getPublicUser(Integer publicUserId);


     String deletePublicUser(Integer id);

}
