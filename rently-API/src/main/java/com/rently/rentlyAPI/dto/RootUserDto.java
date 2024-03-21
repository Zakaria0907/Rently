package com.rently.rentlyAPI.dto;

import com.rently.rentlyAPI.entity.user.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
public class RootUserDto {
    
    public static RootUserDto toDto(User user) {
        if (user instanceof SystemAdmin) {
            return SystemAdminDto.fromEntity((SystemAdmin) user);
        }

        if (user instanceof CompanyAdmin) {
            return CompanyAdminDto.fromEntity((CompanyAdmin) user);
        }

        if (user instanceof Employee) {
            return EmployeeDto.fromEntity((Employee) user);
        }
        if (user instanceof PublicUser) {
            return PublicUserDto.fromEntity((PublicUser) user);
        }
        if (user instanceof Owner) {
            return OwnerDto.fromEntity((Owner) user);
        }
        if (user instanceof Renter) {
            return RenterDto.fromEntity((Renter) user);
        }

        throw new IllegalArgumentException("Unknown user type");
    }
}
