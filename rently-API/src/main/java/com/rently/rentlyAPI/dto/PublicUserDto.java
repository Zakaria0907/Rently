package com.rently.rentlyAPI.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rently.rentlyAPI.entity.user.PublicUser;
import com.rently.rentlyAPI.security.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
@AllArgsConstructor
public class PublicUserDto extends RootUserDto {

    @JsonProperty("id")
    private Integer id;

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
    @Builder.Default
    private String role;

    @JsonProperty("registration_key")
    private String registrationKey;

    public static PublicUser toEntity(PublicUserDto publicUserDto){
        return PublicUser.builder()
                .email(publicUserDto.getEmail())
                .password(publicUserDto.getPassword())
                .firstName(publicUserDto.getFirstName())
                .lastName(publicUserDto.getLastName())
                .phoneNumber(publicUserDto.getPhoneNumber())
                .bio(publicUserDto.getBio())
                .role(Role.PUBLIC_USER)
                // companyId is set in business logic
                .build();

    }

    public static PublicUserDto fromEntity(PublicUser publicUser){
        return PublicUserDto.builder()
                .id(publicUser.getId())
                .email(publicUser.getEmail())
                .firstName(publicUser.getFirstName())
                .lastName(publicUser.getLastName())
                .phoneNumber(publicUser.getPhoneNumber())
                .bio(publicUser.getBio())
                .role(publicUser.getRole().name())
                .build();
    }
}
