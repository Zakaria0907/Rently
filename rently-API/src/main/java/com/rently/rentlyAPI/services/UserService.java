package com.rently.rentlyAPI.services;


import com.rently.rentlyAPI.dto.CompanyAdminDto;
import com.rently.rentlyAPI.dto.EmployeeDto;
import com.rently.rentlyAPI.dto.PublicUserDto;
import com.rently.rentlyAPI.dto.SystemAdminDto;
import com.rently.rentlyAPI.entity.user.User;

public interface UserService {
    //for authentication purposes, we want to get the user with its email
    User findUserAccordingToTypeWithEmail(String email);

    SystemAdminDto registerSystemAdmin(SystemAdminDto systemAdminDto);

    CompanyAdminDto registerCompanyAdmin(CompanyAdminDto companyAdminDto);

    EmployeeDto registerEmployee(EmployeeDto employeeDto);

    PublicUserDto registerPublicUser(PublicUserDto publicUserDto);
    
    String userKeyActivation(String token, String key);
}
