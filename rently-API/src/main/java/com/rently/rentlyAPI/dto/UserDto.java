package com.rently.rentlyAPI.dto;

import com.rently.rentlyAPI.entity.User;
import com.rently.rentlyAPI.security.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserDto {

    private Integer id;

    @NotEmpty(message = "The first name is required")
    @NotNull(message = "The first name is required")
    @NotBlank(message = "The first name is required")
    private String firstname;

    @NotEmpty(message = "The last name is required")
    @NotNull(message = "The last name is required")
    @NotBlank(message = "The last name is required")
    private String lastname;

    //TODO: Add validation for phone number
    private String phoneNumber;
    //TODO: Add validation for bio
    private String bio;


    @NotNull(message = "The email is required")
    @NotBlank(message = "The email is required")
    @NotEmpty(message = "The email is required")
    private String email;

    @Builder.Default
    private String role = Role.USER.name();




    public static UserDto fromEntity(User user) {
        return UserDto.builder()
            .id(user.getId())
            .firstname(user.getFirstname())
            .lastname(user.getLastname())
            .email(user.getEmail())
            .role(user.getRole().name())
            //TODO: Add phone number and bio
//            .phoneNumber(user.getPhoneNumber())
//            .bio(user.getBio())
            .build();
    }

    public static User toEntity(UserDto userDTO) {
        return User.builder()
            .id(userDTO.getId())
            .firstname(userDTO.getFirstname())
            .lastname(userDTO.getLastname())
            //TODO: Add phone number and bio
//            .phoneNumber(userDTO.getPhoneNumber())
//            .bio(userDTO.getBio())
            .email(userDTO.getEmail())
            .role(Role.valueOf(userDTO.getRole()))
            .build();
    }

}
