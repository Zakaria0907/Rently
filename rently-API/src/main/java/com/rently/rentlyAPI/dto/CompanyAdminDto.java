package com.rently.rentlyAPI.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rently.rentlyAPI.entity.user.CompanyAdmin;
import com.rently.rentlyAPI.security.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
public class CompanyAdminDto extends RootUserDto {

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
    private int companyId;

    public static CompanyAdmin toEntity(CompanyAdminDto companyAdminDto){
        return CompanyAdmin.builder()
                .email(companyAdminDto.getEmail())
                .password(companyAdminDto.getPassword())
                .firstName(companyAdminDto.getFirstName())
                .lastName(companyAdminDto.getLastName())
                .phoneNumber(companyAdminDto.getPhoneNumber())
                .bio(companyAdminDto.getBio())
                .role(Role.COMPANY_ADMIN)
                // companyId is set in business logic
                .build();

    }

    public static CompanyAdminDto fromEntity(CompanyAdmin companyAdmin){
        return CompanyAdminDto.builder()
                .id(companyAdmin.getId())
                .email(companyAdmin.getEmail())
                .firstName(companyAdmin.getFirstName())
                .lastName(companyAdmin.getLastName())
                .phoneNumber(companyAdmin.getPhoneNumber())
                .bio(companyAdmin.getBio())
                .role(companyAdmin.getRole().name())
                .companyId(companyAdmin.getCompany().getId())
                .build();
    }


}
