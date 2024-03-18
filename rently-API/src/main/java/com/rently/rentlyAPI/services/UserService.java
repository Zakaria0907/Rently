package com.rently.rentlyAPI.services;

//import com.rently.rentlyAPI.dto.UpdateProfileRequestDto;
//import com.rently.rentlyAPI.dto.UserProfileDto;
//import com.rently.rentlyAPI.entity.User.User;

import com.rently.rentlyAPI.dto.CompanyAdminDto;
import com.rently.rentlyAPI.dto.EmployeeDto;
import com.rently.rentlyAPI.dto.SystemAdminDto;

public interface UserService {
    
    SystemAdminDto registerSystemAdmin(SystemAdminDto systemAdminDto);
    
    CompanyAdminDto registerCompanyAdmin(CompanyAdminDto companyAdminDto);

    EmployeeDto registerEmployee(EmployeeDto employeeDto);

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
