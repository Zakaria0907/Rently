package com.rently.rentlyAPI.services.impl;

import com.rently.rentlyAPI.dto.CompanyAdminDto;
import com.rently.rentlyAPI.dto.EmployeeDto;
import com.rently.rentlyAPI.dto.PublicUserDto;
import com.rently.rentlyAPI.dto.SystemAdminDto;
import com.rently.rentlyAPI.entity.Condo;
import com.rently.rentlyAPI.entity.user.Occupant;
import com.rently.rentlyAPI.entity.user.User;
import com.rently.rentlyAPI.exceptions.AuthenticationException;
import com.rently.rentlyAPI.security.Role;
import com.rently.rentlyAPI.services.*;
import com.rently.rentlyAPI.utils.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final SystemAdminService systemAdminService;
    private final CompanyAdminService companyAdminService;
    private final EmployeeService employeeService;
    private final PublicUserService publicUserService;
    private final OwnerService ownerService;
    private final RenterService renterService;
    
    private final JwtUtils jwtUtils;
    

    @Override
    public User findUserAccordingToTypeWithEmail(String email) {

        if (systemAdminService.findByEmail(email).isPresent()) {
            return systemAdminService.findByEmail(email).get();
        }

        if (companyAdminService.findByEmail(email).isPresent()) {
            return companyAdminService.findByEmail(email).get();
        }

        if (employeeService.findByEmail(email).isPresent()) {
            return employeeService.findByEmail(email).get();
        }

        if (publicUserService.findByEmail(email).isPresent()) {
            return publicUserService.findByEmail(email).get();
        }

        if (ownerService.findByEmail(email).isPresent()) {
            return ownerService.findByEmail(email).get();
        }

        if (renterService.findByEmail(email).isPresent()) {
            return renterService.findByEmail(email).get();
        }
        throw new AuthenticationException("User with email " + email + " not found");
    }


    @Override
    public SystemAdminDto registerSystemAdmin(SystemAdminDto systemAdminDto) {
        return systemAdminService.registerSystemAdmin(systemAdminDto);
    }

    @Override
    public CompanyAdminDto registerCompanyAdmin(CompanyAdminDto companyAdminDto) {
        return companyAdminService.registerCompanyAdminAndLinkToCompany(companyAdminDto);
    }

    @Override
    public EmployeeDto registerEmployee(EmployeeDto employeeDto) {
        return employeeService.registerEmployee(employeeDto);
    }

    @Override
    public PublicUserDto registerPublicUser(PublicUserDto publicUserDto) {
        return publicUserService.registerPublicUser(publicUserDto);
    }
    
    @Override
    public String userKeyActivation(String token, String key) {
        // find the condo with the key
        Condo condo = companyAdminService.getCondoEntityByRegistrationKey(key);
        
        String tokenWithoutBearer = token.substring(7);
        String userEmail = jwtUtils.extractUsername(tokenWithoutBearer);
        User user = findUserAccordingToTypeWithEmail(userEmail);
        
        if(user.getRole().equals(Role.PUBLIC_USER)){
            Occupant newOccupant = publicUserService.transformToOccupant(userEmail, key);
            return companyAdminService.linkOccupantToHousingContract(newOccupant, condo).toString();
        }
        
        if(user.getRole().toString().startsWith("OW") || user.getRole().toString().startsWith("RE")){
            return companyAdminService.linkOccupantToHousingContract((Occupant) user, condo).toString();
            
        }
        
        return "Invalid user trying to activate key";
        
    }
    
    
}

