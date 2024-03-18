package com.rently.rentlyAPI.services;

//import com.rently.rentlyAPI.dto.UpdateProfileRequestDto;
//import com.rently.rentlyAPI.dto.UserProfileDto;
//import com.rently.rentlyAPI.entity.User.User;

import com.rently.rentlyAPI.dto.CompanyAdminDto;
import com.rently.rentlyAPI.dto.SystemAdminDto;
import com.rently.rentlyAPI.dto.UserDto;
import com.rently.rentlyAPI.entity.user.User;

import java.util.Optional;

public interface UserService {
    
    SystemAdminDto registerSystemAdmin(SystemAdminDto systemAdminDto);
    
    CompanyAdminDto registerCompanyAdmin(CompanyAdminDto companyAdminDto);

//
//    public User updateProfile(UpdateProfileRequestDto request, Integer userId);
//
//    public UserProfileDto viewProfile(Integer userId);
//
//    User activateKeyToChangeRole(String key);
//
//
//
}
