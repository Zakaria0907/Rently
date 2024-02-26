package com.rently.rentlyAPI.dto.auth;


import com.rently.rentlyAPI.entity.User;
import com.rently.rentlyAPI.entity.auth.Provider;
import com.rently.rentlyAPI.security.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {


    private Integer id;

    @NotBlank(message = "The first name is required")
    private String firstname;
    
    @NotBlank(message = "The last name is required")
    private String lastname;

    @NotBlank(message = "The email is required")
    private String email;
    
    @NotBlank(message = "The password is required")
    private String password;
    
    private String phoneNumber;
    
    private String bio;

    @Builder.Default
    private Role role = Role.COMPANY;

    @Builder.Default
    private Provider provider = Provider.RENTLY;

//    public static RegisterRequestDto fromEntity(User user) {
//        return RegisterRequestDto.builder()
//                .id(user.getId())
//                .firstname(user.getFirstname())
//                .lastname(user.getLastname())
//                .email(user.getEmail())
//                .password(user.getPassword())
//                .role(user.getRole())
//                .provider(user.getProvider())
//                .build();
//    }
//
//    public static User toEntity(RegisterRequestDto registerRequestDto) {
//        return User.builder()
//                .id(registerRequestDto.getId())
//                .firstname(registerRequestDto.getFirstname())
//                .lastname(registerRequestDto.getLastname())
//                .email(registerRequestDto.getEmail())
//                .password(registerRequestDto.getPassword())
//                .role(registerRequestDto.getRole())
//                .provider(registerRequestDto.getProvider())
//                .build();
//    }
}
