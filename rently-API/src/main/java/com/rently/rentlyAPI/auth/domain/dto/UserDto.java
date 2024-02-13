package com.rently.rentlyAPI.auth.domain.dto;

import com.rently.rentlyAPI.auth.domain.entity.User;
import com.rently.rentlyAPI.auth.domain.enums.Role;
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

    private String firstname;

    private String lastname;

    private String email;

    private String role;

    public static UserDto fromEntity(User user) {
        return UserDto.builder()
            .id(user.getId())
            .firstname(user.getFirstname())
            .lastname(user.getLastname())
            .email(user.getEmail())
            .role(user.getRole().name())
            .build();
    }

    public static User toEntity(UserDto userDTO) {
        return User.builder()
            .id(userDTO.getId())
            .firstname(userDTO.getFirstname())
            .lastname(userDTO.getLastname())
            .email(userDTO.getEmail())
            .role(Role.valueOf(userDTO.getRole()))
            .build();
    }

}
