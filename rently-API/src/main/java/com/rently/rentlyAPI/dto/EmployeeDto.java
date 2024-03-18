package com.rently.rentlyAPI.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rently.rentlyAPI.entity.user.Employee;
import com.rently.rentlyAPI.security.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class EmployeeDto {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("email")
    @NotBlank(message = "The email is required")
    private String email;

    @JsonProperty("password")
    @NotBlank(message = "The password is required")
    private String password;

    @JsonProperty("first_name")
    @NotBlank(message = "The first name is required")
    private String firstName;

    @JsonProperty("last_name")
    @NotBlank(message = "The last name is required")
    private String lastName;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("bio")
    private String bio;

    @JsonProperty("role")
    private String role;

    @JsonProperty("company_id")
    private Integer companyId;


    public static Employee toEntity(EmployeeDto employeeDto){
        return Employee.builder()
                .email(employeeDto.getEmail())
                .password(employeeDto.getPassword())
                .firstName(employeeDto.getFirstName())
                .lastName(employeeDto.getLastName())
                .phoneNumber(employeeDto.getPhoneNumber())
                .bio(employeeDto.getBio())
                .role(Role.EMPLOYEE)
                // company is set in the business logic
                .build();

    }

    public static EmployeeDto fromEntity(Employee employee){
        return EmployeeDto.builder()
                .id(employee.getId())
                .email(employee.getEmail())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .phoneNumber(employee.getPhoneNumber())
                .bio(employee.getBio())
                .role(employee.getRole().name())
                .companyId(employee.getCompany().getId())
                .build();
    }

}
