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

import java.util.List;

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

    /**
     * This method finds a user with the given email and returns it, regardless of the role
     *
     * @param email the email of the user to be found
     * @return User with the given email
     * @throws AuthenticationException if no user with the given email is found
     */
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

    /**
     * This method checks if a user with the given email exists in the database, regardless of the role
     *
     * @param email the email of the user to be checked
     * @return true if the user exists, false otherwise
     */
    public boolean userExistsForRegistration(String email) {
        try {
            findUserAccordingToTypeWithEmail(email);
        } catch (AuthenticationException e) {
            // this means no user exists with this email
            return false;
        }
        return true;
    }

    @Override
    public SystemAdminDto registerSystemAdmin(SystemAdminDto systemAdminDto) {
        if (userExistsForRegistration(systemAdminDto.getEmail()) == false) {
            return systemAdminService.registerSystemAdmin(systemAdminDto);
        }
        throw new AuthenticationException("User with email " + systemAdminDto.getEmail() + " already exists.");

    }


    @Override
    public CompanyAdminDto registerCompanyAdmin(CompanyAdminDto companyAdminDto) {
        if (userExistsForRegistration(companyAdminDto.getEmail()) == false) {
            return companyAdminService.registerCompanyAdminAndLinkToCompany(companyAdminDto);
        }
        throw new AuthenticationException("User with email " + companyAdminDto.getEmail() + " already exists.");
    }

    @Override
    public EmployeeDto registerEmployee(EmployeeDto employeeDto) {
        if (userExistsForRegistration(employeeDto.getEmail()) == false) {
            return employeeService.registerEmployee(employeeDto);
        }
        throw new AuthenticationException("User with email " + employeeDto.getEmail() + " already exists.");
    }

    @Override
    public PublicUserDto registerPublicUser(PublicUserDto publicUserDto) {
        if (userExistsForRegistration(publicUserDto.getEmail()) == false) {
            return publicUserService.registerPublicUser(publicUserDto);
        }
        throw new AuthenticationException("User with email " + publicUserDto.getEmail() + " already exists.");
    }

    @Override
    public String userKeyActivation(String token, String key) {
        // find the condo with the key
        Condo condo = companyAdminService.getCondoEntityByRegistrationKey(key);

        String tokenWithoutBearer = token.substring(7);
        String userEmail = jwtUtils.extractUsername(tokenWithoutBearer);
        User user = findUserAccordingToTypeWithEmail(userEmail);

        if (user.getRole().equals(Role.PUBLIC_USER)) {
            Occupant newOccupant = publicUserService.transformToOccupant(userEmail, key);
            return companyAdminService.linkOccupantToHousingContract(newOccupant, condo).toString();
        }

        if (user.getRole().toString().startsWith("OW") || user.getRole().toString().startsWith("RE")) {
            return companyAdminService.linkOccupantToHousingContract((Occupant) user, condo).toString();

        }

        return "Invalid user trying to activate key";

    }

    @Override
    public List<SystemAdminDto> getAllSystemAdmins() {
        return systemAdminService.getAllSystemAdmins();
    }

    @Override
    public List<CompanyAdminDto> getAllCompanyAdmins() {
        return companyAdminService.getAllCompanyAdmins();
    }


}

