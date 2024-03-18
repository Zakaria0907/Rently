package com.rently.rentlyAPI.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rently.rentlyAPI.entity.user.SystemAdmin;
import com.rently.rentlyAPI.security.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SystemAdminDto {
    
    @JsonProperty("id")
    private Integer id;
    
    @JsonProperty("number_created_companies")
    private Integer numberCreatedCompanies;

    @JsonProperty("email")
    @NotBlank(message = "The email is required")
    private String email;

    @JsonProperty("password")
    @NotBlank(message = "The password name is required")
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

    public static SystemAdmin toEntity(SystemAdminDto systemAdminDto){
        return SystemAdmin.builder()
                .id(systemAdminDto.getId())
                .numberCreatedCompanies(0)
                .email(systemAdminDto.getEmail())
                .password(systemAdminDto.getPassword())
                .firstName(systemAdminDto.getFirstName())
                .lastName(systemAdminDto.getLastName())
                .phoneNumber(systemAdminDto.getPhoneNumber())
                .bio(systemAdminDto.getBio())
                .role(Role.SYSTEM_ADMIN) // The role is set to SYSTEM_ADMIN by default
                .build();
    }

    public static SystemAdminDto fromEntity(SystemAdmin systemAdmin){
        return SystemAdminDto.builder()
                .id(systemAdmin.getId())
                .numberCreatedCompanies(systemAdmin.getNumberCreatedCompanies())
                .email(systemAdmin.getEmail())
//                .password(systemAdmin.getPassword()) Not sending the password to the client for obvious reasons
                .firstName(systemAdmin.getFirstName())
                .lastName(systemAdmin.getLastName())
                .phoneNumber(systemAdmin.getPhoneNumber())
                .bio(systemAdmin.getBio())
                .role(systemAdmin.getRole().name())
                .build();
    }

}
