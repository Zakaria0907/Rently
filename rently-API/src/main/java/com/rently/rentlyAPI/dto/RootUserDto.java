package com.rently.rentlyAPI.dto;

import com.rently.rentlyAPI.entity.user.CompanyAdmin;
import com.rently.rentlyAPI.entity.user.Employee;
import com.rently.rentlyAPI.entity.user.SystemAdmin;
import com.rently.rentlyAPI.entity.user.User;
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
        } else if (user instanceof CompanyAdmin) {
            return CompanyAdminDto.fromEntity((CompanyAdmin) user);
        } else if (user instanceof Employee) {
            return EmployeeDto.fromEntity((Employee) user);
        } else {
            throw new IllegalArgumentException("Unknown user type");
        }
    }
}
